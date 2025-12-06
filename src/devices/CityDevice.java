package devices;

import city.City;
import concurent.CityThreadPool;
import concurent.DeviceTickJob;
import events.CityEventType;
import factory.DeviceType;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class CityDevice {
    private final String id;
    protected DeviceType type;
    private City city;
    protected volatile int intervalSeconds;
    protected ScheduledFuture<?> future;

    public CityDevice(String id, int intervalSeconds, DeviceType type) {
        this.id = id;
        this.type = type;
        if(intervalSeconds <= 0) throw new IllegalArgumentException("Seconds can't be <= 0");
        this.intervalSeconds = intervalSeconds;
    }

    public int getIntervalSeconds() {
        return intervalSeconds;
    }

    public String getId(){
        return id;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City getCity(){
        return city;
    }

    protected void updateStatus(String message) {
        if (city != null) {
            city.notifyListeners(this, CityEventType.STATUS, message);
        }
    }

    public void stop() {
        if(future != null && !future.isCancelled()) {
            future.cancel(false);
        }
    }

    public void schedule(CityThreadPool pool){
        DeviceTickJob job = new DeviceTickJob(this);
        future = pool.getScheduler().scheduleAtFixedRate(
                job,
                0,
                intervalSeconds,
                TimeUnit.SECONDS
        );
    }

    public abstract void performAction();
}

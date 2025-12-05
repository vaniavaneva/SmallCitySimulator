package devices;

import city.City;
import city.CityZone;
import concurent.CityThreadPool;
import devices.states.DeviceState;
import devices.states.OperationalState;
import factory.DeviceType;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class CityDevice {
    private final String id;
    protected DeviceType type;
    protected DeviceState state = new OperationalState();
    private CityZone cityZone;
    protected volatile int intervalSeconds;
    protected ScheduledFuture<?> future;

    public CityDevice(String id, int intervalSeconds, DeviceType type) {
        this.id = id;
        this.type = type;
        if(intervalSeconds <= 0) throw new IllegalArgumentException("Seconds can't be <= 0");
        this.intervalSeconds = intervalSeconds;
    }

    /*public int getIntervalSeconds() {
        return intervalSeconds;
    }*/

    public String getId(){
        return id;
    }

    public void setCityZone(CityZone cityZone) {
        this.cityZone = cityZone;
    }

    public CityZone getCityZone(){
        return cityZone;
    }

    public void setState(DeviceState state) {
        this.state = state;
    }
    public DeviceState getState() {
        return state;
    }

    public void updateStatus(String message) {
        if (city != null) {
            city.notifyListeners(this, message);
        }
    }

    public void schedule(CityThreadPool pool){
        future = pool.getScheduler().schedule(() -> {
            performAction();
            schedule(pool);
        }, intervalSeconds, TimeUnit.SECONDS);
    }

    public abstract void performAction();
}

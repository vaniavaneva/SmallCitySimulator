package city;

import concurent.CityThreadPool;
import devices.CityDevice;

import java.util.ArrayList;
import java.util.List;

public class City {
    private final List<CityDevice> devices = new ArrayList<>();
    private final List<CityEventListener> listeners = new ArrayList<>();
    private CityThreadPool pool;

    public void setThreadPool(CityThreadPool pool){
        this.pool = pool;
    }

    public CityThreadPool getThreadPool() {
        return pool;
    }

    public void addDevice(CityDevice device) {
        device.setCity(this);
        devices.add(device);
    }

    public void addListener(CityEventListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners(CityDevice device, String message) {
        for (CityEventListener listener : listeners) {
            listener.onStatus(device, message);
        }
    }

    public void startSimulation(){
        for(CityDevice device : devices){
            device.schedule(pool);
        }
    }
}

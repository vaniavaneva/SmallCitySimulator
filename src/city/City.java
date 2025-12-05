package city;

import concurent.CityThreadPool;
import devices.CityDevice;

import java.util.ArrayList;
import java.util.List;

public class City {
    private final String name;
    private final List<CityZone> zones = new ArrayList<>();
    private final List<CityEventListener> listeners = new ArrayList<>();

    public City(String name){
        this.name = name;
    }

    public void addZone(CityZone zone) {
        zones.add(zone);
    }

    public void addListener(CityEventListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners(CityDevice device, String message) {
        for (CityEventListener listener : listeners) {
            listener.onStatus(device, message);
        }
    }

    public List<CityZone> getZones() {
        return zones;
    }

    public String getName() {
        return name;
    }
}
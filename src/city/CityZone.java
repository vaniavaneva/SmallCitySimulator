package city;

import devices.CityDevice;

import java.util.ArrayList;
import java.util.List;

public class CityZone {
    private final String name;
    private final List<CityDevice> devices = new ArrayList<>();

    public CityZone(String name){
        this.name = name;
    }

    public void addDevice(CityDevice device) {
        device.setCityZone(this);
        devices.add(device);
    }

    public List<CityDevice> getDevices(){
        return devices;
    }

    public String getName(){
        return name;
    }
}

package concurent;

import devices.CityDevice;

public class DeviceTickJob implements Runnable{
    private final CityDevice device;

    public DeviceTickJob(CityDevice device){
        this.device = device;
    }

    @Override
    public void run(){
        device.performAction();
    }
}

package concurent;

import devices.CityDevice;
import devices.states.DeviceState;

public class DeviceTickJob implements Runnable{
    private final CityDevice device;

    public DeviceTickJob(CityDevice device){
        this.device = device;
    }

    @Override
    public void run(){
        if(device.getState().allowAction()){
            device.performAction();
        } else {
            device.getState().handle(device);
        }
    }
}

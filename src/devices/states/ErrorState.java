package devices.states;

import devices.CityDevice;

public class ErrorState implements DeviceState{
    @Override
    public boolean allowAction() {
        return false;
    }

    @Override
    public void handle(CityDevice device) {
        device.updateStatus("Device " + device.getId() + " has ERROR detected");
    }
}

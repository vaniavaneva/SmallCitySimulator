package devices.states;

import devices.CityDevice;

public class MaintenanceState implements DeviceState{
    @Override
    public boolean allowAction() {
        return false;
    }

    @Override
    public void handle(CityDevice device) {
        device.updateStatus("Device " + device.getId() + " is under maintenance");
    }
}

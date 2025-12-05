package devices.states;

import devices.CityDevice;

public class OperationalState implements DeviceState{
    @Override
    public boolean allowAction() {
        return true;
    }

    @Override
    public void handle(CityDevice device) {

    }
}

package observers;

import devices.CityDevice;
import events.CityEventType;

public class Dashboard implements CityEventListener {
    @Override
    public void onEvent(CityDevice device, CityEventType type, String message){
        if (type != CityEventType.ALERT) {
            System.out.println("[DASHBOARD] " + device.getId() + ": " + message);
        }
    }
}

package observers;

import devices.CityDevice;
import events.CityEventType;

public class AlertSystem implements CityEventListener{
    @Override
    public void onEvent(CityDevice device, CityEventType type, String message) {
        if (type == CityEventType.ALERT) {
            System.out.println("[ALERT] ⚠\uFE0F " + device.getId() + " " + message);
        }
    }
}

package observers;

import devices.CityDevice;
import events.CityEventType;
import java.util.logging.Logger;

public class AlertSystem implements CityEventListener{
    private static final Logger logger = Logger.getLogger(AlertSystem.class.getName());

    @Override
    public void onEvent(CityDevice device, CityEventType type, String message) {
        if (type == CityEventType.ALERT) {
            logger.warning("[ALERT] " + device.getId() + " " + message);
        }
    }
}

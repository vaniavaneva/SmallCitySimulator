package observers;

import devices.CityDevice;
import events.CityEventType;
import util.LoggerFactory;

import java.util.logging.Logger;

public class AlertSystem implements CityEventListener{
    private static final Logger logger = LoggerFactory.getLogger("ALERTSYSTEM");

    @Override
    public void onEvent(CityDevice device, CityEventType type, String message) {
        if (type == CityEventType.ALERT) {
            logger.warning("[ALERT] " + device.getId() + " " + message);
        }
    }
}

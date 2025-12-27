package observers;

import devices.CityDevice;
import events.CityEventType;
import java.util.logging.Logger;

public class Dashboard implements CityEventListener {
    private static final Logger logger = Logger.getLogger(Dashboard.class.getName());

    @Override
    public void onEvent(CityDevice device, CityEventType type, String message){
        if (type != CityEventType.ALERT) {
            logger.info("[DASHBOARD] " + device.getId() + ": " + message);
        }
    }
}

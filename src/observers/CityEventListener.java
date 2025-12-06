package observers;

import devices.CityDevice;
import events.CityEventType;

public interface CityEventListener {
    void onEvent(CityDevice device, CityEventType type, String message);
}

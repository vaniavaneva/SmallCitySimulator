package city;

import devices.CityDevice;

public interface CityEventListener {
    void onStatus(CityDevice device, String message);
}

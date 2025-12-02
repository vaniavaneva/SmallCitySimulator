package observers;

import city.CityEventListener;
import devices.CityDevice;

public class AlertSystem implements CityEventListener{

    @Override
    public void onStatus(CityDevice device, String message) {
        if (message.toUpperCase().contains("ALERT")) {
            System.out.println("[ALERT] " + device.getId() + " " + message);
        }
    }
}

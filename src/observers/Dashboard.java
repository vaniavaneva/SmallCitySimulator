package observers;

import city.CityEventListener;
import devices.CityDevice;

public class Dashboard implements CityEventListener {
    @Override
    public void onStatus(CityDevice device, String message){
        if (!message.toUpperCase().contains("ALERT")) {
            System.out.println("[DASHBOARD] " + device.getId() + ": " + message);
        }
    }
}

package observers;

import city.CityEventListener;
import devices.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLogger implements CityEventListener {
    private final File logFile;

    private List<String> alerts = new ArrayList<>();

    private int trafficLightChanges = 0;

    private int streetLightOnCount = 0;
    private int streetLightTotalEvents = 0;

    private int timesBikesRented = 0;
    private int timesBikesReturned = 0;
    private int timesBikesCharged = 0;

    public DataLogger(String filePath) {
        this.logFile = ensureFileExists(filePath);
    }

    @Override
    public void onStatus(CityDevice device, String message) {

        if (message.toUpperCase().contains("ALERT")) {
            alerts.add(device.getId() + " " + message);
        }

        if (device instanceof TrafficLight) {
            trafficLightChanges++;

        } else if (device instanceof StreetLight) {
            streetLightTotalEvents++;

            if (message.toUpperCase().contains("ON")) {
                streetLightOnCount++;
            }

        }

        if(device instanceof BikeStation){
            if(message.toLowerCase().contains("rented")) timesBikesRented++;
            else if(message.toLowerCase().contains("returned")) timesBikesReturned++;
            else if(message.toLowerCase().contains("charging")) timesBikesCharged++;
        }
    }

    public void saveInfo(){
        clearFile();
        double percent = (streetLightOnCount / (double) streetLightTotalEvents) * 100.0;
        write("All traffic lights changed " + trafficLightChanges + " times.\n\n");
        write("Street lights were ON for " + String.format("%.2f", percent) + "% of the simulation.\n\n");
        write("Bikes were rented " + timesBikesRented + " times.\n\n");
        write("Bikes were returned " + timesBikesReturned + " times.\n\n");
        write("Bikes were charged " + timesBikesCharged + " times.\n\n");
        write("Alerts occurred: " + alerts.size() + "\n");
        for(String a : alerts){
            write("\t" + a + "\n");
        }
    }

    private File ensureFileExists(String path) {
        File f = new File(path);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return f;
    }

    private void clearFile() {
        try (FileWriter fw = new FileWriter(logFile, false)) { // false = overwrite
            fw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(String text) {
        try (FileWriter fw = new FileWriter(logFile, true)) {
            fw.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

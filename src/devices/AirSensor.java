package devices;

import factory.DeviceType;
import strategies.air.AirAnalysisStrategy;
import java.util.ArrayList;
import java.util.List;

public class AirSensor extends CityDevice{
    private double pm25;
    private AirAnalysisStrategy strategy;
    private List<Double> history = new ArrayList<>();
    private final int MAX_HISTORY = 100;


    public AirSensor(String id){
        super(id, 5, DeviceType.AIR_SENSOR);
    }

    public void setStrategy(AirAnalysisStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void performAction(){
        pm25 = 20 + Math.random() * 60;
        history.add(pm25);

        if(history.size() > MAX_HISTORY){
            history.removeFirst();
        }

        double quality = strategy.analyzeQuality(history);
        if(quality > 55){
            updateStatus("ALERT: Poor air quality. PM2.5=" + String.format("%.2f", pm25));
        } else {
            updateStatus("Air OK: " + String.format("%.2f", pm25));
        }
    }
}

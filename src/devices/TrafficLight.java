package devices;

import factory.DeviceType;
import strategies.traffic.TrafficStrategy;

public class TrafficLight extends CityDevice{
    private volatile String state = "RED \uD83D\uDD34";
    private TrafficStrategy strategy;

    public TrafficLight(String id) {
        super(id,1, DeviceType.TRAFFIC_LIGHT);
    }

    public void setStrategy(TrafficStrategy strategy){
        this.strategy = strategy;
    }

    @Override
    public void performAction() {
        if(strategy==null){
            updateStatus("No strategy set.");
        }

        switch (state){
            case "RED \uD83D\uDD34" -> state = "GREEN \uD83D\uDFE2";
            case "GREEN \uD83D\uDFE2" -> state = "YELLOW \uD83D\uDFE1";
            case "YELLOW \uD83D\uDFE1" -> state = "RED \uD83D\uDD34";
        }

        int greenTime = strategy.computeGreenTime(state);
        this.intervalSeconds = greenTime;

        updateStatus("Light changed to: " + state + " (next change in " + greenTime + "s)");
    }
}

package devices;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import city.*;
import factory.DeviceType;

public class BikeStation extends CityDevice{
    private int bikesAvailable;
    private int capacity;
    private int chargers;
    private List<ScheduledFuture<?>> chargingSlots = new ArrayList<>();

    public BikeStation(String id) {
        super(id, 7, DeviceType.BIKE_STATION);
    }

    public void setBikesAvailable(int bikes){
        if(bikes > capacity) throw new IllegalArgumentException("Available bikes should be <= capacity");
        bikesAvailable = bikes;
    }

    public void setCapacity(int capacity){
        if(capacity < bikesAvailable) throw new IllegalArgumentException("Capacity should be >= bikes available");
        this.capacity = capacity;
    }

    public void setChargers(int chargers){
        if(chargers <= 0) throw new IllegalArgumentException("Chargers can't be <= 0");
        this.chargers = chargers;
    }

    /*public int getBikesAvailable() {
        return bikesAvailable;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getChargers(){
        return chargers;
    }*/

    @Override
    public void performAction() {
        double chance = Math.random(); //0-1

        if(chance < 0.4){ //40% rent
            if(bikesAvailable > 0){
                bikesAvailable--;
                updateStatus("Bikes rented | Available: " + bikesAvailable);
            } else {
                updateStatus("ALERT: No bikes available");
            }
        } else if(chance < 0.8){ //40% return
            if(bikesAvailable < capacity){
                bikesAvailable++;
                updateStatus("Bike returned | Available: " + bikesAvailable);
            } else {
                updateStatus("ALERT: Station full, can't return bike");
            }
        } else { //20% electric bike charging
            if(chargers > 0){
                startCharging();
            } else{
                updateStatus("ALERT: No chargers available");
            }
        }

        if(bikesAvailable <= 1){
            updateStatus("ALERT: Bike levels low (" + bikesAvailable + ")");
        }
        if(chargers <= 1){
            updateStatus("ALERT: Charger levels low (" + chargers + ")");
        }
    }

    private void startCharging(){
        chargers--;
        int chargeTime = (int)(Math.random() * 11 + 20); //20-30

        updateStatus("Electric bike charging | ETC: " + chargeTime + "s");

        ScheduledFuture<?> future = getCity().getThreadPool().getScheduler().schedule(() -> {
            chargers++;
            updateStatus("Charging complete. Chargers available: " + chargers);
        }, chargeTime, TimeUnit.SECONDS);
        chargingSlots.add(future);
    }
}

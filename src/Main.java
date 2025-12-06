import city.City;
import concurent.CityThreadPool;
import devices.*;
import factory.DeviceFactory;
import factory.DeviceType;
import observers.*;
import strategies.air.AverageStrategy;
import strategies.air.PeakDetectionStrategy;
import strategies.traffic.AdaptiveTrafficStrategy;
import strategies.traffic.FixedCycleStrategy;

public class Main {

    private static final int SIMULATION_DURATION_MS = 120_000;

    public static void main(String[] args) throws InterruptedException {
        City city = new City();

        CityThreadPool pool = new CityThreadPool(4);
        city.setThreadPool(pool);

        city.addListener(new Dashboard());
        city.addListener(new AlertSystem());
        DataLogger logger = new DataLogger("city_log.txt");
        city.addListener(logger);

        TrafficLight tl1 = (TrafficLight) DeviceFactory.createDevice(DeviceType.TRAFFIC_LIGHT, "TL-01");
        tl1.setStrategy(new AdaptiveTrafficStrategy(5, 15, 2, 4));
        TrafficLight tl3 = (TrafficLight) DeviceFactory.createDevice(DeviceType.TRAFFIC_LIGHT, "TL-03");
        tl3.setStrategy(new AdaptiveTrafficStrategy(5, 15, 2, 4));

        TrafficLight tl2 = (TrafficLight) DeviceFactory.createDevice(DeviceType.TRAFFIC_LIGHT, "TL-02");
        tl2.setStrategy(new FixedCycleStrategy());
        TrafficLight tl4 = (TrafficLight) DeviceFactory.createDevice(DeviceType.TRAFFIC_LIGHT, "TL-04");
        tl4.setStrategy(new FixedCycleStrategy());

        AirSensor as1 = (AirSensor) DeviceFactory.createDevice(DeviceType.AIR_SENSOR, "AS-01");
        as1.setStrategy(new AverageStrategy());
        AirSensor as3 = (AirSensor) DeviceFactory.createDevice(DeviceType.AIR_SENSOR, "AS-03");
        as3.setStrategy(new AverageStrategy());

        AirSensor as2 = (AirSensor) DeviceFactory.createDevice(DeviceType.AIR_SENSOR, "AS-02");
        as2.setStrategy(new PeakDetectionStrategy());
        AirSensor as4 = (AirSensor) DeviceFactory.createDevice(DeviceType.AIR_SENSOR, "AS-04");
        as4.setStrategy(new PeakDetectionStrategy());

        StreetLight sl1 = (StreetLight) DeviceFactory.createDevice(DeviceType.STREET_LIGHT, "SL-01");
        StreetLight sl2 = (StreetLight) DeviceFactory.createDevice(DeviceType.STREET_LIGHT, "SL-02");

        BikeStation bs1 = (BikeStation) DeviceFactory.createDevice(DeviceType.BIKE_STATION, "BS-01");
        BikeStation bs2 = (BikeStation) DeviceFactory.createDevice(DeviceType.BIKE_STATION, "BS-02");

        bs1.setCapacity(8);
        bs1.setBikesAvailable(5);
        bs1.setChargers(2);

        bs2.setCapacity(4);
        bs2.setBikesAvailable(4);
        bs2.setChargers(3);

        city.addDevice(tl1);
        city.addDevice(tl2);
        city.addDevice(as1);
        city.addDevice(as2);
        city.addDevice(sl1);
        city.addDevice(sl2);
        city.addDevice(bs1);
        city.addDevice(bs2);

        city.startSimulation();
        Thread.sleep(SIMULATION_DURATION_MS);

        for(CityDevice device : city.getAllDevices()) {
            device.stop();
            if(device instanceof BikeStation) {
                ((BikeStation)device).cancelAllCharging();
            }
        }

        pool.shutdown();
        System.out.println("Simulation stopped.");
        logger.saveInfo();
    }
}
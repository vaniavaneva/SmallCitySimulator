package concurent;

import devices.CityDevice;
import events.CityEventType;
import util.LoggerFactory;

import java.util.Objects;
import java.util.logging.Logger;

public class DeviceTickJob implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger("DEVICETICKJOB");
    private final CityDevice device;

    public DeviceTickJob(CityDevice device){
        this.device = Objects.requireNonNull(device);
    }

    @Override
    public void run(){
        logger.fine("Executing " + device.getId());
        try {
            long start = System.nanoTime();
            device.performAction();
            long duration = System.nanoTime() - start;

            logger.fine(device.getId() + " completed in " + duration/1_000_000 + "ms");
        } catch (Exception e) {
            logger.severe("Error in " + device.getId() + ": " + e);
        }
    }
}

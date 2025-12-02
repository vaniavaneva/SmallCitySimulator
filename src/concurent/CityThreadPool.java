package concurent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class CityThreadPool {
    private final ScheduledExecutorService scheduler;

    public CityThreadPool(int threads){
        scheduler = Executors.newScheduledThreadPool(threads);
    }

    public ScheduledExecutorService getScheduler() {
        return scheduler;
    }

    public void shutdown(){
        scheduler.shutdownNow();
    }
}
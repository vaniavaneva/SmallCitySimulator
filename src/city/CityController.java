package city;

import concurent.CityThreadPool;
import devices.CityDevice;

public class CityController {
    private static CityController instance;

    private CityThreadPool pool;

    public void setThreadPool(CityThreadPool pool){
        this.pool = pool;
    }

    public CityThreadPool getThreadPool() {
        return pool;
    }

    private CityController() {}
    public static CityController getInstance() {
        if(instance != null){
            instance = new CityController();
        }
        return instance;
    }

    public void startSimulation(City city) {
        for(CityDevice device : devices){
            device.schedule(pool);
        }
    }
}

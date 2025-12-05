package city;

import concurent.CityThreadPool;
import devices.CityDevice;

public class CityController {
    private static CityController instance;



    private CityController() {}
    public static CityController getInstance() {
        if(instance != null){
            instance = new CityController();
        }
        return instance;
    }
}

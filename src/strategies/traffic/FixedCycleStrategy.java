package strategies.traffic;

import java.util.Objects;

public class FixedCycleStrategy implements TrafficStrategy{
    @Override
    public int computeGreenTime(String state){
        if(Objects.equals(state, "YELLOW \uD83D\uDFE1")){
            return 1;
        } else {
            return 10;
        }
    }
}

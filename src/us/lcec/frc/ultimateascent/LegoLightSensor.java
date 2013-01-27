package us.lcec.frc.ultimateascent;

import edu.wpi.first.wpilibj.AnalogChannel;


public class LegoLightSensor {

    
    AnalogChannel an;
    public LegoLightSensor(
            int analogPort) {
        an = new AnalogChannel(analogPort);
    }

    
    
    
    boolean isWhite()
    {
        return an.getAverageValue() == 2;
    }
            
}

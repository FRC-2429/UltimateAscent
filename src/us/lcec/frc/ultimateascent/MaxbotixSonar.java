package us.lcec.frc.ultimateascent;

import edu.wpi.first.wpilibj.AnalogChannel;


public class MaxbotixSonar {

    AnalogChannel an;
    
    public MaxbotixSonar(int channel) {
        an = new AnalogChannel(channel);
    }
    
    public double getInches()
    {
        return an.getAverageVoltage()/9.8 * 1000; 
    }

    
    
    
}

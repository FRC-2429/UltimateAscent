package us.lcec.frc.ultimateascent;


public class TillLight implements BooleanInter{

    LegoLightSensor lightSensor;
    
    
    public TillLight(LegoLightSensor lightSensor) {
        
        this.lightSensor = lightSensor;
    }

    public boolean run() {
        return lightSensor.isWhite();
    }
    
    
    

    
}

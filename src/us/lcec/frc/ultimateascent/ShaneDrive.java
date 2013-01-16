package us.lcec.frc.ultimateascent;


public class ShaneDrive implements DriveControlMethod{

    public double getForward() {
        
        return ElectronicsMap.joy1.getY();
    }

    public double getRotation() {
         return ElectronicsMap.joy2.getY();
    }

}

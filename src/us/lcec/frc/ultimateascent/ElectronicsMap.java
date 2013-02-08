package us.lcec.frc.ultimateascent;

import edu.wpi.first.wpilibj.Joystick;


public class ElectronicsMap {

    final static public boolean practiceBot = true;
    
    
    static {
        if (practiceBot)
        {
            rightOneDrive = 20;
            rightTwoDrive = 3;
            leftOneDrive = 11;
            leftTwoDrive = 7;
        }
        
    }
    static public int rightOneDrive;
    static public int rightTwoDrive;
    static public int leftOneDrive;
    static public int leftTwoDrive;
    
    public static Joystick joy1;
    public static Joystick joy2;
    public static Joystick xbox;
}

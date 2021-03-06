package us.lcec.frc.ultimateascent;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ElectronicsMap {

    final static public boolean practiceBot = true;
    
    static public RecordingCrio  recorder;
    
    
    static {
        if (practiceBot)
        {
            rightOneDrive = 6;
            rightTwoDrive = 7;
            leftOneDrive = 3;
            leftTwoDrive = 12;
            armOne=11;
            armTwo=20;
            armRotate=13;
        }
        
    }
    
    static public void init()
    {
        leftSonar = new MaxbotixSonar(5);
        rightSonar = new MaxbotixSonar(4);
        
        
        
        
        joy1 = new Joystick(1);
        joy2 = new Joystick(2);
        xbox = new Joystick(3);
        
        recorder = new RecordingCrio();
        
        SmartDashboard.putData(recorder);
        
    }
    
    static public int rightOneDrive;
    static public int rightTwoDrive;
    static public int leftOneDrive;
    static public int leftTwoDrive;
    static public int armOne;
    static public int armTwo;
    static public int armRotate;
    
    public static Joystick joy1;
    public static Joystick joy2;
    public static Joystick xbox;
    
    public static MaxbotixSonar leftSonar;
    public static MaxbotixSonar rightSonar;
}

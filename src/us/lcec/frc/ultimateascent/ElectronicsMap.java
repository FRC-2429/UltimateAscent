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
            rightOneDrive = 20;
            rightTwoDrive = 7;
            leftOneDrive = 3;
            leftTwoDrive = 14;
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
    
    public static Joystick joy1;
    public static Joystick joy2;
    public static Joystick xbox;
    
    public static MaxbotixSonar leftSonar;
    public static MaxbotixSonar rightSonar;
}

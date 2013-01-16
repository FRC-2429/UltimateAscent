package us.lcec.frc.ultimateascent;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;


public class RobotDrive {

    SpeedController leftOne, leftTwo;
    SpeedController rightOne, rightTwo;
    
    
    DriveControlMethod methodToDrive = new ShaneDrive();
    
 

    public RobotDrive() {
        leftOne = new Jaguar(ElectronicsMap.leftOneDrive);
        leftTwo = new Jaguar(ElectronicsMap.leftTwoDrive);
        
        rightOne = new Jaguar(ElectronicsMap.rightOneDrive);
        rightTwo = new Jaguar(ElectronicsMap.rightTwoDrive);
        
        
        
        
        
    }
    
    
    
    void update()
    {
        
        double forward = methodToDrive.getForward();
        double rotation = methodToDrive.getRotation();
        
        double left = forward + rotation;
        double right = forward -  rotation;
        
        
        
        double maximum = Math.max(Math.abs(left), Math.abs(right));
        if (maximum > 1)
        {
            left/= maximum;
            right/= maximum;
        }
        
        leftOne.set(left);
        leftTwo.set(left);
        rightOne.set(-right);
        rightTwo.set(-right);
    }
    
    
    
    
    
    
    
    
    
}

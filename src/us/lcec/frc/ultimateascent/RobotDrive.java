package us.lcec.frc.ultimateascent;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RobotDrive {

    CANJaguar leftOne, leftTwo;
    CANJaguar rightOne, rightTwo;
    
    

    
 
    SendableChooser driveMethodChooser;
    

    public RobotDrive() {
        try {
            leftOne = new CANJaguar(ElectronicsMap.leftOneDrive);
            leftTwo = new CANJaguar(ElectronicsMap.leftTwoDrive);
            
            rightOne = new CANJaguar(ElectronicsMap.rightOneDrive);
            rightTwo = new CANJaguar(ElectronicsMap.rightTwoDrive);
            
            LiveWindow.addActuator("RobotDrive", "leftOne", leftOne);
            LiveWindow.addActuator("RobotDrive", "leftTwo", leftTwo);
            
            LiveWindow.addActuator("RobotDrive", "rightOne", rightOne);
            LiveWindow.addActuator("RobotDrive", "rightTwo", rightTwo);
            
            
           
        
            
            driveMethodChooser = new SendableChooser();
            driveMethodChooser.addDefault("Shane Drive", new ShaneDrive());
            driveMethodChooser.addObject("Ethan Drive", new EthanDrive());
            SmartDashboard.putData("DriveMethod",driveMethodChooser);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        
        
        
    }
    
    
    
    void set(double forward, double rotation)
    {
     
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
    
    
    
    void update()
    {
       
        
     
            
            DriveControlMethod methodToDrive = (DriveControlMethod) driveMethodChooser.getSelected();
                double forward = methodToDrive.getForward();
            double rotation = methodToDrive.getRotation();
            
          set(forward,rotation);
        
    }
    
    
    
    
    
    
    
    
    
}

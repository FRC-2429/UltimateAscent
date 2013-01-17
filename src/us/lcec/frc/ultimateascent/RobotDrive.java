package us.lcec.frc.ultimateascent;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RobotDrive {

    Jaguar leftOne, leftTwo;
    Jaguar rightOne, rightTwo;
    
    

    
 
    SendableChooser driveMethodChooser;
    

    public RobotDrive() {
        leftOne = new Jaguar(ElectronicsMap.leftOneDrive);
        leftTwo = new Jaguar(ElectronicsMap.leftTwoDrive);
        
        rightOne = new Jaguar(ElectronicsMap.rightOneDrive);
        rightTwo = new Jaguar(ElectronicsMap.rightTwoDrive);
        
        LiveWindow.addActuator("RobotDrive", "leftOne", leftOne);
        LiveWindow.addActuator("RobotDrive", "leftTwo", leftTwo);
        
        LiveWindow.addActuator("RobotDrive", "rightOne", rightOne);
        LiveWindow.addActuator("RobotDrive", "rightTwo", rightTwo);
        
        
        
        driveMethodChooser = new SendableChooser();
        driveMethodChooser.addDefault("Shane Drive", new ShaneDrive());
        driveMethodChooser.addObject("Ethan Drive", new EthanDrive());
        
        SmartDashboard.putData("DriveMethod",driveMethodChooser);
        
        
        
    }
    
    
    
    void update()
    {
        
        DriveControlMethod methodToDrive = (DriveControlMethod) driveMethodChooser.getSelected();
        
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

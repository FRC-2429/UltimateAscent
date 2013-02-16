/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package us.lcec.frc.ultimateascent;


import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Dashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.buttons.NetworkButton;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
     Jaguar jag1;
     Jaguar jag2;
     Jaguar jag3;
     Jaguar jag4;
     Relay re;
     Joystick x;
     
     NetworkButton b;
     RobotDrive hamburgers;
     RecordingCrio r;
     boolean init=false;
    public void robotInit() {
        System.out.println("Hello");
//        try {
//            arm = new CANJaguar(11);
//        } catch (CANTimeoutException ex) {
//            ex.printStackTrace();
//        }
        jag1 = new Jaguar(1);
        jag2 = new Jaguar(2);
        jag3 = new Jaguar(3);
        jag4 = new Jaguar(4);
        //hamburgers = new RobotDrive();
//        temp = new Jaguar(6);
        x = new Joystick(1);
       
        
        //SmartDashboard.getBoolean("foo");
        SmartDashboard.putString("blah2", "Ok");
        
        b = new NetworkButton("Blah", "fii");
        SmartDashboard.putData("What",b);
        
        SendableChooser chose = new SendableChooser();
        chose.addObject("blah", "1");
        
        SmartDashboard.putData("Foo", chose);
        
        r = new RecordingCrio();
        
        SmartDashboard.putData(r);
        r.addMotor(jag1);
        r.addMotor(jag2);
        r.addMotor(jag3);
        r.addMotor(jag4);
        

    }

    public void autonomousInit() {
      
    }

    public void disabledInit() {
     
    }
    
    

    /**
     * This function is called periodically during autonomous
     */
    
    public void autonomousPeriodic() {
         
    }

    /**
     * This function is called periodically during operator control
     */
    public void telepInit(){
        System.out.println(init);
        
    }
    public void teleopPeriodic() {
        
        
        if (!r.play.playing)
        {
           jag1.set(x.getY());
           jag2.set(-x.getY());
           jag3.set(-x.getY());
           jag4.set(x.getY());
        }
        else {
            hamburgers.update();
        
        }  
         
       //r.update();
      
    }
    
    /**
     * This function is called periodically during test mode
     */
    
    
    public void testPeriodic() {
    
       
    }
    
}

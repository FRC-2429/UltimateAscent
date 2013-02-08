/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package us.lcec.frc.ultimateascent;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.NetworkButton;
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
     
     Joystick x;
     
     NetworkButton b;
     
     
     RecordingCrio r;
     
    public void robotInit() {
        System.out.println("Hello");
      
        
        jag1 = new Jaguar(1);
        jag2 = new Jaguar(2);
        jag3 = new Jaguar(3);
        jag4 = new Jaguar(4);
        
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
    public void teleopPeriodic() {
        if (!r.play.playing)
        {
           jag1.set(x.getY());
           jag2.set(-x.getY());
           jag3.set(-x.getY());
           jag4.set(x.getY());
        }
        
       r.update();
       
    }
    
    /**
     * This function is called periodically during test mode
     */
    
    
    public void testPeriodic() {
    
       
    }
    
}

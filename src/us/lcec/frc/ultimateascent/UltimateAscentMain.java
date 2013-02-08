/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package us.lcec.frc.ultimateascent;

import com.sun.squawk.debugger.JDWPListener;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.HiTechnicColorSensor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class UltimateAscentMain extends IterativeRobot {

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    //ADXL345_I2C accel;
    
    RobotDrive drive;

    
    //AnalogChannel an,an2;
    
    
    //DigitalInput one,two,three;
    
    
    //Gyro g;
    
    
    //LegoLightSensor light;
    PIDController controller;
    
    
    //HiTechnicColorSensor color;
    
    
    
    public void robotInit() {
        ElectronicsMap.init();
        
        drive = new RobotDrive();

        //accel = new ADXL345_I2C(1, ADXL345_I2C.DataFormat_Range.k2G);
        
        //color = new HiTechnicColorSensor(1);
        //SmartDashboard.putData("HiColor", color);
        
        
        
        
//        an = new AnalogChannel(4);
//        an2 = new AnalogChannel(2);
//        g = new Gyro(1);
//        
//        one = new DigitalInput(1);
//        two = new DigitalInput(2);
//        three = new DigitalInput(3);
//        
//        
//        light = new LegoLightSensor(3);
        
      
        
//        controller = new PIDController(.01, 0, 0, g, new PIDOutput() {
//
//            public void pidWrite(double output) {
//                drive.set(0,output);
//            }
//        });
        
        
//        SmartDashboard.putData("Foo", controller);
//        
        System.out.println("Ready");
    }

    
    
    
    public void autonomousInit() {
//        g.reset();
//        
//        controller.setAbsoluteTolerance(1);
//        controller.setSetpoint(50);
//        controller.enable();
//        
//        autoQueue.addTask(new PidComplete(controller), new Runnable() {
//
//            public void run() {
//                
//                System.out.println("Done");
//            }
//        });
        
//        autoQueue.clear();
//        drive.set(.2, 0);
//        
//        autoQueue.addTask(new TillLight(light), new Runnable() {
//
//            public void run() {
//                System.out.println("Done");
//                drive.set(0, 0);
//                
//                g.reset();
//        
//             controller.setAbsoluteTolerance(1);
//           controller.setSetpoint(50);
//            controller.enable();
//                autoQueue.addTask(new PidComplete(controller), new Runnable() {
//
//                    public void run() { 
//                        System.out.println("Done2");
//                 drive.set(0, 0);
//                    }
//                });
//            }
//        });
    }

    /**
     * This function is called periodically during autonomous
     */
    
    
    ActionQueue autoQueue = new ActionQueue();
    
    
    public void autonomousPeriodic() {
        autoQueue.update();
        
        //System.out.println(g.getAngle());
        //System.out.println(controller.get());
        
    }
    
    /**
     * This function is called periodically during operator control
     */
    
    boolean hit = false;

    public void teleopInit() {
        hit = false;
    }
    
    
    
    public void teleopPeriodic() {
        
        
        if (!ElectronicsMap.recorder.play.playing)
        {
           drive.update();
        }
        
      

       ElectronicsMap.recorder.update();
      
      
     
      
      
      SmartDashboard.putNumber("LeftSonar", ElectronicsMap.leftSonar.getInches());
      SmartDashboard.putNumber("RightSonar", ElectronicsMap.rightSonar.getInches());
      

    }
    

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {

        LiveWindow.run();
    }
}

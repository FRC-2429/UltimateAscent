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
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.HiTechnicColorSensor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
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
    Gyro g;
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
        g = new Gyro(1);
//        
//        one = new DigitalInput(1);
//        two = new DigitalInput(2);
//        three = new DigitalInput(3);
//        
//        
//        light = new LegoLightSensor(3);



        controller = new PIDController(1, 0, 0, g, new PIDOutput() {
            public void pidWrite(double output) {
                output = Math.max(output, -.2);
                output = Math.min(output, .2);
                drive.set(0, output);
            }
        });


//        SmartDashboard.putData("Foo", controller);
//        
        System.out.println("Ready");
    }

    public void autonomousInit() {



        autoQueue.clear();
        g.reset();

        controller.setAbsoluteTolerance(4);
        controller.setSetpoint(10);
        controller.enable();




        autoQueue.addTask(new PidComplete(controller), new Runnable() {
            public void run() {

                controller.disable();
                drive.set(.2, 0);
                System.out.println("Done");
                autoQueue.addTask(new TimeSince(1000), new Runnable() {
                    public void run() {
                        autoQueue.addTask(new TillDistance(ElectronicsMap.leftSonar, 100), new Runnable() {
                            public void run() {
                                System.out.println("Done");
                                drive.set(0, 0);

                                controller.setSetpoint(0);
                                controller.enable();
                                autoQueue.addTask(new PidComplete(controller), new Runnable() {
                                    public void run() {
                                        controller.disable();
                                        drive.set(.2, 0);
                                        autoQueue.addTask(new TillDistance(ElectronicsMap.leftSonar, 45), new Runnable() {

                                            public void run() {
                                                drive.set(0, 0);
                                                 controller.setSetpoint(55);
                                                 controller.enable();
                                                 autoQueue.addTask(new PidComplete(controller), new Runnable() {

                                                    public void run() {
                                                        controller.disable();
                                                        drive.set(.2, 0);
                                                        autoQueue.addTask(new TillDistance(ElectronicsMap.leftSonar, 9), new Runnable() {

                                                            public void run() {
                                                                drive.set(0,0);
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });


                            }
                        });
                    }
                });


            }
        });

    }
    /**
     * This function is called periodically during autonomous
     */
    ActionQueue autoQueue = new ActionQueue();

    public void autonomousPeriodic() {
        autoQueue.update();

        SmartDashboard.putNumber("LeftSonar", ElectronicsMap.leftSonar.getInches());
        SmartDashboard.putNumber("RightSonar", ElectronicsMap.rightSonar.getInches());

        //System.out.println(g.getAngle());
        //System.out.println(controller.get());

    }
    /**
     * This function is called periodically during operator control
     */
    boolean hit = false;

    public void teleopInit() {
        g.reset();

//        controller.setSetpoint(360);
//        controller.setAbsoluteTolerance(.01);
//        
        controller.disable();

    }

    public void teleopPeriodic() {
        try {
            if (!ElectronicsMap.recorder.play.playing) {
                drive.update();
            }



            SmartDashboard.putString("leftOne", drive.leftOne.getOutputCurrent() + " " + drive.leftOne.getOutputVoltage());
            SmartDashboard.putString("leftTwo", drive.leftTwo.getOutputCurrent() + " " + drive.leftTwo.getOutputVoltage());
            SmartDashboard.putString("rightOne", drive.rightOne.getOutputCurrent() + " " + drive.rightOne.getOutputVoltage());
            SmartDashboard.putString("rightTwo", drive.rightTwo.getOutputCurrent() + " " + drive.rightTwo.getOutputVoltage());


            ElectronicsMap.recorder.update();



            SmartDashboard.putNumber("gyro", g.getAngle());



            SmartDashboard.putNumber("LeftSonar", ElectronicsMap.leftSonar.getInches());
            SmartDashboard.putNumber("RightSonar", ElectronicsMap.rightSonar.getInches());
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }


    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {

        LiveWindow.run();
    }
}

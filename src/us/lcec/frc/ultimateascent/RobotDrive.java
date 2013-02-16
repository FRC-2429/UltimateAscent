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
//    CANJaguar arm1;
//    CANJaguar arm2;
    
    SendableChooser driveMethodChooser;

    public RobotDrive() {
        try {
            leftOne = new CANJaguar(ElectronicsMap.leftOneDrive);
            leftTwo = new CANJaguar(ElectronicsMap.leftTwoDrive);

            rightOne = new CANJaguar(ElectronicsMap.rightOneDrive);
            rightTwo = new CANJaguar(ElectronicsMap.rightTwoDrive);
            
            
            CANJaguar.NeutralMode mode = CANJaguar.NeutralMode.kBrake;
            leftOne.configNeutralMode(mode);
            leftTwo.configNeutralMode(mode);
            rightOne.configNeutralMode(mode);
            rightTwo.configNeutralMode(mode);
            
            
            
//            arm1 = new CANJaguar(11);
//            arm1.configNeutralMode(CANJaguar.NeutralMode.kBrake);
//            arm2 = new CANJaguar(4);
//            arm2.configNeutralMode(CANJaguar.NeutralMode.kBrake);
            ElectronicsMap.recorder.addMotor(leftOne, ElectronicsMap.leftOneDrive);
            ElectronicsMap.recorder.addMotor(leftTwo, ElectronicsMap.leftTwoDrive);
            
            ElectronicsMap.recorder.addMotor(rightOne, ElectronicsMap.rightOneDrive);
            ElectronicsMap.recorder.addMotor(rightTwo, ElectronicsMap.rightTwoDrive);
            
            
            LiveWindow.addActuator("RobotDrive", "leftOne", leftOne);
            LiveWindow.addActuator("RobotDrive", "leftTwo", leftTwo);

            LiveWindow.addActuator("RobotDrive", "rightOne", rightOne);
            LiveWindow.addActuator("RobotDrive", "rightTwo", rightTwo);
            
//            LiveWindow.addActuator("Arm", "arm1", arm1);
//            LiveWindow.addActuator("Arm", "arm2", arm2);
            

            
            




            driveMethodChooser = new SendableChooser();
            driveMethodChooser.addDefault("Shane Drive", new ShaneDrive());
            driveMethodChooser.addObject("Ethan Drive", new EthanDrive());
            driveMethodChooser.addObject("New Shane Drive", new NewShaneDrive());
            SmartDashboard.putData("DriveMethod", driveMethodChooser);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }



    }

    void set(double forward, double rotation) {
        try {
            double left = forward + rotation;
            double right = forward - rotation;



            double maximum = Math.max(Math.abs(left), Math.abs(right));
            if (maximum > 1) {
                left /= maximum;
                right /= maximum;
            }

            leftOne.setX(left);
            leftTwo.setX(left);
            rightOne.setX(-right);
            rightTwo.setX(-right);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    void update() {




        DriveControlMethod methodToDrive = (DriveControlMethod) driveMethodChooser.getSelected();

        double forward = methodToDrive.getForward();
        double rotation = methodToDrive.getRotation();

        if (methodToDrive instanceof NewShaneDrive) {
            try {
                leftOne.setX(forward);
                leftTwo.setX(forward);
                rightOne.setX(-rotation);
                rightTwo.setX(-rotation);
            } catch (CANTimeoutException ex) {
                ex.printStackTrace();
            }
        } else {
            set(forward, rotation);
        }

    
    }
}

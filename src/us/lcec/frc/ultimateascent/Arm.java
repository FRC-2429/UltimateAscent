package us.lcec.frc.ultimateascent;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Arm {
    CANJaguar arm1;
    CANJaguar arm2;
    CANJaguar rotate;
    boolean front,back;
    
    public Arm(){
        try {
            arm1 = new CANJaguar(ElectronicsMap.armOne);
            arm2 = new CANJaguar(ElectronicsMap.armTwo);
            arm1.configNeutralMode(CANJaguar.NeutralMode.kBrake);
            arm2.configNeutralMode(CANJaguar.NeutralMode.kBrake);
            arm1.configNeutralMode(CANJaguar.NeutralMode.kCoast);
            arm2.configNeutralMode(CANJaguar.NeutralMode.kCoast);
            rotate = new CANJaguar(ElectronicsMap.armRotate);
            
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        System.out.println("go arm");
        front=true;
        back=true;
        LiveWindow.addActuator("Arm", "arm1", arm1);
        LiveWindow.addActuator("Arm", "arm2", arm2);

    }
    
    public void set(double x,double r){
        if(Math.abs(x)>1) {
            x=x/Math.abs(x);
        }
        try {
            arm1.setX(x);
//            arm1.setX(0);
            arm2.setX(x);
            rotate.setX(r);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            try {
                System.out.println(arm1.getOutputCurrent());
                System.out.println(arm2.getOutputCurrent());
            } catch (CANTimeoutException ex1) {
                ex1.printStackTrace();
            }
        }
        update();
    }
    
    public void update(){
        
//        try {
//            front = arm1.getForwardLimitOK();
//            back=arm1.getReverseLimitOK();
//        } catch (CANTimeoutException ex) {
//            ex.printStackTrace();
//        }
//        if(front&&back)
//            return;
//        else
//            try {
//            arm1.setX(0);
//            arm2.setX(0);
//        } catch (CANTimeoutException ex) {
//            ex.printStackTrace();
//        }
    }
}

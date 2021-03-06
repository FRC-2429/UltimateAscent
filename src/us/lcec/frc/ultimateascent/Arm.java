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
           
           
            rotate = new CANJaguar(ElectronicsMap.armRotate);
            rotate.configNeutralMode(CANJaguar.NeutralMode.kBrake);
            
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
            System.out.println(arm1.getOutputCurrent()+"a "+arm2.getOutputCurrent()+"a");
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            try {
                System.out.println(arm1.getOutputCurrent());
                System.out.println(arm2.getOutputCurrent());
            } catch (CANTimeoutException ex1) {
                ex1.printStackTrace();
            }
        }
        
        
        limitCheck();
        
    }
    
    public void limitCheck()
    {
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
    
    
    Runnable bigAction =  new Runnable() {

            public void run() {
                System.out.println("StartAuto");
                manualControl = false;
                set(0,.5);
                
                myQueue.addTask(new TimeSince(3000), new Runnable() {

                    public void run() {
                        set(0,0);
                        reset();
                    }
                });
                
            }
    };
    
    
    void singleTurn(int direction)
    {
        manualControl = false;
        set(direction * .5,0);
        System.out.println(direction);
        myQueue.addTask(new TimeSince(100), new Runnable() {

            public void run() {
                set(0,0);
                reset();
            }
        });
        
    }
    
    ActionQueue myQueue = new ActionQueue();
    boolean manualControl = true;
    
    public void reset()
    {
        manualControl = true;
        myQueue.clear();
        myQueue.addTask(new TillButton(ElectronicsMap.xbox, 2), bigAction);
        myQueue.addTask(new TillButton(ElectronicsMap.joy1, 6), new Runnable() {

            public void run() {
                singleTurn(1);
            }
        });
        
        myQueue.addTask(new TillButton(ElectronicsMap.joy1, 7), new Runnable() {

            public void run() {
                singleTurn(-1);
            }
        });
    }
    
    public void update(){
        
        
        
        myQueue.update();
        
        
        if (manualControl)
        {
        double ramp = ElectronicsMap.joy1.getZ();
        ramp = (ramp +1.0)/2.0;
        double arm = ElectronicsMap.xbox.getZ();
        double rotateInput=ElectronicsMap.xbox.getY();
       
        
            set(arm*ramp,rotateInput*ramp);
            System.out.print("im running"+" "+ramp+" "+ramp*arm+" "+arm+" ");
            try {
                System.out.println(arm1.getOutputCurrent()+"a "+arm2.getOutputCurrent()+"a");
            } catch (CANTimeoutException ex) {
                ex.printStackTrace();
            }
        
        }
        

    }
}

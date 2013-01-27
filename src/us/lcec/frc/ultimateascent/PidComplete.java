/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package us.lcec.frc.ultimateascent;

import edu.wpi.first.wpilibj.PIDController;

/**
 *
 * @author Administrator
 */
public class PidComplete implements BooleanInter{

    PIDController controller;
    
    public PidComplete(PIDController controller) {
        this.controller = controller;
    }

    
    
    
    public boolean run() {
        return controller.onTarget();
    }
    
}

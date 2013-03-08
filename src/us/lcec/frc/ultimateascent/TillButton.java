/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package us.lcec.frc.ultimateascent;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Admin
 */
public class TillButton implements  BooleanInter{

    Joystick stick;
    int buttonId;

    public TillButton(Joystick stick, int buttonId) {
        this.stick = stick;
        this.buttonId = buttonId;
    }
    
    
    
    public boolean run() {
        return stick.getRawButton(buttonId);
    }
    
}

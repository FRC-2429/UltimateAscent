/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package us.lcec.frc.ultimateascent;

/**
 *
 * @author Admin
 */
public class TimeSince implements BooleanInter{

    long targetTime;

    public TimeSince(long targetTime) {
        this.targetTime = targetTime + System.currentTimeMillis();
    }
    
    
    
    public boolean run() {
        return System.currentTimeMillis() > targetTime;
    }
    
}

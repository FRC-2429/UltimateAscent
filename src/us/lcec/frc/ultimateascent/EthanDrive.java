/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package us.lcec.frc.ultimateascent;

/**
 *
 * @author ethan_000
 */
public class EthanDrive implements DriveControlMethod {

    public double getForward() {
        return -ElectronicsMap.joy1.getY();
    }

    public double getRotation() {
        return ElectronicsMap.joy1.getX();
    }
    
}

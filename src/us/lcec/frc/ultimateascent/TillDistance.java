/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package us.lcec.frc.ultimateascent;

/**
 *
 * @author Admin
 */
public class TillDistance implements BooleanInter{

    MaxbotixSonar sonar;
    double distance;

    public TillDistance(MaxbotixSonar sonar, double distance) {
        this.sonar = sonar;
        this.distance = distance;
    }
    
    
    
    public boolean run() {
        
        return sonar.getInches() < distance;
    }
    
    
    
}

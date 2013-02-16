/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package us.lcec.frc.ultimateascent;

import com.sun.squawk.microedition.io.FileConnection;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.microedition.io.Connector;

/**
 *
 * @author Admin
 */
public class Player {
 
    
    
    
    
    void setFile(String file)
    {
        fileName = file;
    }
    
    
    void startPlaying()
    {
        try {
            playing = true;
            lastTime = System.currentTimeMillis();
            
            file = (FileConnection) Connector.open("file:///records/" + fileName);
            
            if (!file.exists())
                throw new RuntimeException("File does not exist");
            
            
            
            fileIn = file.openDataInputStream();
            
           
            
            System.out.println(file.getPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    void addMotor(Jaguar s){
    
        motors.put(Integer.valueOf(s.getChannel()), s);
  
    
        
    }
    
    
    void update()
    {
        
        if(playing && System.currentTimeMillis() > lastTime)
        {
            
            while(true)
            {
                try {
                    byte motor = fileIn.readByte();
                    
                    if (motor == -2)
                    {
                        playing = false;
                        break;
                    }
                    else if (motor == -1)
                    {
                        long delta = fileIn.readLong();
                       
                        lastTime += delta;
                        break;
                    }
                    else
                    {
                        double value = fileIn.readDouble();
                        
                         SpeedController s = (SpeedController) motors.get(Integer.valueOf(motor));
                         s.set(value);
                         
                         
                        
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            
            
            
       
        }
                           
        
        
    }
    
    
    void stop()
    {
        try {
            playing = false;
            
           
            
            fileIn.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
    }
    
    
    
    private String fileName;
    private FileConnection file;
    private DataInputStream fileIn;
    
    boolean playing = false;
    long lastTime;
    
    Hashtable motors = new Hashtable();

    void addMotor(CANJaguar s, int id) {
         motors.put(Integer.valueOf(id), s);
    }
}

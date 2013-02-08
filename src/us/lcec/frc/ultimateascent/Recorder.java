package us.lcec.frc.ultimateascent;

import com.sun.squawk.microedition.io.FileConnection;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.io.Connector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Recorder {

    public Recorder() {
    
        
        
    
    }
    
    
    
    
    void setFile(String file)
    {
        fileName = file;
    }
    
    
    void startRecordoing()
    {
        try {
            recording = true;
            lastTime = System.currentTimeMillis();
            
            file = (FileConnection) Connector.open("file:///records/" + fileName);
            
            if (file.exists())
            {
                file.delete();
            }
            
            file.create();
            
            fileOut = file.openDataOutputStream();
            
           
            
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
        long deltaTime = System.currentTimeMillis() -lastTime;
        if(recording && deltaTime > 10)
        {
            try {
                Enumeration motorNumbers = motors.keys();
                
                while (motorNumbers.hasMoreElements())
                {
                    
                        try {
                            Integer id = (Integer) motorNumbers.nextElement();
                            SpeedController s = (SpeedController) motors.get(id);
                            
                            fileOut.writeByte(id.byteValue());
                            fileOut.writeDouble(s.get());
                            
                           
                            
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                      
                        
                    
                    
                }
                
                fileOut.writeByte(-1);
                fileOut.writeLong(deltaTime);
                
                lastTime = System.currentTimeMillis();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
                           
        
        
    }
    
    
    void stop()
    {
        try {
            recording = false;
            
            fileOut.writeByte(-2);
            
            fileOut.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
    }
    
    
    
    private String fileName;
    private FileConnection file;
    private DataOutputStream fileOut;
    
    boolean recording = false;
    long lastTime;
    
    Hashtable motors = new Hashtable();

    void addMotor(CANJaguar s, int id) {
         motors.put(Integer.valueOf(id), s);
    }
    
    
    
    
    
    
}

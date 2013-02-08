package us.lcec.frc.ultimateascent;


import com.sun.squawk.microedition.io.FileConnection;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import java.io.IOException;
import java.util.Random;
import javax.microedition.io.Connection;
import javax.microedition.io.Connector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ethan_000
 */
public class RecordingCrio implements NamedSendable, ITableListener{

    ITable table;
    
    Recorder rec = new Recorder();
    Player play = new Player();
    
    
     void addMotor(Jaguar s){
    
        
       rec.addMotor(s);
       play.addMotor(s);
    
        
    }
     
     void addMotor(CANJaguar s, int id){
    
        
       rec.addMotor(s,id);
       play.addMotor(s,id);
    
        
    }
     
     void update()
     {
         rec.update();
         play.update();
     }
    
    
    
    public String getName() {
        return "RecordingThingy";
    }

    
    public void initTable(ITable itable) {
       
       
       this.table = itable;
       if (table != null)
       {
           table.addTableListener(this);
           table.putString("FileStatus","NotChecked");
           table.putString("File","exampleFileName.txt");
           table.putString("Status", "waiting");
           
           
       }
    }

 
    public ITable getTable() {
        return table;
    }

   
    public String getSmartDashboardType() {
        return "RecordingType";
    }

    

    boolean checkFile(String file)
    {
        try {
           boolean result;
          FileConnection fileConn = (FileConnection) Connector.open("file:///records/" + file);
          result = fileConn.exists();
          fileConn.close();
          return result;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
          
    }
    
    
    
  
    public void valueChanged(ITable itable, String string, Object o, boolean bln) {
        if (string.equals("Command"))
        {
            String typeOfCommand = (String) o;
            if (typeOfCommand.equals("Stop")){
                    table.putString("Status", "Waiting");
                    if (rec.recording)
                    rec.stop();
                    if(play.playing)
                    play.stop();}
                    
                    
            if (typeOfCommand.equals("Record")){        
                   table.putString("Status", "Recording");  
                   rec.startRecordoing();}
                    
             if (typeOfCommand.equals("Play")) {
                   table.putString("Status", "Playing");
                   play.startPlaying();}
                   
            
        }
        else if (string.equals("File"))
        {
            
            String fileName = (String) o;
            
            if (checkFile(fileName))
            {
                table.putString("FileStatus", fileName + " exists");
            }

            else
            {
                table.putString("FileStatus", fileName + " does not exist");
            }
            
            rec.setFile(fileName);
            play.setFile(fileName);
        }
    }
    
}

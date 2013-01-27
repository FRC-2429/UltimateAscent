
package us.lcec.frc.ultimateascent;

import java.util.Vector;

/**
 *
 * @author frc2429
 */
public class ActionQueue {
    Vector runnables = new Vector();
    Vector booleanInters = new Vector();
    public ActionQueue(){
    }
    
    public void addTask(BooleanInter b, Runnable r)
    {
        runnables.addElement(r);
        booleanInters.addElement(b);
    }
    
    public void update(){
        for (int i = 0 ; i< runnables.size(); i++)
        {
            Runnable r = (Runnable) runnables.elementAt(i);
            BooleanInter b = (BooleanInter) booleanInters.elementAt(i);
            
            if(b.run())
            {
                r.run();
                
                runnables.removeElementAt(i);
                booleanInters.removeElementAt(i);
                
                i--;
            }
        }
                 
    }
    
    public void clear()
    {
        runnables.removeAllElements();
        booleanInters.removeAllElements();
    }
}

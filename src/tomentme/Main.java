package tomentme;

import tomentme.GUI.*;

public class Main 
{
    public static String path;

    public static void main(String[] args) 
    {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        path = System.getProperty("user.dir");
        
        // Creates the event-dispatcher thread which runs an endless loop for performing thread-safe swing-related stuff.
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                GUI.CreateAndShowGUI();
            }
        });
    }
}

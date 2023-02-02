package tomentme;

import tomentme.GUI.*;

public class Main 
{
    public static void main(String[] args) 
    {
        // Creates the event-dispatcher thread which runs an endless loop for performing thread-safe swing-related stuff.
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                GUI.CreateAndShowGUI();
            }
        });

        // Main thread loop
        while(true)
        {

        }
    }
}
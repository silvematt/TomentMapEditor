package tomentme;

import tomentme.GUI.*;

public class Main 
{
    public static void main(String[] args) 
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                GUI.CreateAndShowGUI();
            }
        });
    }
}
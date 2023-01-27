package tomentme.GUI;

import java.awt.Dimension;
import javax.swing.*;

import tomentme.*;        


public class GUI 
{
    private static JFrame frame;


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void CreateAndShowGUI() 
    {
        //Create and set up the window.
        frame = new JFrame("Toment Map Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AddMenuBar();

        //Display the window.
        frame.setPreferredSize(new Dimension(TomentME.DEF_WINDOW_W,TomentME.DEF_WINDOW_H));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void AddMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu mFile = new JMenu("File");

        JMenuItem miNew, miOpen, miExit;
        Action miExitAction = new MenuBarActions.ExitAction();

        miNew = new JMenuItem("New Map");
        miOpen = new JMenuItem("Open Map");

        miExit = new JMenuItem(miExitAction);
        miExit.setText("Exit");

        mFile.add(miNew, 0);
        mFile.add(miOpen, 1);
        mFile.add(new JSeparator(), 2);
        mFile.add(miExit, 3);

        menuBar.add(mFile);

        frame.setJMenuBar(menuBar);
    }

    public static void Start()
    {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                CreateAndShowGUI();
            }
        });
    }
}

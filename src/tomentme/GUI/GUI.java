package tomentme.GUI;

import java.awt.*;
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
    public static void CreateAndShowGUI() 
    {
        //Create and set up the window.
        frame = new JFrame("Toment Map Editor");
        frame.setLayout(new BorderLayout(10, 5));
        frame.setPreferredSize(new Dimension(TomentME.DEF_WINDOW_W,TomentME.DEF_WINDOW_H));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TomentEditor editor = new TomentEditor();
        AddMenuBar();
        frame.add(editor);

        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    
    private static void AddMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu mFile = new JMenu("File");
        JMenu mSettings = new JMenu("Settings");
        JMenu mRun = new JMenu("Run");

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
        menuBar.add(mSettings);
        menuBar.add(mRun);

        frame.setJMenuBar(menuBar);
    }

    public static JFrame GetFrame()
    {
        return frame;
    }
}

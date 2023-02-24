package tomentme.GUI;

import java.awt.*;
import javax.swing.*;

import tomentme.*;
import tomentme.GUI.Elements.TileButton;
import tomentme.GUI.Elements.Palette.ChangeEditModeButton;
import tomentme.GUI.Elements.SelectionPanel.SelectionWallFaceButton;        

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
        frame.setPreferredSize(new Dimension(TomentEditor.DEF_WINDOW_W, TomentEditor.DEF_WINDOW_H));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Initialize
        TileButton.InitializeGUIMembers();
        ChangeEditModeButton.InitializeGUIMembers();
        SelectionWallFaceButton.InitializeGUIMembers();
        
        // Creates the editor
        TomentEditor editor = new TomentEditor();
        AddMenuBar();
        frame.add(editor);

        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    
    // Adds the toolbar
    private static void AddMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu mFile = new JMenu("File");
        JMenu mSettings = new JMenu("Settings");
        JMenu mRun = new JMenu("Run");

        // Items
        JMenuItem miNew, miOpen, miSave, miSaveAs, miExit;

        // Menu Actions
        Action miNewMapAction = new MenuBarActions.NewMapAction();
        Action miExitAction = new MenuBarActions.ExitAction();
        Action miOpenAction = new MenuBarActions.OpenMapAction();
        Action miSaveAction = new MenuBarActions.SaveMapAction();
        Action miSaveAsAction = new MenuBarActions.SaveAsAction();

        // Define actions
        miNew = new JMenuItem(miNewMapAction);
        miNew.setText("New Map");
        miOpen = new JMenuItem(miOpenAction);
        miOpen.setText("Open Map");
        miSave = new JMenuItem(miSaveAction);
        miSave.setText("Save");
        miSaveAs = new JMenuItem(miSaveAsAction);
        miSaveAs.setText("Save as");
        miExit = new JMenuItem(miExitAction);
        miExit.setText("Exit");

        // Add them to the JMenus
        mFile.add(miNew, 0);
        mFile.add(miOpen, 1);
        mFile.add(new JSeparator(), 2);
        mFile.add(miSave, 3);
        mFile.add(miSaveAs, 4);
        mFile.add(new JSeparator(), 5);
        mFile.add(miExit, 6);

        JMenuItem miEditorSettings, miMapSettings, miTileSetting;
        Action miSettingsAction = new MenuBarActions.OpenSettingsAction();

        miEditorSettings = new JMenuItem("Editor Settings");
        miMapSettings = new JMenuItem(miSettingsAction);;
        miMapSettings.setText("Map Settings");

        miTileSetting = new JMenuItem("Tile Settings");

        mSettings.add(miEditorSettings, 0);
        mSettings.add(new JSeparator(), 1);
        mSettings.add(miMapSettings, 2);
        mSettings.add(new JSeparator(), 3);
        mSettings.add(miTileSetting, 4);


        JMenuItem miRun;
        miRun = new JMenuItem("Run in game");
        mRun.add(miRun, 0);

        menuBar.add(mFile);
        menuBar.add(mSettings);
        menuBar.add(mRun);

        frame.setJMenuBar(menuBar);
    }

    // Gets the frame
    public static JFrame GetFrame()
    {
        return frame;
    }
}

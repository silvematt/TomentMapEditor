package tomentme.GUI;

import java.awt.*;
import java.security.PublicKey;

import javax.swing.*;
import javax.swing.border.Border;

import tomentme.*;        
import tomentme.GUI.Elements.*;


public class GUI 
{
    private static JFrame frame;

    public static Border notSelectedBorder;
    public static Border selectedBorder;
    public static Border hoverBorder;

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

        AddMenuBar();
        AddMainContent();

        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void InitializeGUIMembers()
    {
        notSelectedBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1, false);
        selectedBorder = BorderFactory.createLineBorder(Color.YELLOW, 1, false);
        hoverBorder = BorderFactory.createLineBorder(Color.CYAN, 1, false);
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

    private static void AddMainContent()
    {
        JPanel content = new JPanel(new BorderLayout());

        JPanel toolSections = new JPanel();
        toolSections.setLayout(new BoxLayout(toolSections, BoxLayout.Y_AXIS));
        toolSections.setBackground(Color.red);
        
        // COMMANDS PANEL START
        JPanel commandsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        commandsPanel.setBackground(Color.black);

        JPanel incLeftPanel = new JPanel();
        incLeftPanel.setLayout(new GridLayout(3,1));
        incLeftPanel.setPreferredSize(new Dimension(60, 80));

        JLabel curFloorText = new JLabel("Floor: 0");
        curFloorText.setHorizontalAlignment(JLabel.CENTER);
        incLeftPanel.add(curFloorText);

        JButton upLvlButton = new JButton("▲");
        incLeftPanel.add(upLvlButton);
        
        JButton downLvlButton = new JButton("▼");
        incLeftPanel.add(downLvlButton);

        commandsPanel.add(incLeftPanel);
        
        JPanel incRightPanel = new JPanel();
        incRightPanel.setBackground(Color.blue);
        incRightPanel.setPreferredSize(new Dimension(150, 100));
        commandsPanel.add(incRightPanel);

        toolSections.add(commandsPanel);
        // COMMANDS PANEL END
        

        JPanel palettePanel = new JPanel();
        palettePanel.setBackground(Color.GREEN);
        palettePanel.setPreferredSize(new Dimension(150, 400));

        JPanel paletteSelection = new JPanel();
        palettePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel psLabel = new JLabel("Palette: ");
        paletteSelection.add(psLabel);

        JPanel paletteButtons = new JPanel();
        
        JButton psBTextures = new JButton("TEXTURES");
        psBTextures.setBorder(null);
        psBTextures.setPreferredSize(new Dimension(60, 20));
        paletteButtons.add(psBTextures);

        JButton psBObjects = new JButton("OBJECTS");
        psBObjects.setBorder(null);
        psBObjects.setPreferredSize(new Dimension(60, 20));
        paletteButtons.add(psBObjects);

        JButton psBAI = new JButton("AI");
        psBAI.setBorder(null);
        psBAI.setPreferredSize(new Dimension(30, 20));
        paletteButtons.add(psBAI);

        JButton psBFloorCeiling = new JButton("F/C");
        psBFloorCeiling.setBorder(null);
        psBFloorCeiling.setPreferredSize(new Dimension(30, 20));
        paletteButtons.add(psBFloorCeiling);
    
        
        palettePanel.add(paletteSelection);
        palettePanel.add(paletteButtons);
        

        toolSections.add(palettePanel);

        JPanel viewerPanel = new JPanel();
        viewerPanel.setBackground(Color.CYAN);
        viewerPanel.setPreferredSize(new Dimension(150, 200));
        toolSections.add(viewerPanel);

        JPanel selectedFacePanel = new JPanel();
        selectedFacePanel.setBackground(Color.YELLOW);
        selectedFacePanel.setPreferredSize(new Dimension(150, 200));
        toolSections.add(selectedFacePanel);

        JPanel viewport = new JPanel(new GridLayout(24, 24));
        viewport.setBackground(Color.GRAY);


        for(int i = 0; i < 24; i++)
            for(int j = 0; j < 24; j++)
            {
                TileButton btn = new TileButton();
                btn.setBackground(Color.gray);
                viewport.add(btn);
            }


        content.add(toolSections, BorderLayout.EAST);
        content.add(viewport);

        frame.add(content);
    }

    public static JFrame GetFrame()
    {
        return frame;
    }
}

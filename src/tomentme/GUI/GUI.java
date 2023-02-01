package tomentme.GUI;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import tomentme.*;        
import tomentme.GUI.Elements.*;

import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

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

        InitializeGUIMembers();
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
        hoverBorder = BorderFactory.createLineBorder(Color.YELLOW, 1, false);
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
        palettePanel.setPreferredSize(new Dimension(150, 240));

        JPanel paletteSelection = new JPanel();
        paletteSelection.setLayout(new FlowLayout(FlowLayout.LEFT));
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
        
        JPanel psContent = new JPanel();
        psContent.setLayout(new BorderLayout());
        psContent.setBackground(Color.yellow);
        
        JPanel scrollContentPanel = new JPanel(new GridLayout(0,2));
        JScrollPane scrollFrame = new JScrollPane(scrollContentPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFrame.setPreferredSize(new Dimension( 210,140));
        scrollFrame.setAutoscrolls(true);

        // Fill items
        for(int i = 0; i < 5; i++)
        {
            JPanel objContainer = new JPanel();
            objContainer.setBackground(Color.gray);
            JLabel objLabel = new JLabel("ItemID");
            objLabel.setPreferredSize(new Dimension(90, 60));
            objContainer.add(objLabel);
            scrollContentPanel.add(objContainer);
        }

        psContent.add(scrollFrame);
        toolSections.add(paletteSelection);
        palettePanel.add(paletteButtons);
        palettePanel.add(psContent);
        toolSections.add(palettePanel);

        JPanel viewerPanel = new JPanel();
        viewerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        viewerPanel.setBackground(Color.CYAN);
        
        JPanel viewerPanelSelection = new JPanel();
        viewerPanelSelection.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel vLabel = new JLabel("Viewer: ");
        viewerPanelSelection.add(vLabel);

        toolSections.add(viewerPanelSelection);

        JPanel viewerContentPanel = new JPanel();
        viewerContentPanel.setLayout(new BoxLayout(viewerContentPanel, BoxLayout.Y_AXIS));

        viewerContentPanel.add(new JLabel("Selected Tile: (0,0)"));
        viewerContentPanel.add(new JLabel("Wall Type: None"));
        viewerContentPanel.add(new JLabel("Load Preset"));
        viewerContentPanel.add(new JLabel("Wall Data: NULL"));

        viewerPanel.add(viewerContentPanel);
        toolSections.add(viewerPanel);

        JPanel selectedFacePanel = new JPanel();
        selectedFacePanel.setLayout(new FlowLayout());
        selectedFacePanel.setBackground(Color.YELLOW);

        JPanel selectedFaceSelection = new JPanel();
        selectedFaceSelection.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel sfLabel = new JLabel("Selected Info: ");
        selectedFaceSelection.add(sfLabel);
        toolSections.add(selectedFaceSelection);

        BufferedImage myPicture;
        try 
        {
            myPicture = ImageIO.read(new File("C:/Users/silve/Desktop/315424480_3536606013292959_305079958389972222_n.jpg"));
            ImageIcon img = new ImageIcon(myPicture);
            Image newimg = img.getImage().getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            JLabel picLabel = new JLabel(new ImageIcon(newimg));
            picLabel.setPreferredSize(new Dimension(64,64));
            selectedFacePanel.add(picLabel, BorderLayout.LINE_START);

        } catch (IOException e) 
        {
            e.printStackTrace();
        }

        JPanel sbtnPnl = new JPanel();
        JButton sbtn = new JButton("Select");
        sbtn.setBorder(null);
        sbtn.setPreferredSize(new Dimension(100,30));
        sbtnPnl.add(sbtn);

        selectedFacePanel.add(sbtnPnl);

        JPanel pnl = new JPanel();
        pnl.setLayout(new GridLayout(0, 1));

        JButton TOPBtn = new JButton("TOP");
        JButton BOTTOMBtn = new JButton("BOTTOM");
        JButton LEFTBtn = new JButton("LEFT");
        JButton RIGHTBtn = new JButton("RIGHT");
        JButton FORWARDBtn = new JButton("FORWARD");
        JButton BACKBtn = new JButton("BACK");

        TOPBtn.setPreferredSize(new Dimension(60, 15));
        BOTTOMBtn.setPreferredSize(new Dimension(60, 15));
        LEFTBtn.setPreferredSize(new Dimension(60, 15));
        RIGHTBtn.setPreferredSize(new Dimension(60, 15));
        FORWARDBtn.setPreferredSize(new Dimension(60, 15));
        BACKBtn.setPreferredSize(new Dimension(60, 15));

        TOPBtn.setBorder(null);
        BOTTOMBtn.setBorder(null);
        LEFTBtn.setBorder(null);
        RIGHTBtn.setBorder(null);
        FORWARDBtn.setBorder(null);
        BACKBtn.setBorder(null);

        TOPBtn.setFont(new Font("Arial", Font.PLAIN, 10));
        BOTTOMBtn.setFont(new Font("Arial", Font.PLAIN, 10));
        LEFTBtn.setFont(new Font("Arial", Font.PLAIN, 10));
        RIGHTBtn.setFont(new Font("Arial", Font.PLAIN, 10));
        FORWARDBtn.setFont(new Font("Arial", Font.PLAIN, 10));
        BACKBtn.setFont(new Font("Arial", Font.PLAIN, 10));


        pnl.add(TOPBtn);
        pnl.add(BOTTOMBtn);
        pnl.add(LEFTBtn);
        pnl.add(RIGHTBtn);
        pnl.add(FORWARDBtn);
        pnl.add(BACKBtn);


        selectedFacePanel.add(pnl, BorderLayout.LINE_END);

        toolSections.add(selectedFacePanel);

        JPanel viewport = new JPanel(new GridLayout(24, 24));
        viewport.setBackground(Color.GRAY);

        for(int i = 0; i < 24; i++)
            for(int j = 0; j < 24; j++)
            {
                TileButton btn = new TileButton();
                btn.setBackground(Color.gray);
                btn.setBorder(notSelectedBorder);
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

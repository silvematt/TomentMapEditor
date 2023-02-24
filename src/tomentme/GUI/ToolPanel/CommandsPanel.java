package tomentme.GUI.ToolPanel;

import java.awt.*;
import javax.swing.*;

import tomentme.TomentEditor;
import tomentme.GUI.Elements.CommandsPanel.*;
import tomentme.Map.WallObject;
import tomentme.TomentEditor.EditMode;
import java.awt.event.ActionListener;
import java.awt.event.*;

/*
 * Represent the commands panel located in the ToolsPanel
 */
public class CommandsPanel 
{
    // Labels
    private JLabel curFloorText;
    private JLabel curMode;
    private JLabel curPaletteItem;

    // Status
    public boolean isCopying;
    public WallObject copiedWall;
    public int copiedInt;

    // Constructor
    public CommandsPanel(JPanel toolSections)
    {
        // Create all the elements
        JPanel commandsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        commandsPanel.setBackground(Color.GRAY);

        // Ordering panel
        JPanel incLeftPanel = new JPanel();
        incLeftPanel.setLayout(new GridLayout(3,1));
        incLeftPanel.setPreferredSize(new Dimension(60, 80));

        // Floor naviagtion
        curFloorText = new JLabel("Floor: 0");
        curFloorText.setHorizontalAlignment(JLabel.CENTER);
        incLeftPanel.add(curFloorText);

        CommandUpLevelButton upLvlButton = new CommandUpLevelButton();
        incLeftPanel.add(upLvlButton);
        
        CommandDownLevelButton downLvlButton = new CommandDownLevelButton();
        incLeftPanel.add(downLvlButton);

        commandsPanel.add(incLeftPanel);
        
        // Right panel
        JPanel incRightPanel = new JPanel();
        incRightPanel.setBackground(Color.white);
        incRightPanel.setPreferredSize(new Dimension(150, 100));
        
        // Display info
        curMode = new JLabel("Current mode: WALL");
        curPaletteItem = new JLabel("Seleted Item: NULL");

        // Commands panel's buttons
        JPanel buttonsPanel = new JPanel();
        JButton copyButton = new JButton("Copy");
        copyButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                TomentEditor.instance.CopySelectedTile();
            }
        });        

        JButton previousBtn = new JButton("<-");
        previousBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                TomentEditor.instance.RestoreToPrevious();
            }
        });        


        buttonsPanel.add(copyButton);
        buttonsPanel.add(previousBtn);

        incRightPanel.add(curMode);
        incRightPanel.add(curPaletteItem);
        incRightPanel.add(buttonsPanel);

        commandsPanel.add(incRightPanel);
        
        toolSections.add(commandsPanel);
    }

    // Updates the floor text
    public void SetCurFloorText(String str)
    {
        curFloorText.setText(str);
    }

    // Sets the current mode text
    public void SetCurModeText(EditMode mode)
    {
        switch(mode)
        {
            case WALL:
                curMode.setText("Current mode: WALL");
                break;

            case AI:
                curMode.setText("Current mode: AI");
                break;

            case FLOOR_CEILING:
                curMode.setText("Current mode: F/C");
                break;

            case SPRITE:
                curMode.setText("Current mode: SPRITE");
                break;

            default:
                curMode.setText("Current mode: WALL");
                break;

        }
    }

    // Sets the selected item text
    public void SetCurSelectedItem(String str)
    {
        curPaletteItem.setText("Selected Item: " + str);
    }

    // Sets the is copying text
    public void SetIsCopying(boolean val)
    {
        isCopying = val;

        if(isCopying)
            curPaletteItem.setText("COPYING!");
    }

    public boolean IsCopying()
    {
        return isCopying;
    }
}

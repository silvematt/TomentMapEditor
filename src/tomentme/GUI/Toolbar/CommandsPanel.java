package tomentme.GUI.Toolbar;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

import javax.swing.*;

import tomentme.TomentEditor;
import tomentme.GUI.Elements.CommandsPanel.CommandDownLevelButton;
import tomentme.GUI.Elements.CommandsPanel.CommandUpLevelButton;

public class CommandsPanel 
{
    private JLabel curFloorText;

    public CommandsPanel(JPanel toolSections)
    {
        JPanel commandsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        commandsPanel.setBackground(Color.black);

        JPanel incLeftPanel = new JPanel();
        incLeftPanel.setLayout(new GridLayout(3,1));
        incLeftPanel.setPreferredSize(new Dimension(60, 80));

        curFloorText = new JLabel("Floor: 0");
        curFloorText.setHorizontalAlignment(JLabel.CENTER);
        incLeftPanel.add(curFloorText);

        CommandUpLevelButton upLvlButton = new CommandUpLevelButton();
        incLeftPanel.add(upLvlButton);
        
        CommandDownLevelButton downLvlButton = new CommandDownLevelButton();
        incLeftPanel.add(downLvlButton);

        commandsPanel.add(incLeftPanel);
        
        JPanel incRightPanel = new JPanel();
        incRightPanel.setBackground(Color.blue);
        incRightPanel.setPreferredSize(new Dimension(150, 100));
        commandsPanel.add(incRightPanel);

        toolSections.add(commandsPanel);
    }

    public void SetCurFloorText(String str)
    {
        curFloorText.setText(str);
    }
}

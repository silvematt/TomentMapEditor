package tomentme.GUI.Toolbar;

import java.awt.*;
import javax.swing.*;

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
    }

    public void SetCurFloorText(String str)
    {
        curFloorText.setText(str);
    }
}

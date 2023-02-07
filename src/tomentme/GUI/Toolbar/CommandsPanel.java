package tomentme.GUI.Toolbar;

import java.awt.*;
import javax.swing.*;

import tomentme.GUI.Elements.CommandsPanel.*;
import tomentme.TomentEditor.EditMode;

public class CommandsPanel 
{
    private JLabel curFloorText;

    private JLabel curMode;

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
        incRightPanel.setBackground(Color.gray);
        incRightPanel.setPreferredSize(new Dimension(150, 100));
        
        curMode = new JLabel("Current mode: WALL");
        incRightPanel.add(curMode);
        commandsPanel.add(incRightPanel);
        
        toolSections.add(commandsPanel);
    }

    public void SetCurFloorText(String str)
    {
        curFloorText.setText(str);
    }

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
}

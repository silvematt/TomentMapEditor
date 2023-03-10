package tomentme.GUI.Elements.CommandsPanel;

import javax.swing.JButton;

import tomentme.TomentEditor;

import java.awt.event.ActionListener;
import java.awt.event.*;


/*
 * Commands button that allows to go down a level
 */
public class CommandDownLevelButton extends JButton
{
    // Constructor
    public CommandDownLevelButton()
    {
        this.setText("▼");
        this.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                TomentEditor.instance.SetCurrentFloor(TomentEditor.instance.GetCurrentFloor()-1);
                TomentEditor.instance.GetViewport().UpdateViewport();
            }
        });
    }
}

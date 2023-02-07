package tomentme.GUI.Toolbar.Elements;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.*;

import tomentme.TomentEditor;
import tomentme.GUI.Elements.TileButton;
import tomentme.TomentEditor.EditMode;

public class ChangeEditModeButton extends JButton
{
    private EditMode modeToSelect;

    public ChangeEditModeButton(String text, EditMode mode)
    {
        this.setText(text);
        this.modeToSelect = mode;
        this.setBorder(null);

        this.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                TomentEditor.instance.SetMode(modeToSelect);
            }
        });
    }
}

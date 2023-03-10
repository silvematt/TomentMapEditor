package tomentme.GUI.Elements.Palette;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.*;

import tomentme.TomentEditor;
import tomentme.TomentEditor.EditMode;

/*
 * Palette button that allows to change the edit mode
 */
public class ChangeEditModeButton extends JButton
{
    private EditMode modeToSelect;

    // Tile Button Borders
    public static Border notSelectedBorder;
    public static Border selectedBorder;
    
    // Initializes the static members
    public static void InitializeGUIMembers()
    {
        notSelectedBorder = BorderFactory.createLineBorder(Color.BLACK, 1, false);
        selectedBorder = BorderFactory.createLineBorder(Color.RED, 3, false);
    }

    // Constructor, create button and listener
    public ChangeEditModeButton(String text, EditMode mode)
    {
        this.setText(text);
        this.modeToSelect = mode;
        this.setBorder(notSelectedBorder);

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

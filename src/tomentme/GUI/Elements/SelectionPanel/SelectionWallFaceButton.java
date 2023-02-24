package tomentme.GUI.Elements.SelectionPanel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import tomentme.GUI.ToolPanel.SelectionPanel;

import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;

/*
 * Represent one of the six buttons that allows to change the selected face of a wall.
 */
public class SelectionWallFaceButton extends JButton
{
    // Value (WallObject.TEXTUREARRAY_*)
    public int textureArrayValue;

    // Tile Button Borders
    public static Border notSelectedBorder;
    public static Border selectedBorder;
    
    // Initializes the static members
    public static void InitializeGUIMembers()
    {
        notSelectedBorder = BorderFactory.createLineBorder(Color.BLACK, 1, false);
        selectedBorder = BorderFactory.createLineBorder(Color.RED, 1, false);
    }

    // Constructor
    public SelectionWallFaceButton(SelectionPanel panel, String text, int textureValue)
    {
        this.setText(text);
        this.textureArrayValue = textureValue;
        this.setBorder(notSelectedBorder);
        this.setPreferredSize(new Dimension(60, 15));
        this.setFont(new Font("Arial", Font.PLAIN, 10));
        
        this.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                panel.Wall_ChangeTextureArray((SelectionWallFaceButton)e.getSource());
            }
        });
    }
}

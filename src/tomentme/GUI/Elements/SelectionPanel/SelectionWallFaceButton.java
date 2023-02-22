package tomentme.GUI.Elements.SelectionPanel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import tomentme.GUI.ToolPanel.SelectionPanel;

import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;

public class SelectionWallFaceButton extends JButton
{
    public int textureArrayValue;

    // Tile Button Borders
    public static Border notSelectedBorder;
    public static Border selectedBorder;
    
    public static void InitializeGUIMembers()
    {
        notSelectedBorder = BorderFactory.createLineBorder(Color.BLACK, 1, false);
        selectedBorder = BorderFactory.createLineBorder(Color.RED, 1, false);
    }

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

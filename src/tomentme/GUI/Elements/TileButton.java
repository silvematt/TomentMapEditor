package tomentme.GUI.Elements;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.*;
import javax.swing.event.MouseInputListener;

import tomentme.GUI.*;

public class TileButton extends JButton
{
    boolean isSelected;
    Border border;

    public TileButton()
    {
        this.addMouseListener(GetMl());
    }

    // Tile Button MoseAdapter for Border
    private static java.awt.event.MouseAdapter ml;
    private static java.awt.event.MouseAdapter GetMl()
    {
        if(ml == null)
        {
            ml = new java.awt.event.MouseAdapter()
            {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt)
                {            
                    JButton c = (JButton)evt.getComponent();                   
                    c.setBorder(GUI.hoverBorder);
                }                                      
            
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt)
                {                                      
                    JButton c = (JButton)evt.getComponent();                   
                    c.setBorder(GUI.notSelectedBorder);
                }  
            };

            return ml;
        }

        return ml;
    }
}
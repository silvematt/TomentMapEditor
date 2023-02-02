package tomentme.GUI.Elements;

import javax.swing.JButton;

import tomentme.GUI.*;
import java.awt.event.*;

public class TileButton extends JButton
{
    private int x,y;
    private boolean isSelected;


    public TileButton(int _x, int _y)
    {
        this.x = _x;
        this.y = _y;

        this.addMouseListener(GetMl());
        this.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println(x + " | " + y);
            }
        });
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
                    c.setBorder(TomentEditor.hoverBorder);
                }                                      
            
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt)
                {                                      
                    JButton c = (JButton)evt.getComponent();                   
                    c.setBorder(TomentEditor.notSelectedBorder);
                }  
            };

            return ml;
        }

        return ml;
    }

    public int GetX()
    {
        return x;
    }

    public int GetY()
    {
        return y;
    }

    public boolean IsSelected()
    {
        return isSelected;
    }
}
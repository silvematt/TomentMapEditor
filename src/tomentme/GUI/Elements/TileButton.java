package tomentme.GUI.Elements;

import javax.swing.JButton;
import javax.swing.border.Border;

import javax.swing.*;
import java.awt.event.*;

import tomentme.*;        
import java.awt.*;


public class TileButton extends JButton
{
    // Tile Button Borders
    public static Border notSelectedBorder;
    public static Border selectedBorder;
    public static Border hoverBorder;
    
    public static void InitializeGUIMembers()
    {
        notSelectedBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1, false);
        selectedBorder = BorderFactory.createLineBorder(Color.YELLOW, 1, false);
        hoverBorder = BorderFactory.createLineBorder(Color.WHITE, 1, false);
    }

    
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
                TomentEditor.instance.SelectTile((TileButton)e.getSource());
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
                public void mousePressed(MouseEvent e) 
                {
                    TileButton c = (TileButton)e.getComponent();     

                    if(SwingUtilities.isRightMouseButton(e))
                    {
                        TomentEditor.instance.DrawSelectedPaletteTile(c);
                    }
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt)
                {            
                    TileButton c = (TileButton)evt.getComponent();     
                    
                    if(!c.IsSelected())       
                        c.setBorder(hoverBorder);
                }                                      
            
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt)
                {                                      
                    TileButton c = (TileButton)evt.getComponent();      
                    if(!c.IsSelected())
                        c.setBorder(notSelectedBorder);
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

    public void SetSelected(boolean selected)
    {
        isSelected = selected;
    }

    public void Unselect()
    {
        setSelected(false);
        setBorder(notSelectedBorder);
    }
}
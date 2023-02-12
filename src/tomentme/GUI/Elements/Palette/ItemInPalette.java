package tomentme.GUI.Elements.Palette;

import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;

import tomentme.TomentEditor;

public class ItemInPalette extends JPanel 
{
    public enum ItemType
    {
        WALL,
        SPRITE,
        AI,
        FLOOR,
        CEILING
    };

    public ItemType iType;
    public String iName;
    public int iID;

    public ItemInPalette(ImageIcon icon, int id, String name, ItemType t)
    {
        iName = name;
        iID = id;
        iType = t;

        this.setBorder(null);

        JLabel objIcon = new JLabel(icon);
        this.add(objIcon);
        JPanel objContent = new JPanel();
        objContent.setLayout(new BoxLayout(objContent, BoxLayout.Y_AXIS));
        this.setBackground(Color.GRAY);
        JLabel objName = new JLabel(name);
        JLabel objLabel = new JLabel("ID: " + id);
        objContent.add(objLabel);
        objContent.add(objName);
        this.add(objContent);

        MouseListener ml = new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                ItemInPalette panel = (ItemInPalette)e.getSource();
                TomentEditor.instance.GetPalettePanel().SelectItemInPalette(panel);
            }
        };

        this.addMouseListener( ml );
    }

    public void Unselect()
    {
        this.setBackground(Color.GRAY);
    }

    public void Select()
    {
        this.setBackground(Color.RED);
    }

    public String GetName()
    {
        return iName;
    }
}

package tomentme.GUI.Toolbar.Elements;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ItemInPalette extends JPanel 
{
    public ItemInPalette(ImageIcon icon, String id, String name)
    {
        this.setBorder(null);

        JLabel objIcon = new JLabel(icon);
        this.add(objIcon);
        JPanel objContent = new JPanel();
        objContent.setLayout(new BoxLayout(objContent, BoxLayout.Y_AXIS));
        this.setBackground(Color.gray);
        JLabel objName = new JLabel(name);
        JLabel objLabel = new JLabel(id);
        objContent.add(objLabel);
        objContent.add(objName);
        this.add(objContent);
    }
}

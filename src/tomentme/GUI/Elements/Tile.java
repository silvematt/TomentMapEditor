package tomentme.GUI.Elements;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.*;

public class Tile extends JComponent
{
    boolean isSelected;
    Border border;

    Dimension preferredSize = null;

    public Tile()
    {
        setOpaque(true);

        border = BorderFactory.createLineBorder(Color.YELLOW);
        setBorder(border);
        setPreferredSize(new Dimension(16, 16));
        setSize(preferredSize);
    }

    public Dimension getPreferredSize() 
    {
        return super.getPreferredSize();
    }

    public void setPreferredSize(Dimension newPrefSize) 
    {
        preferredSize = newPrefSize;
        super.setPreferredSize(newPrefSize);
    }
}

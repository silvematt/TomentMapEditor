package tomentme.GUI.Toolbar;

import java.awt.*;
import javax.swing.*;

import tomentme.GUI.*;
import tomentme.GUI.Elements.*;

public class Viewport 
{
    public Viewport(JPanel content)
    {
        JPanel viewport = new JPanel(new GridLayout(24, 24));
        viewport.setBackground(Color.GRAY);

        for(int i = 0; i < 24; i++)
            for(int j = 0; j < 24; j++)
            {
                TileButton btn = new TileButton(j,i);
                btn.setBackground(Color.gray);
                btn.setBorder(TomentEditor.notSelectedBorder);
                viewport.add(btn);
            }


        content.add(viewport);
    }
}

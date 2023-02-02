package tomentme.GUI.Toolbar;

import java.awt.*;
import javax.swing.*;

public class PalettePanel 
{
    public PalettePanel(JPanel toolSections)
    {
        JPanel palettePanel = new JPanel();
        palettePanel.setBackground(Color.GREEN);
        palettePanel.setPreferredSize(new Dimension(150, 240));

        JPanel paletteSelection = new JPanel();
        paletteSelection.setLayout(new FlowLayout(FlowLayout.LEFT));
        palettePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel psLabel = new JLabel("Palette: ");
        paletteSelection.add(psLabel);

        JPanel paletteButtons = new JPanel();
        
        JButton psBTextures = new JButton("TEXTURES");
        psBTextures.setBorder(null);
        psBTextures.setPreferredSize(new Dimension(60, 20));
        paletteButtons.add(psBTextures);

        JButton psBObjects = new JButton("OBJECTS");
        psBObjects.setBorder(null);
        psBObjects.setPreferredSize(new Dimension(60, 20));
        paletteButtons.add(psBObjects);

        JButton psBAI = new JButton("AI");
        psBAI.setBorder(null);
        psBAI.setPreferredSize(new Dimension(30, 20));
        paletteButtons.add(psBAI);

        JButton psBFloorCeiling = new JButton("F/C");
        psBFloorCeiling.setBorder(null);
        psBFloorCeiling.setPreferredSize(new Dimension(30, 20));
        paletteButtons.add(psBFloorCeiling);
        
        JPanel psContent = new JPanel();
        psContent.setLayout(new BorderLayout());
        psContent.setBackground(Color.yellow);
        
        JPanel scrollContentPanel = new JPanel(new GridLayout(0,2));
        JScrollPane scrollFrame = new JScrollPane(scrollContentPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFrame.setPreferredSize(new Dimension( 210,140));
        scrollFrame.setAutoscrolls(true);

        // Fill items
        for(int i = 0; i < 5; i++)
        {
            JPanel objContainer = new JPanel();
            objContainer.setBackground(Color.gray);
            JLabel objLabel = new JLabel("ItemID");
            objLabel.setPreferredSize(new Dimension(90, 60));
            objContainer.add(objLabel);
            scrollContentPanel.add(objContainer);
        }

        psContent.add(scrollFrame);
        toolSections.add(paletteSelection);
        palettePanel.add(paletteButtons);
        palettePanel.add(psContent);
        toolSections.add(palettePanel);
    }
}

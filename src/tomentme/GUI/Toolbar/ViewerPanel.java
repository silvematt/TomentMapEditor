package tomentme.GUI.Toolbar;

import java.awt.*;
import javax.swing.*;

public class ViewerPanel 
{
    public ViewerPanel(JPanel toolSections)
    {
        JPanel viewerPanel = new JPanel();
        viewerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        viewerPanel.setBackground(Color.CYAN);
        
        JPanel viewerPanelSelection = new JPanel();
        viewerPanelSelection.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel vLabel = new JLabel("Viewer: ");
        viewerPanelSelection.add(vLabel);

        toolSections.add(viewerPanelSelection);

        JPanel viewerContentPanel = new JPanel();
        viewerContentPanel.setLayout(new BoxLayout(viewerContentPanel, BoxLayout.Y_AXIS));

        viewerContentPanel.add(new JLabel("Selected Tile: (0,0)"));
        viewerContentPanel.add(new JLabel("Wall Type: None"));
        viewerContentPanel.add(new JLabel("Load Preset"));
        viewerContentPanel.add(new JLabel("Wall Data: NULL"));

        viewerPanel.add(viewerContentPanel);
        toolSections.add(viewerPanel);
    }
}

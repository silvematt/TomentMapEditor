package tomentme.GUI.Toolbar;

import java.awt.*;
import java.util.Currency;

import javax.swing.*;

import tomentme.TomentEditor;
import tomentme.AssetsManager.AssetManager.WallAssets;
import tomentme.GUI.Elements.TileButton;
import tomentme.Map.TMap;
import tomentme.Map.WallObject;
import tomentme.TomentEditor.EditMode;

public class ViewerPanel 
{
    // Common
    private JLabel selectedTileLabel;

    // Edit: Wall
    private JLabel wallTypeLabel;
    private JLabel wallDataLabel;
    private JLabel wallLoadPreset;

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

        selectedTileLabel = new JLabel("Selected Tile: (0,0)");
        viewerContentPanel.add(selectedTileLabel);

        wallTypeLabel = new JLabel("Wall Type: None");
        wallDataLabel = new JLabel("Wall Data: None");
        wallLoadPreset = new JLabel("Load Preset:");

        viewerContentPanel.add(wallTypeLabel);
        viewerContentPanel.add(wallDataLabel);
        viewerContentPanel.add(wallLoadPreset);

        viewerPanel.add(viewerContentPanel);
        toolSections.add(viewerPanel);
    }

    public void UpdateViewer(EditMode mode, TileButton tile)
    {
        SetSelectedTileValue("("+tile.GetX()+","+tile.GetY()+")");

        TMap curMap = TomentEditor.instance.currentMap;

        switch (mode)
        {
            case AI:

                break;

            case FLOOR_CEILING:

                break;

            case SPRITE:

                break;

            case WALL:

                WallObject wallObj = null;
                switch(TomentEditor.instance.GetCurrentFloor())
                {
                    case 0:
                        wallObj = curMap.level0[tile.GetY()][tile.GetX()];
                        break;

                    case 1:
                        wallObj = curMap.level1[tile.GetY()][tile.GetX()];
                        break;

                    case 2:
                        wallObj = curMap.level2[tile.GetY()][tile.GetX()];
                        break;

                    default:
                        wallObj = curMap.level0[tile.GetY()][tile.GetX()];
                        break;
                }

                wallTypeLabel.setText("Wall Type: " + WallAssets.GetEnumName(wallObj.assetID));
                wallDataLabel.setText("Wall Data: " + wallObj.data);
                break;

            default:
                break;
            
        }
    }

    public void SetSelectedTileValue(String str)
    {
        selectedTileLabel.setText("Selected Tile: " + str);
    }
}

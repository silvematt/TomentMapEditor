package tomentme.GUI.Toolbar;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import javax.swing.*;

import tomentme.TomentEditor;
import tomentme.AssetsManager.AssetManager.SpritesAssets;
import tomentme.AssetsManager.AssetManager.WallAssets;
import tomentme.GUI.Elements.TileButton;
import tomentme.Map.TMap;
import tomentme.Map.WallObject;
import tomentme.TomentEditor.EditMode;

public class ViewerPanel 
{
    // Common
    private JPanel viewerContentPanel;
    private JLabel selectedTileLabel;

    // Edit: Wall
    private List<JComponent> wallElements = new ArrayList<>();
    private JLabel wallTypeLabel;
    private JLabel wallDataLabel;
    private JLabel wallLoadPreset;

    // Edit: Sprites
    private List<JComponent> spritesElements = new ArrayList<>();
    private JLabel spritesLabel;
    private JLabel spriteName;
    private JLabel spirteType;

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

        viewerContentPanel = new JPanel();
        viewerContentPanel.setLayout(new BoxLayout(viewerContentPanel, BoxLayout.Y_AXIS));

        selectedTileLabel = new JLabel("Selected Tile: (0,0)");
        viewerContentPanel.add(selectedTileLabel);

        FillWallElements();
        FillSpritesElements();

        viewerPanel.add(viewerContentPanel);
        toolSections.add(viewerPanel);
    }

    private void FillWallElements()
    {
        wallTypeLabel = new JLabel("Wall Type: None");
        wallDataLabel = new JLabel("Wall Data: None");
        wallLoadPreset = new JLabel("Load Preset:");

        wallElements.add(wallTypeLabel);
        wallElements.add(wallDataLabel);
        wallElements.add(wallLoadPreset);

        viewerContentPanel.add(wallTypeLabel);
        viewerContentPanel.add(wallDataLabel);
        viewerContentPanel.add(wallLoadPreset);
    }

    private void FillSpritesElements()
    {
        spritesLabel = new JLabel("Sprite ID: None");
        spriteName = new JLabel("Sprite Name: None");
        spirteType = new JLabel("Type: Dynamic");

        spritesElements.add(spritesLabel);
        spritesElements.add(spriteName);
        spritesElements.add(spirteType);

        viewerContentPanel.add(spritesLabel);
        viewerContentPanel.add(spriteName);
        viewerContentPanel.add(spirteType);
    }

    public void UpdateViewer(EditMode mode, TileButton tile)
    {
        if(tile == null)
            return;
        
        SetSelectedTileValue("("+tile.GetX()+","+tile.GetY()+")");

        TMap curMap = TomentEditor.instance.currentMap;

        switch (mode)
        {
            case AI:

                break;

            case FLOOR_CEILING:

                break;

            case SPRITE:
                for(JComponent cmp : spritesElements)
                    viewerContentPanel.add(cmp);

                for(JComponent cmp : wallElements)
                    viewerContentPanel.remove(cmp);

                int spriteObj = 0;
                switch(TomentEditor.instance.GetCurrentFloor())
                {
                    case 0:
                        spriteObj = curMap.spritesMapLevel0[tile.GetY()][tile.GetX()];
                        break;

                    case 1:
                        spriteObj = curMap.spritesMapLevel1[tile.GetY()][tile.GetX()];
                        break;

                    case 2:
                        spriteObj = curMap.spritesMapLevel2[tile.GetY()][tile.GetX()];
                        break;

                    default:
                        spriteObj = curMap.spritesMapLevel0[tile.GetY()][tile.GetX()];
                        break;
                }

                spritesLabel.setText("Sprite ID: " + spriteObj);
                spriteName.setText("Name: " + SpritesAssets.GetEnumName(spriteObj));
                break;

            case WALL:
                for(JComponent cmp : wallElements)
                    viewerContentPanel.add(cmp);

                for(JComponent cmp : spritesElements)
                    viewerContentPanel.remove(cmp);

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

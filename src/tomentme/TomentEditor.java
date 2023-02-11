package tomentme;

import javax.swing.JPanel;

import java.awt.*;

import javax.swing.*;

import tomentme.AssetsManager.AssetManager;
import tomentme.GUI.Elements.*;
import tomentme.GUI.Elements.Palette.ItemInPalette;
import tomentme.GUI.Toolbar.*;
import tomentme.Map.TMap;
import tomentme.Map.WallObject;



public class TomentEditor extends JPanel
{
    // Defines
    public static final int DEF_WINDOW_W = 800;
    public static final int DEF_WINDOW_H = 600;
    
    public static TomentEditor instance;

    // Tools
    private JPanel toolSections;

    // Elements
    private CommandsPanel commands;
    private PalettePanel palette;
    private ViewerPanel viewer;
    private SelectionPanel selection;
    private Viewport viewport;

    // Data
    public TMap currentMap;
    private int currentFloor = 0;

    // Application Logic
    private TileButton curSelectedButton;

    public TileButton GetCurrentTileButton()
    {
        return curSelectedButton;
    }

    public enum EditMode
    {
        WALL,
        SPRITE,
        FLOOR_CEILING,
        AI
    }

    private EditMode curEditMode = EditMode.WALL;

    public TomentEditor()
    {
        setLayout(new BorderLayout());

        if(instance == null)
            instance = this;

        InitializeTomentMapEditor();
    }

    private void InitializeTomentMapEditor()
    {
        toolSections = new JPanel();
        toolSections.setLayout(new BoxLayout(toolSections, BoxLayout.Y_AXIS));
        toolSections.setBackground(Color.GRAY);

        AssetManager assetManager = new AssetManager();
        AssetManager.instance = assetManager;
        assetManager.InitializeAssetManager();
        
        commands = new CommandsPanel(toolSections);
        palette = new PalettePanel(toolSections);
        viewer = new ViewerPanel(toolSections);
        selection = new SelectionPanel(toolSections);

        this.add(toolSections, BorderLayout.EAST);
        viewport = new Viewport(this);
        
        // Testing level load
        currentMap = new TMap();
        currentMap.LoadMap("lvl1");
        
        SetMode(EditMode.WALL);
        
        SelectTile(viewport.tiles[0][0]);
        viewport.UpdateViewport();
    }

    public void SelectTile(TileButton tile)
    {
        if(curSelectedButton != null)
            curSelectedButton.Unselect();

        viewer.UpdateViewer(curEditMode, tile);
        selection.UpdatePanel(curEditMode, tile);

        tile.setBorder(TileButton.selectedBorder);
        tile.SetSelected(true);

        curSelectedButton = tile;
    }

    public void DrawSelectedPaletteTile(TileButton tile)
    {
        ItemInPalette itemToDraw = palette.selectedItem;

        if(itemToDraw == null)
            return;

        TMap curMap = TomentEditor.instance.currentMap;
        
        switch(itemToDraw.iType)
        {
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

                wallObj.assetID = itemToDraw.iID;
                break;

            case AI:
            case SPRITE:
                switch(TomentEditor.instance.GetCurrentFloor())
                {
                    case 0:
                        curMap.spritesMapLevel0[tile.GetY()][tile.GetX()] = itemToDraw.iID;
                        break;

                    case 1:
                        curMap.spritesMapLevel1[tile.GetY()][tile.GetX()] = itemToDraw.iID;
                        break;

                    case 2:
                        curMap.spritesMapLevel2[tile.GetY()][tile.GetX()] = itemToDraw.iID;
                        break;

                    default:
                        curMap.spritesMapLevel0[tile.GetY()][tile.GetX()] = itemToDraw.iID;
                        break;
                }
                break;
        }
        
        UpdateAll();
    }

    // Public getters
    public CommandsPanel GetCommandsPanel()
    {
        return commands;
    }

    public PalettePanel GetPalettePanel()
    {
        return palette;
    }

    public ViewerPanel GetViewerPanel()
    {
        return viewer;
    }

    public SelectionPanel GetSelectionPanel()
    {
        return selection;
    }

    public Viewport GetViewport()
    {
        return viewport;
    }

    public int GetCurrentFloor()
    {
        return currentFloor;
    }

    public int SetCurrentFloor(int _floor)
    {
        if(_floor < 0)
            _floor = 0;

        if(_floor > 2)
            _floor = 2;

        commands.SetCurFloorText("Floor: " + _floor);
        return (currentFloor = _floor);
    }

    public void SetMode(EditMode mode)
    {
        curEditMode = mode;

        palette.UnselectItemInPalette();

        // Update all
        UpdateAll();
    }

    public void UpdateAll()
    {
        // Update all
        commands.SetCurModeText(curEditMode);
        palette.Update(curEditMode);
        viewer.UpdateViewer(curEditMode, curSelectedButton);
        selection.UpdatePanel(curEditMode, curSelectedButton);
        viewport.UpdateViewport();
        
        toolSections.repaint();
    }

    public EditMode GetMode()
    {
        return curEditMode;
    }

    public TileButton GetCurTileButton()
    {
        return curSelectedButton;
    }
}

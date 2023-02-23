package tomentme;

import javax.swing.JPanel;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;

import tomentme.AssetsManager.AssetManager;
import tomentme.GUI.GUI;
import tomentme.GUI.Elements.*;
import tomentme.GUI.Elements.Palette.ItemInPalette;
import tomentme.GUI.ToolPanel.*;
import tomentme.Map.*;
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

    private int stateIndx = -1;
    private List<TMapMemento> states = new ArrayList<>();

    boolean initialized = false;

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

        curEditMode = EditMode.WALL;

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

        curEditMode = (EditMode.WALL);
        NewMap();

        commands = new CommandsPanel(toolSections);
        palette = new PalettePanel(toolSections);
        viewer = new ViewerPanel(toolSections);
        selection = new SelectionPanel(toolSections);

        this.add(toolSections, BorderLayout.EAST);
        viewport = new Viewport(this);
        
        SelectTile(viewport.tiles[12][12]);
        viewport.UpdateViewport();
        UpdateAll();
        initialized = true;
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
        SaveMapState();

        System.out.println("doing");
        if(commands.IsCopying())
        {
            switch(curEditMode)
            {
                case FLOOR_CEILING:
                    break;

                case AI:
                case SPRITE:
                    Utilities.SetInSpritesMap(currentFloor, tile.GetY(), tile.GetX(), commands.copiedInt);
                    break;

                case WALL:
                    Utilities.SetWallParse(currentFloor, tile.GetY(), tile.GetX(), commands.copiedWall);
                    break;
                default:
                    break;
            }
        }
        else // adding from palette
        {
            ItemInPalette itemToDraw = palette.selectedItem;

            if(itemToDraw == null)
                return;
            
            switch(itemToDraw.iType)
            {
                case WALL:
                    WallObject wallObj = Utilities.GetWallFromMap(currentFloor, tile.GetY(), tile.GetX());

                    wallObj.assetID = itemToDraw.iID;

                    // Set all the textures as 1 by default
                    for(int i = 0; i < wallObj.textureArray.length; i++)
                        wallObj.textureArray[i] = 1;
                    break;

                case AI:
                case SPRITE:
                    Utilities.SetInSpritesMap(currentFloor, tile.GetY(), tile.GetX(), itemToDraw.iID);
                    break;

                default:
                    break;
            }
        }

        UpdateAll();
    }


    public void CopySelectedTile()
    {
        switch(curEditMode)
        {
            case FLOOR_CEILING:
                break;

            case AI:
            case SPRITE:
                // If selected, get the selected tile
                if(curSelectedButton == null)
                    return;

                commands.SetIsCopying(true);

                int sprite = Utilities.GetSpriteFromMap(TomentEditor.instance.GetCurrentFloor(), curSelectedButton.GetY(), curSelectedButton.GetX());
                commands.copiedInt = sprite;
                break;

            case WALL:
                // If selected, get the selected tile
                if(curSelectedButton == null)
                    return;

                commands.SetIsCopying(true);

                WallObject wallObj = Utilities.GetWallFromMap(TomentEditor.instance.GetCurrentFloor(), curSelectedButton.GetY(), curSelectedButton.GetX());
                commands.copiedWall = wallObj;
                break;

            default:
                break;
            
        }
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
        if(curEditMode != null && curEditMode == mode)
            return;

        curEditMode = mode;

        commands.SetIsCopying(false);
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

    public void SaveMapState()
    {
        stateIndx++;
        states.add(stateIndx, new TMapMemento(currentMap));
    }

    public void RestoreToPrevious()
    {
        if(stateIndx >= 0)
        {
            currentMap.RestoreFromMemento(states.get(stateIndx));
            stateIndx--;
        }

        UpdateAll();
    }

    public void NewMap()
    {
        currentMap = new TMap();
        GUI.GetFrame().setTitle("Toment MapEditor - " + "New Map");

        if(initialized)
            UpdateAll();
    }

    public void OpenMap(String str)
    {
        currentMap.LoadMap(str);
        GUI.GetFrame().setTitle("Toment MapEditor - " + currentMap.ID);
        UpdateAll();
    }

    public void SaveMap()
    {

    }
}

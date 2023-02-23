package tomentme;

import javax.swing.JPanel;

import java.awt.*;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
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
        
        try
        {
            System.out.println(Main.path+"/Data/maps/"+currentMap.ID+".tmap");
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Main.path+"/Data/maps/"+currentMap.ID+".tmap"), "utf-8"));

            WriteMapToFile(writer);

            writer.close();
        }
        catch(Exception e)
        {

        }
    }

    private void WriteMapToFile(Writer writer)
    {
        TMap m = currentMap;
        
        try
        {
            writer.write("id="+m.ID+";\n");
            writer.write("name="+m.displayName+";\n");
            writer.write("startingLevel="+m.playerStartingLevel+";\n");
            writer.write("startingGridX="+m.playerStartingGridX+";\n");
            writer.write("startingGridY="+m.playerStartingGridY+";\n");
            writer.write("startingRot="+m.playerStartingRot+";\n");

            // Write level maps
            int row = 0;
            int col = 0;

            // Write level0 map
            writer.write("level0=\n");
            writer.write("[\n");

            boolean level0Done = false;
            row = 0;
            col = 0;
            while(!level0Done)
            {
                // Write row
                writer.write("{");
                while(row < 24)
                {
                    writer.write("(" + m.level0[col][row].assetID + "," + m.level0[col][row].textureArray[WallObject.TEXTURE_ARRAY_TOP] + "," + m.level0[col][row].textureArray[WallObject.TEXTURE_ARRAY_BOTTOM] + "," + m.level0[col][row].textureArray[WallObject.TEXTURE_ARRAY_LEFT] + "," + m.level0[col][row].textureArray[WallObject.TEXTURE_ARRAY_RIGHT] + "," + m.level0[col][row].textureArray[WallObject.TEXTURE_ARRAY_UP] + "," + m.level0[col][row].textureArray[WallObject.TEXTURE_ARRAY_DOWN] + "," + "\"" + m.level0[col][row].data + "\"),");
                    row++;
                }

                writer.write("}");

                col++;

                if(col < 24)
                {
                    writer.write(",\n");
                    row = 0;
                }
                else
                {
                    writer.write("];\n");
                    level0Done = true;
                    break;
                }
            }

            // Write level1 map
            writer.write("level1=\n");
            writer.write("[\n");

            boolean level1Done = false;
            row = 0;
            col = 0;
            while(!level1Done)
            {
                // Write row
                writer.write("{");
                while(row < 24)
                {
                    writer.write("(" + m.level1[col][row].assetID + "," + m.level1[col][row].textureArray[WallObject.TEXTURE_ARRAY_TOP] + "," + m.level1[col][row].textureArray[WallObject.TEXTURE_ARRAY_BOTTOM] + "," + m.level1[col][row].textureArray[WallObject.TEXTURE_ARRAY_LEFT] + "," + m.level1[col][row].textureArray[WallObject.TEXTURE_ARRAY_RIGHT] + "," + m.level1[col][row].textureArray[WallObject.TEXTURE_ARRAY_UP] + "," + m.level1[col][row].textureArray[WallObject.TEXTURE_ARRAY_DOWN] + "," + "\"" + m.level1[col][row].data + "\"),");
                    row++;
                }

                writer.write("}");

                col++;

                if(col < 24)
                {
                    writer.write(",\n");
                    row = 0;
                }
                else
                {
                    writer.write("];\n");
                    level1Done = true;
                    break;
                }
            }

            // Write level2 map
            writer.write("level2=\n");
            writer.write("[\n");

            boolean level2Done = false;
            row = 0;
            col = 0;
            while(!level2Done)
            {
                // Write row
                writer.write("{");
                while(row < 24)
                {
                    writer.write("(" + m.level2[col][row].assetID + "," + m.level2[col][row].textureArray[WallObject.TEXTURE_ARRAY_TOP] + "," + m.level2[col][row].textureArray[WallObject.TEXTURE_ARRAY_BOTTOM] + "," + m.level2[col][row].textureArray[WallObject.TEXTURE_ARRAY_LEFT] + "," + m.level2[col][row].textureArray[WallObject.TEXTURE_ARRAY_RIGHT] + "," + m.level2[col][row].textureArray[WallObject.TEXTURE_ARRAY_UP] + "," + m.level2[col][row].textureArray[WallObject.TEXTURE_ARRAY_DOWN] + "," + "\"" + m.level2[col][row].data + "\"),");
                    row++;
                }

                writer.write("}");

                col++;

                if(col < 24)
                {
                    writer.write(",\n");
                    row = 0;
                }
                else
                {
                    writer.write("];\n");
                    level2Done = true;
                    break;
                }
            }

            // Write floor map
            writer.write("floorsMap=\n");
            writer.write("[\n");

            boolean floormapDone = false;
            row = 0;
            col = 0;
            while(!floormapDone)
            {
                // Write row
                writer.write("{");
                while(row < 24)
                {
                    writer.write(m.floorMap[col][row] + ",");
                    row++;
                }

                writer.write("}");

                col++;

                if(col < 24)
                {
                    writer.write(",\n");
                    row = 0;
                }
                else
                {
                    writer.write("];\n");
                    floormapDone = true;
                    break;
                }
            }

            // Write ceiling map
            writer.write("ceilingMap=\n");
            writer.write("[\n");

            boolean ceilingMapDone = false;
            row = 0;
            col = 0;
            while(!ceilingMapDone)
            {
                // Write row
                writer.write("{");
                while(row < 24)
                {
                    writer.write(m.ceilingMap[col][row] + ",");
                    row++;
                }

                writer.write("}");

                col++;

                if(col < 24)
                {
                    writer.write(",\n");
                    row = 0;
                }
                else
                {
                    writer.write("];\n");
                    ceilingMapDone = true;
                    break;
                }
            }

            // Write sprites map 0
            writer.write("spritesMapLevel0=\n");
            writer.write("[\n");

            boolean spritesMapLevel0Done = false;
            row = 0;
            col = 0;
            while(!spritesMapLevel0Done)
            {
                // Write row
                writer.write("{");
                while(row < 24)
                {
                    writer.write(m.spritesMapLevel0[col][row] + ",");
                    row++;
                }

                writer.write("}");

                col++;

                if(col < 24)
                {
                    writer.write(",\n");
                    row = 0;
                }
                else
                {
                    writer.write("];\n");
                    spritesMapLevel0Done = true;
                    break;
                }
            }

            // Write sprites map 0
            writer.write("spritesMapLevel1=\n");
            writer.write("[\n");

            boolean spritesMapLevel1Done = false;
            row = 0;
            col = 0;
            while(!spritesMapLevel1Done)
            {
                // Write row
                writer.write("{");
                while(row < 24)
                {
                    writer.write(m.spritesMapLevel1[col][row] + ",");
                    row++;
                }

                writer.write("}");

                col++;

                if(col < 24)
                {
                    writer.write(",\n");
                    row = 0;
                }
                else
                {
                    writer.write("];\n");
                    spritesMapLevel1Done = true;
                    break;
                }
            }

            // Write sprites map 0
            writer.write("spritesMapLevel2=\n");
            writer.write("[\n");

            boolean spritesMapLevel2Done = false;
            row = 0;
            col = 0;
            while(!spritesMapLevel2Done)
            {
                // Write row
                writer.write("{");
                while(row < 24)
                {
                    writer.write(m.spritesMapLevel2[col][row] + ",");
                    row++;
                }

                writer.write("}");

                col++;

                if(col < 24)
                {
                    writer.write(",\n");
                    row = 0;
                }
                else
                {
                    writer.write("];\n");
                    spritesMapLevel2Done = true;
                    break;
                }
            }

            // Write last properties
            writer.write("wallLighting="+m.wallLight+"f;\n");
            writer.write("floorLighting="+m.floorLight+"f;\n");
            writer.write("skyID="+m.skyID+";\n");
            int hasAbsCeilingVal = m.hasAbsCeiling ? 1 : 0;
            writer.write("hasAbsCeiling="+hasAbsCeilingVal+";\n");
            writer.write("absCeilingLevel="+m.absCeilingLevel+";\n;");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}

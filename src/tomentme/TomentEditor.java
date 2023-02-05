package tomentme;

import javax.swing.JPanel;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import tomentme.*;
import tomentme.AssetsManager.AssetManager;
import tomentme.GUI.*;
import tomentme.GUI.Elements.*;
import tomentme.GUI.Toolbar.*;
import tomentme.Map.TMap;

import java.io.*;
import java.awt.image.*;
import javax.imageio.*;


public class TomentEditor extends JPanel
{
    public static TomentEditor instance;

    // Tools
    private JPanel toolSections;

    private CommandsPanel commands;
    private PalettePanel palette;
    private ViewerPanel viewer;
    private SelectionPanel selection;

    private Viewport viewport;

    public TMap currentMap;

    private int currentFloor = 0;

    // Logic
    private TileButton curSelectedButton;


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
        toolSections.setBackground(Color.red);
        
        commands = new CommandsPanel(toolSections);
        palette = new PalettePanel(toolSections);
        viewer = new ViewerPanel(toolSections);
        selection = new SelectionPanel(toolSections);

        AssetManager assetManager = new AssetManager();
        AssetManager.instance = assetManager;
        assetManager.InitializeAssetManager();

        this.add(toolSections, BorderLayout.EAST);
        viewport = new Viewport(this);
        
        // Testing level load
        currentMap = new TMap();
        currentMap.LoadMap("lvl1");

        viewport.UpdateViewport();
    }

    public void SelectTile(TileButton tile)
    {
        if(curSelectedButton != null)
            curSelectedButton.Unselect();

        viewer.SetSelectedTileValue("("+tile.GetX()+","+tile.GetY()+")");
        tile.setBorder(TileButton.selectedBorder);
        tile.SetSelected(true);

        curSelectedButton = tile;
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
}

package tomentme.GUI;

import javax.swing.JPanel;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import tomentme.*;        
import tomentme.GUI.Elements.*;
import tomentme.GUI.Toolbar.*;

import java.io.*;
import java.awt.image.*;
import javax.imageio.*;


public class TomentEditor extends JPanel
{
    private JPanel toolSections;

    private CommandsPanel commands;
    private PalettePanel palette;
    private ViewerPanel viewer;
    private SelectionPanel selection;

    private Viewport viewport;

    // Statics
    public static Border notSelectedBorder;
    public static Border selectedBorder;
    public static Border hoverBorder;
    
    public TomentEditor()
    {
        setLayout(new BorderLayout());

        InitializeGUIMembers();
        InitializeTomentMapEditor();
    }

    public static void InitializeGUIMembers()
    {
        notSelectedBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1, false);
        selectedBorder = BorderFactory.createLineBorder(Color.YELLOW, 1, false);
        hoverBorder = BorderFactory.createLineBorder(Color.YELLOW, 1, false);
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

        this.add(toolSections, BorderLayout.EAST);

        viewport = new Viewport(this);
    }
}

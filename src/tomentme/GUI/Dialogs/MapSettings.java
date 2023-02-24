package tomentme.GUI.Dialogs;

import java.text.NumberFormat;

import javax.swing.text.NumberFormatter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

import tomentme.TomentEditor;
import tomentme.Map.TMap;
import java.awt.event.*;

/*
 * Represents the DialogBox that edits the settings of a map
 */
public class MapSettings extends JDialog
{
    // All fields
    private JTextField idValue;
    private JTextField nameValue;
    private JTextField startingLevel;
    private JTextField startingPosX;
    private JTextField startingPosY;
    private JTextField startingRot;
    private JTextField wallLight;
    private JTextField floorLight;
    private JTextField skyID;
    private JTextField absCeil;
    private JTextField ceilingLevel;


    // Constructor
    public MapSettings(JFrame frame, String title, boolean modal)
    {
        super(frame, title, modal);
        
        // Set layout
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        Initialize();
        
        // Set final properties
        this.setLocationRelativeTo(frame);
        this.setSize(300, 400);
        this.setVisible(true);
    }

    // Initializes the panel by adding all the content
    public void Initialize()
    {
        TMap currentMap = TomentEditor.instance.currentMap;

        JPanel content = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // ID
        JPanel idPanel = new JPanel();
        JLabel idLabel = new JLabel("ID: ");
        idValue = new JTextField(20);
        idValue.setText(currentMap.ID);
        idPanel.add(idLabel);
        idPanel.add(idValue);
        content.add(idPanel);

        // Name
        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Name: ");
        nameValue = new JTextField(20);
        nameValue.setText(currentMap.displayName);
        namePanel.add(nameLabel);
        namePanel.add(nameValue);
        content.add(namePanel);

        // Level
        JPanel startingLevelPanel = new JPanel();
        JLabel startingLevelLabel = new JLabel("Floor: ");
        startingLevel = new JFormattedTextField(20);
        startingLevel.setText(Integer.toString(currentMap.playerStartingLevel));
        startingLevelPanel.add(startingLevelLabel);
        startingLevelPanel.add(startingLevel);
        content.add(startingLevelPanel);

        // Pos
        JPanel startingPos = new JPanel();
        JLabel startingPosLabel = new JLabel("Start (X,Y): ");
        startingPosX = new JTextField(2);
        startingPosY = new JTextField(2);
        startingPosX.setText(Integer.toString(currentMap.playerStartingGridX));
        startingPosY.setText(Integer.toString(currentMap.playerStartingGridY));
        startingPos.add(startingPosLabel);
        startingPos.add(startingPosX);
        startingPos.add(startingPosY);
        content.add(startingPos);

        // Rot
        JPanel startingRotPanel = new JPanel();
        JLabel startingRotLabel = new JLabel("Rotation: ");
        startingRot = new JTextField(4);
        startingRot.setText(Float.toString(currentMap.playerStartingRot));
        startingRotPanel.add(startingRotLabel);
        startingRotPanel.add(startingRot);
        content.add(startingRotPanel);

        // Wall light
        JPanel wallLightPanel = new JPanel();
        JLabel startingWallLightLabel = new JLabel("Wall Light: ");
        wallLight = new JTextField(4);
        wallLight.setText(Float.toString(currentMap.wallLight));
        wallLightPanel.add(startingWallLightLabel);
        wallLightPanel.add(wallLight);
        content.add(wallLightPanel);

        // Floor light
        JPanel floorLightPanel = new JPanel();
        JLabel startingFloorLightLabel = new JLabel("Floor Light: ");
        floorLight = new JTextField(4);
        floorLight.setText(Float.toString(currentMap.floorLight));
        floorLightPanel.add(startingFloorLightLabel);
        floorLightPanel.add(floorLight);
        content.add(floorLightPanel);

        // SkyID
        JPanel skyIDPanel = new JPanel();
        JLabel skyIDLabel = new JLabel("Sky ID: ");
        skyID = new JTextField(20);
        skyID.setText(Integer.toString(currentMap.skyID));
        skyIDPanel.add(skyIDLabel);
        skyIDPanel.add(skyID);
        content.add(skyIDPanel);

        // Has ceiling
        JPanel absCeilPanel = new JPanel();
        JLabel absCeilLabel = new JLabel("Abs Ceiling?");
        absCeil = new JTextField(4);
        absCeil.setText(Integer.toString(currentMap.absCeilingLevel));
        absCeilPanel.add(absCeilLabel);
        absCeilPanel.add(absCeil);
        content.add(absCeilPanel);

        // Ceiling lvl
        JPanel ceilingLevelPanel = new JPanel();
        JLabel ceilingLevelLabel = new JLabel("Ceiling Lvl: ");
        ceilingLevel = new JTextField(4);
        ceilingLevel.setText(Integer.toString(currentMap.skyID));
        ceilingLevelPanel.add(ceilingLevelLabel);
        ceilingLevelPanel.add(ceilingLevel);
        content.add(ceilingLevelPanel);

        // Save button
        JButton saveBtn = new JButton("SAVE");
        saveBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Save();
            }
        });


        // Cancel button
        JButton cancelBTn = new JButton("CANCEL");
        cancelBTn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Cancel();
            }
        });
        content.add(new JLabel("                                                                                               "));

        content.add(saveBtn);
        content.add(cancelBTn);

        this.add(content);
    }

    // Saves the settings on the map
    public void Save()
    {
        TMap currentMap = TomentEditor.instance.currentMap;

        // Save all properties
        currentMap.ID = idValue.getText();
        currentMap.displayName = nameValue.getText();

        currentMap.playerStartingLevel = Integer.parseInt(startingLevel.getText());
        currentMap.playerStartingGridX = Integer.parseInt(startingPosX.getText());
        currentMap.playerStartingGridY = Integer.parseInt(startingPosY.getText());

        currentMap.playerStartingRot = Float.parseFloat(startingRot.getText());
        currentMap.wallLight = Float.parseFloat(wallLight.getText());
        currentMap.floorLight = Float.parseFloat(floorLight.getText());

        currentMap.skyID = Integer.parseInt(skyID.getText());
        currentMap.hasAbsCeiling = (Integer.parseInt(absCeil.getText())) == 0 ? false : true;
        currentMap.absCeilingLevel = Integer.parseInt(ceilingLevel.getText());

        TomentEditor.instance.UpdateAll();

        this.dispose();
    }

    // Cancels the process
    public void Cancel()
    {
        this.dispose();
    }
}

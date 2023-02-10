package tomentme.GUI.Toolbar;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import tomentme.TomentEditor;
import tomentme.AssetsManager.AssetManager;
import tomentme.AssetsManager.AssetManager.TextureIDs;
import tomentme.GUI.Elements.TileButton;
import tomentme.GUI.Toolbar.Elements.SelectionWallFaceButton;
import tomentme.Map.TMap;
import tomentme.Map.WallObject;
import tomentme.TomentEditor.EditMode;

import java.io.*;
import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class SelectionPanel 
{
    private SelectionWallFaceButton curWallFace = null;
    public int selectedTextureArray = 0;
    
    private JLabel imgLabel;

    // Wall
    private JComboBox selectBox;
    private boolean fireSelectBox = false;
    private List<SelectionWallFaceButton> wallFaceButtons = new ArrayList<>();

    public SelectionPanel(JPanel toolSections)
    {
        JPanel selectedFacePanel = new JPanel();
        selectedFacePanel.setLayout(new FlowLayout());
        selectedFacePanel.setBackground(Color.YELLOW);

        JPanel selectedFaceSelection = new JPanel();
        selectedFaceSelection.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel sfLabel = new JLabel("Selected Info: ");
        selectedFaceSelection.add(sfLabel);
        toolSections.add(selectedFaceSelection);

        imgLabel = new JLabel((AssetManager.instance.textures[1]));
        imgLabel.setPreferredSize(new Dimension(64,64));
        selectedFacePanel.add(imgLabel, BorderLayout.LINE_START);

        JPanel sbtnPnl = new JPanel();
        selectBox = new JComboBox<>(TextureIDs.values());
        selectBox.setBorder(null);
        selectBox.setPreferredSize(new Dimension(70,25));

        selectBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Wall_SelectTexture();
            }
        });

        sbtnPnl.add(selectBox);

        selectedFacePanel.add(sbtnPnl);

        JPanel pnl = new JPanel();
        pnl.setLayout(new GridLayout(0, 1));

        SelectionWallFaceButton TOPBtn = new SelectionWallFaceButton(this, "TOP", WallObject.TEXTURE_ARRAY_TOP);
        SelectionWallFaceButton BOTTOMBtn = new SelectionWallFaceButton(this, "BOTTOM", WallObject.TEXTURE_ARRAY_BOTTOM);
        SelectionWallFaceButton LEFTBtn = new SelectionWallFaceButton(this, "LEFT", WallObject.TEXTURE_ARRAY_LEFT);
        SelectionWallFaceButton RIGHTBtn = new SelectionWallFaceButton(this, "RIGHT", WallObject.TEXTURE_ARRAY_RIGHT);
        SelectionWallFaceButton FORWARDBtn = new SelectionWallFaceButton(this, "FORWARD", WallObject.TEXTURE_ARRAY_UP);
        SelectionWallFaceButton BACKBtn = new SelectionWallFaceButton(this, "BACK", WallObject.TEXTURE_ARRAY_DOWN);

        wallFaceButtons.add(TOPBtn);
        wallFaceButtons.add(BOTTOMBtn);
        wallFaceButtons.add(LEFTBtn);
        wallFaceButtons.add(RIGHTBtn);
        wallFaceButtons.add(BACKBtn);
        wallFaceButtons.add(FORWARDBtn);
        wallFaceButtons.add(BACKBtn);

        // Set first as default
        curWallFace = TOPBtn;
        TOPBtn.setBorder(SelectionWallFaceButton.selectedBorder);

        for(SelectionWallFaceButton btn : wallFaceButtons)
            pnl.add(btn);

        selectedFacePanel.add(pnl, BorderLayout.LINE_END);

        toolSections.add(selectedFacePanel);
    }

    public void UpdatePanel(EditMode mode, TileButton tile)
    {
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

                imgLabel.setIcon(AssetManager.instance.textures[wallObj.textureArray[selectedTextureArray]]);

                fireSelectBox = false;
                selectBox.setSelectedIndex(wallObj.textureArray[selectedTextureArray]);
                fireSelectBox = true;

                // Update img label
                if(wallObj.assetID > 0)
                {
                    for(SelectionWallFaceButton btn : wallFaceButtons)
                        btn.setVisible(true);

                    selectBox.setVisible(true);
                }
                else
                {
                    for(SelectionWallFaceButton btn : wallFaceButtons)
                        btn.setVisible(false);

                    selectBox.setVisible(false);
                }
                
                break;

            default:
                break;
            
        }
    }

	public void Wall_ChangeTextureArray(SelectionWallFaceButton source) 
    {
        if(curWallFace != null)
            curWallFace.setBorder(SelectionWallFaceButton.notSelectedBorder);

        curWallFace = source;
        source.setBorder(SelectionWallFaceButton.selectedBorder);

        selectedTextureArray = source.textureArrayValue;

        // Update panel
        UpdatePanel(TomentEditor.instance.GetMode(), TomentEditor.instance.GetCurTileButton());
	}

    public void Wall_SelectTexture()
    {
        if(!fireSelectBox)
            return;

        int selection = selectBox.getSelectedIndex();

        System.out.println(selection);

        TMap curMap = TomentEditor.instance.currentMap;
        
        TileButton tile = TomentEditor.instance.GetCurrentTileButton();

        if(tile != null)
        {
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

            wallObj.textureArray[selectedTextureArray] = selection;

            UpdatePanel(TomentEditor.instance.GetMode(), TomentEditor.instance.GetCurTileButton());
        }
    }
}

package tomentme.GUI.ToolPanel;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import tomentme.TomentEditor;
import tomentme.Utilities;
import tomentme.AssetsManager.AssetManager;
import tomentme.AssetsManager.AssetManager.*;
import tomentme.GUI.Elements.TileButton;
import tomentme.GUI.Elements.SelectionPanel.SelectionWallFaceButton;
import tomentme.Map.*;
import tomentme.TomentEditor.EditMode;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

public class SelectionPanel 
{
    private SelectionWallFaceButton curWallFace = null;
    public int selectedTextureArray = 0;
    
    private JLabel imgLabel;

    // Wall
    private JComboBox selectBox;
    private boolean fireSelectBox = false;
    private boolean fireSelectBoxSprite = false;
    private List<SelectionWallFaceButton> wallFaceButtons = new ArrayList<>();

    private JComboBox selectBoxSprites;
    private JPanel sbtnPnl;
    private JPanel wallFaceButtonsPanel;

    public SelectionPanel(JPanel toolSections)
    {
        JPanel selectedFacePanel = new JPanel();
        selectedFacePanel.setLayout(new FlowLayout());
        selectedFacePanel.setBackground(Color.GRAY);

        JPanel selectedFaceSelection = new JPanel();
        selectedFaceSelection.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel sfLabel = new JLabel("Selected Info: ");
        selectedFaceSelection.add(sfLabel);
        toolSections.add(selectedFaceSelection);

        imgLabel = new JLabel((AssetManager.instance.textures[1]));
        imgLabel.setPreferredSize(new Dimension(64,64));
        selectedFacePanel.add(imgLabel, BorderLayout.LINE_START);

        sbtnPnl = new JPanel();
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

        selectBoxSprites = new JComboBox<>(SpritesAssets.values());
        selectBoxSprites.setBorder(null);
        selectBoxSprites.setPreferredSize(new Dimension(70,25));

        selectBoxSprites.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Sprite_SelectSprite();
            }
        });

        sbtnPnl.add(selectBox);
        sbtnPnl.add(selectBoxSprites);

        selectedFacePanel.add(sbtnPnl);

        wallFaceButtonsPanel = new JPanel();
        wallFaceButtonsPanel.setBackground(null);
        wallFaceButtonsPanel.setLayout(new GridLayout(0, 1));

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
            wallFaceButtonsPanel.add(btn);

        selectedFacePanel.add(wallFaceButtonsPanel, BorderLayout.LINE_END);

        toolSections.add(selectedFacePanel);
    }

    public void UpdatePanel(EditMode mode, TileButton tile)
    {
        if(tile == null)
            return;

        switch (mode)
        {
            case FLOOR_CEILING:

                break;

            case AI:
            case SPRITE:
                int spriteObj = Utilities.GetSpriteFromMap(TomentEditor.instance.GetCurrentFloor(), tile.GetY(), tile.GetX());

                imgLabel.setIcon(AssetManager.instance.sprites[spriteObj]);

                fireSelectBoxSprite = false;
                selectBoxSprites.setSelectedIndex(spriteObj);
                fireSelectBoxSprite = true;

                sbtnPnl.remove(selectBox);
                sbtnPnl.add(selectBoxSprites);

                for(SelectionWallFaceButton btn : wallFaceButtons)
                    btn.setVisible(false);
                break;

            case WALL:
                
                WallObject wallObj = Utilities.GetWallFromMap(TomentEditor.instance.GetCurrentFloor(), tile.GetY(), tile.GetX());

                imgLabel.setIcon(AssetManager.instance.textures[wallObj.textureArray[selectedTextureArray]]);

                fireSelectBox = false;
                selectBox.setSelectedIndex(wallObj.textureArray[selectedTextureArray]);
                fireSelectBox = true;

                // Update img label
                for(SelectionWallFaceButton btn : wallFaceButtons)
                    btn.setVisible(true);

                selectBox.setVisible(true);


                sbtnPnl.add(selectBox);
                sbtnPnl.remove(selectBoxSprites);

                for(SelectionWallFaceButton btn : wallFaceButtons)
                    btn.setVisible(true);
                
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
            WallObject wallObj = Utilities.GetWallFromMap(TomentEditor.instance.GetCurrentFloor(), tile.GetY(), tile.GetX());

            wallObj.textureArray[selectedTextureArray] = selection;

            UpdatePanel(TomentEditor.instance.GetMode(), TomentEditor.instance.GetCurTileButton());
        }
    }

    private void Sprite_SelectSprite()
    {
        if(!fireSelectBoxSprite)
            return;

        int selection = selectBoxSprites.getSelectedIndex();

        System.out.println(selection);

        TileButton tile = TomentEditor.instance.GetCurrentTileButton();

        if(tile != null)
        {
            Utilities.SetInSpritesMap(TomentEditor.instance.GetCurrentFloor(), tile.GetY(), tile.GetX(), selection);

            UpdatePanel(TomentEditor.instance.GetMode(), TomentEditor.instance.GetCurTileButton());
            TomentEditor.instance.GetViewport().UpdateViewport();
            TomentEditor.instance.GetViewerPanel().UpdateViewer(TomentEditor.instance.GetMode(), TomentEditor.instance.GetCurTileButton());
        }
    }
}

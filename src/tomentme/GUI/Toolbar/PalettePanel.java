package tomentme.GUI.Toolbar;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

import tomentme.TomentEditor;
import tomentme.AssetsManager.AssetManager;
import tomentme.AssetsManager.AssetManager.*;
import tomentme.GUI.Elements.Palette.*;
import tomentme.GUI.Elements.Palette.ItemInPalette.ItemType;
import tomentme.TomentEditor.EditMode;

public class PalettePanel 
{
    public ItemInPalette selectedItem;

    private JPanel scrollContentPanel;

    private List<JComponent> wallsElements = new ArrayList<JComponent>();
    private List<JComponent> spritesElements = new ArrayList<JComponent>();
    private List<JComponent> aiElements = new ArrayList<JComponent>();

    private ChangeEditModeButton editWall;
    private ChangeEditModeButton editSprites;
    private ChangeEditModeButton editAI;
    private ChangeEditModeButton editFloorCeiling;

    public PalettePanel(JPanel toolSections)
    {
        JPanel palettePanel = new JPanel();
        palettePanel.setBackground(Color.GRAY);
        palettePanel.setPreferredSize(new Dimension(150, 240));

        JPanel paletteSelection = new JPanel();
        paletteSelection.setLayout(new FlowLayout(FlowLayout.LEFT));
        palettePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel psLabel = new JLabel("Palette: ");
        paletteSelection.add(psLabel);

        JPanel paletteButtons = new JPanel();
        
        editWall = new ChangeEditModeButton("WALL", EditMode.WALL);
        editWall.setPreferredSize(new Dimension(60, 20));
        paletteButtons.add(editWall);

        editSprites = new ChangeEditModeButton("SPRITES", EditMode.SPRITE);
        editSprites.setPreferredSize(new Dimension(60, 20));
        paletteButtons.add(editSprites);

        editAI = new ChangeEditModeButton("AI", EditMode.AI);
        editAI.setPreferredSize(new Dimension(30, 20));
        paletteButtons.add(editAI);

        editFloorCeiling = new ChangeEditModeButton("F/C", EditMode.FLOOR_CEILING);
        editFloorCeiling.setPreferredSize(new Dimension(30, 20));
        paletteButtons.add(editFloorCeiling);
        
        JPanel psContent = new JPanel();
        psContent.setLayout(new BorderLayout());
        psContent.setBackground(Color.GRAY);
        
        scrollContentPanel = new JPanel(new GridLayout(0,2));
        JScrollPane scrollFrame = new JScrollPane(scrollContentPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFrame.getVerticalScrollBar().setUnitIncrement(8);
        scrollFrame.setPreferredSize(new Dimension(222,140));
        scrollFrame.setAutoscrolls(true);
        scrollContentPanel.setBorder(null);
        scrollFrame.setBorder(null);

        // Fill items
        FillContentPane_Walls(scrollContentPanel);
        FillContentPane_Sprites(scrollContentPanel);
        FillContentPane_AI(scrollContentPanel);

        psContent.add(scrollFrame);
        toolSections.add(paletteSelection);
        palettePanel.add(paletteButtons);
        palettePanel.add(psContent);
        toolSections.add(palettePanel);

        HideAll();
    }

    public void Update(EditMode mode)
    {
        HideAll();
        editWall.setBorder(ChangeEditModeButton.notSelectedBorder);
        editSprites.setBorder(ChangeEditModeButton.notSelectedBorder);
        editAI.setBorder(ChangeEditModeButton.notSelectedBorder);
        editFloorCeiling.setBorder(ChangeEditModeButton.notSelectedBorder);

        switch(mode)
        {
            case AI:
                for(JComponent c : aiElements)
                    scrollContentPanel.add(c);
                editAI.setBorder(ChangeEditModeButton.selectedBorder);
                break;

            case FLOOR_CEILING:
                editFloorCeiling.setBorder(ChangeEditModeButton.selectedBorder);
                break;

            case SPRITE:
                for(JComponent c : spritesElements)
                    scrollContentPanel.add(c);

                editSprites.setBorder(ChangeEditModeButton.selectedBorder);
                break;

            case WALL:
                for(JComponent c : wallsElements)
                    scrollContentPanel.add(c);

                editWall.setBorder(ChangeEditModeButton.selectedBorder);
                break;

            default:
                break;
            
        }

        scrollContentPanel.repaint();
    }

    public void SelectItemInPalette(ItemInPalette newItem)
    {
        if(selectedItem != null)
            selectedItem.Unselect();

        selectedItem = newItem;
        selectedItem.Select();

        TomentEditor.instance.GetCommandsPanel().SetCurSelectedItem(selectedItem.GetName());
        TomentEditor.instance.GetCommandsPanel().SetIsCopying(false);
    }

    public void UnselectItemInPalette()
    {
        if(selectedItem != null)
            selectedItem.Unselect();

        TomentEditor.instance.GetCommandsPanel().SetCurSelectedItem("NULL");

        selectedItem = null;
    }

    private void HideAll()
    {
        for(JComponent c : wallsElements)
            scrollContentPanel.remove(c);

        for(JComponent c : spritesElements)
            scrollContentPanel.remove(c);

        for(JComponent c : aiElements)
            scrollContentPanel.remove(c);
    }

    private void FillContentPane_Walls(JPanel scrollContentPanel)
    {
        ItemInPalette nothing = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.EMPTY.ordinal()], WallAssets.EMPTY.ordinal(), "EMPTY", ItemType.WALL);
        scrollContentPanel.add(nothing);
        wallsElements.add(nothing);

        ItemInPalette wall = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_Wall.ordinal()], WallAssets.W_Wall.ordinal(), "Wall     ", ItemType.WALL);
        scrollContentPanel.add(wall);
        wallsElements.add(wall);

        ItemInPalette thinWallHor = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_ThinWallHor.ordinal()], WallAssets.W_ThinWallHor.ordinal(), "Thin H", ItemType.WALL);
        scrollContentPanel.add(thinWallHor);
        wallsElements.add(thinWallHor);

        ItemInPalette thinWallVer = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_ThinWallVer.ordinal()],WallAssets.W_ThinWallVer.ordinal(), "Thin V", ItemType.WALL);
        scrollContentPanel.add(thinWallVer);
        wallsElements.add(thinWallVer);

        ItemInPalette doorHor = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_DoorHor.ordinal()],WallAssets.W_DoorHor.ordinal(), "Door H", ItemType.WALL);
        scrollContentPanel.add(doorHor);
        wallsElements.add(doorHor);

        ItemInPalette doorVer = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_DoorVer.ordinal()],WallAssets.W_DoorVer.ordinal(), "Door V", ItemType.WALL);
        scrollContentPanel.add(doorVer);
        wallsElements.add(doorVer);

        ItemInPalette teleporter = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_WallTriggerChangeMap.ordinal()],WallAssets.W_WallTriggerChangeMap.ordinal(), "Teleport", ItemType.WALL);
        scrollContentPanel.add(teleporter);
        wallsElements.add(teleporter);

        ItemInPalette ladderUp = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_WallLadder.ordinal()],WallAssets.W_WallLadder.ordinal(), "Ladder U", ItemType.WALL);
        scrollContentPanel.add(ladderUp);
        wallsElements.add(ladderUp);

        ItemInPalette ladderDown = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_WallLadderDown.ordinal()],WallAssets.W_WallLadderDown.ordinal(), "Ladder D", ItemType.WALL);
        scrollContentPanel.add(ladderDown);
        wallsElements.add(ladderDown);

        ItemInPalette invisibleWall = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_WallInvisible.ordinal()],WallAssets.W_WallInvisible.ordinal(), "Invisible", ItemType.WALL);
        scrollContentPanel.add(invisibleWall);
        wallsElements.add(invisibleWall);
    }

    private void FillContentPane_Sprites(JPanel scrollContentPanel)
    {
        ItemInPalette nothing = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_EMPTY.ordinal()], SpritesAssets.S_EMPTY.ordinal(), "EMPTY", ItemType.SPRITE);
        scrollContentPanel.add(nothing);
        spritesElements.add(nothing);

        ItemInPalette barrel = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_Barrel1.ordinal()],SpritesAssets.S_Barrel1.ordinal(), "Barrel", ItemType.SPRITE);
        scrollContentPanel.add(barrel);
        spritesElements.add(barrel);

        ItemInPalette campfire = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_Campfire.ordinal()],SpritesAssets.S_Campfire.ordinal(), "Campfire", ItemType.SPRITE);
        scrollContentPanel.add(campfire);
        spritesElements.add(campfire);

        ItemInPalette axePickup = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_PickupAxe.ordinal()],SpritesAssets.S_PickupAxe.ordinal(), "P_Axe", ItemType.SPRITE);
        scrollContentPanel.add(axePickup);
        spritesElements.add(axePickup);

        ItemInPalette hpotionPickup = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_PickupHealthPotion.ordinal()],SpritesAssets.S_PickupHealthPotion.ordinal(), "Health  ", ItemType.SPRITE);
        scrollContentPanel.add(hpotionPickup);
        spritesElements.add(hpotionPickup);

        ItemInPalette mpotionPickup = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_PickupManaPotion.ordinal()],SpritesAssets.S_PickupManaPotion.ordinal(), "Mana", ItemType.SPRITE);
        scrollContentPanel.add(mpotionPickup);
        spritesElements.add(mpotionPickup);

        ItemInPalette tomeFireball = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_TomeFireball1.ordinal()],SpritesAssets.S_TomeFireball1.ordinal(), "TFireball", ItemType.SPRITE);
        scrollContentPanel.add(tomeFireball);
        spritesElements.add(tomeFireball);

        ItemInPalette tomeIDart = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_TomeIceDart1.ordinal()],SpritesAssets.S_TomeIceDart1.ordinal(), "TIDart", ItemType.SPRITE);
        scrollContentPanel.add(tomeIDart);
        spritesElements.add(tomeIDart);

        ItemInPalette table1 = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_Table1.ordinal()],SpritesAssets.S_Table1.ordinal(), "Table 1", ItemType.SPRITE);
        scrollContentPanel.add(table1);
        spritesElements.add(table1);

        ItemInPalette skull = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_SkullStatic.ordinal()],SpritesAssets.S_SkullStatic.ordinal(), "Skull", ItemType.SPRITE);
        scrollContentPanel.add(skull);
        spritesElements.add(skull);

        ItemInPalette altarEmpty = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_AltarEmpty.ordinal()],SpritesAssets.S_AltarEmpty.ordinal(), "Altar E", ItemType.SPRITE);
        scrollContentPanel.add(altarEmpty);
        spritesElements.add(altarEmpty);

        ItemInPalette altarHealth = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_AltarHealth.ordinal()],SpritesAssets.S_AltarHealth.ordinal(), "Altar H", ItemType.SPRITE);
        scrollContentPanel.add(altarHealth);
        spritesElements.add(altarHealth);

        ItemInPalette altarMana = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_AltarMana.ordinal()],SpritesAssets.S_AltarMana.ordinal(), "Altar M", ItemType.SPRITE);
        scrollContentPanel.add(altarMana);
        spritesElements.add(altarMana);
    }

    private void FillContentPane_AI(JPanel scrollContentPanel)
    {
        ItemInPalette nothing = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_EMPTY.ordinal()], SpritesAssets.S_EMPTY.ordinal(), "EMPTY     ", ItemType.AI);
        scrollContentPanel.add(nothing);
        aiElements.add(nothing);

        ItemInPalette skeleton = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.DS_Skeleton.ordinal()],SpritesAssets.DS_Skeleton.ordinal(), "Skeleton", ItemType.AI);
        scrollContentPanel.add(skeleton);
        aiElements.add(skeleton);

        ItemInPalette skeletonBurnt = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.DS_SkeletonBurnt.ordinal()],SpritesAssets.DS_SkeletonBurnt.ordinal(), "Skeleton B", ItemType.AI);
        scrollContentPanel.add(skeletonBurnt);
        aiElements.add(skeletonBurnt);

        ItemInPalette skeletonElite = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.DS_SkeletonElite.ordinal()],SpritesAssets.DS_SkeletonElite.ordinal(), "Skeleton E", ItemType.AI);
        scrollContentPanel.add(skeletonElite);
        aiElements.add(skeletonElite);

        ItemInPalette skeletonLord = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.DS_SkeletonLord.ordinal()],SpritesAssets.DS_SkeletonLord.ordinal(), "Skeleton L", ItemType.AI);
        scrollContentPanel.add(skeletonLord);
        aiElements.add(skeletonLord);
    }
}

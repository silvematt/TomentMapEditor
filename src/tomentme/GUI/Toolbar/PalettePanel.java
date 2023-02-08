package tomentme.GUI.Toolbar;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import tomentme.AssetsManager.AssetManager;
import tomentme.AssetsManager.AssetManager.SpritesAssets;
import tomentme.AssetsManager.AssetManager.WallAssets;
import tomentme.GUI.Toolbar.Elements.ChangeEditModeButton;
import tomentme.GUI.Toolbar.Elements.ItemInPalette;
import tomentme.TomentEditor.EditMode;

public class PalettePanel 
{
    JPanel scrollContentPanel;

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
        palettePanel.setBackground(Color.GREEN);
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
        psContent.setBackground(Color.yellow);
        
        scrollContentPanel = new JPanel(new GridLayout(0,2));
        JScrollPane scrollFrame = new JScrollPane(scrollContentPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFrame.getVerticalScrollBar().setUnitIncrement(8);
        scrollFrame.setPreferredSize(new Dimension( 222,140));
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

    public void OnStateChanges(EditMode mode)
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
        ItemInPalette wall = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_Wall.ordinal()], "ID: " + WallAssets.W_Wall.ordinal(), "Wall     ");
        scrollContentPanel.add(wall);
        wallsElements.add(wall);

        ItemInPalette thinWallHor = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_ThinWallHor.ordinal()], "ID: " + WallAssets.W_ThinWallHor.ordinal(), "Thin H");
        scrollContentPanel.add(thinWallHor);
        wallsElements.add(thinWallHor);

        ItemInPalette thinWallVer = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_ThinWallVer.ordinal()], "ID: " + WallAssets.W_ThinWallVer.ordinal(), "Thin V");
        scrollContentPanel.add(thinWallVer);
        wallsElements.add(thinWallVer);

        ItemInPalette doorHor = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_DoorHor.ordinal()], "ID: " + WallAssets.W_DoorHor.ordinal(), "Door H");
        scrollContentPanel.add(doorHor);
        wallsElements.add(doorHor);

        ItemInPalette doorVer = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_DoorVer.ordinal()], "ID: " + WallAssets.W_DoorVer.ordinal(), "Door V");
        scrollContentPanel.add(doorVer);
        wallsElements.add(doorVer);

        ItemInPalette teleporter = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_WallTriggerChangeMap.ordinal()], "ID: " + WallAssets.W_WallTriggerChangeMap.ordinal(), "Teleport");
        scrollContentPanel.add(teleporter);
        wallsElements.add(teleporter);

        ItemInPalette ladderUp = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_WallLadder.ordinal()], "ID: " + WallAssets.W_WallLadder.ordinal(), "Ladder U");
        scrollContentPanel.add(ladderUp);
        wallsElements.add(ladderUp);

        ItemInPalette ladderDown = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_WallLadderDown.ordinal()], "ID: " + WallAssets.W_WallLadderDown.ordinal(), "Ladder D");
        scrollContentPanel.add(ladderDown);
        wallsElements.add(ladderDown);

        ItemInPalette invisibleWall = new ItemInPalette(AssetManager.instance.mapEditorPalette[WallAssets.W_WallInvisible.ordinal()], "ID: " + WallAssets.W_WallInvisible.ordinal(), "Invisible");
        scrollContentPanel.add(invisibleWall);
        wallsElements.add(invisibleWall);
    }

    private void FillContentPane_Sprites(JPanel scrollContentPanel)
    {
        ItemInPalette barrel = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_Barrel1.ordinal()], "ID: " + SpritesAssets.S_Barrel1.ordinal(), "Barrel");
        scrollContentPanel.add(barrel);
        spritesElements.add(barrel);

        ItemInPalette campfire = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_Campfire.ordinal()], "ID: " + SpritesAssets.S_Campfire.ordinal(), "Campfire");
        scrollContentPanel.add(campfire);
        spritesElements.add(campfire);

        ItemInPalette axePickup = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_PickupAxe.ordinal()], "ID: " + SpritesAssets.S_PickupAxe.ordinal(), "P_Axe");
        scrollContentPanel.add(axePickup);
        spritesElements.add(axePickup);

        ItemInPalette hpotionPickup = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_PickupHealthPotion.ordinal()], "ID: " + SpritesAssets.S_PickupHealthPotion.ordinal(), "Health  ");
        scrollContentPanel.add(hpotionPickup);
        spritesElements.add(hpotionPickup);

        ItemInPalette mpotionPickup = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_PickupManaPotion.ordinal()], "ID: " + SpritesAssets.S_PickupManaPotion.ordinal(), "Mana");
        scrollContentPanel.add(mpotionPickup);
        spritesElements.add(mpotionPickup);

        ItemInPalette tomeFireball = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_TomeFireball1.ordinal()], "ID: " + SpritesAssets.S_TomeFireball1.ordinal(), "TFireball");
        scrollContentPanel.add(tomeFireball);
        spritesElements.add(tomeFireball);

        ItemInPalette tomeIDart = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_TomeIceDart1.ordinal()], "ID: " + SpritesAssets.S_TomeIceDart1.ordinal(), "TIDart");
        scrollContentPanel.add(tomeIDart);
        spritesElements.add(tomeIDart);

        ItemInPalette table1 = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_Table1.ordinal()], "ID: " + SpritesAssets.S_Table1.ordinal(), "Table 1");
        scrollContentPanel.add(table1);
        spritesElements.add(table1);

        ItemInPalette skull = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_SkullStatic.ordinal()], "ID: " + SpritesAssets.S_SkullStatic.ordinal(), "Skull");
        scrollContentPanel.add(skull);
        spritesElements.add(skull);

        ItemInPalette altarEmpty = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_AltarEmpty.ordinal()], "ID: " + SpritesAssets.S_AltarEmpty.ordinal(), "Altar E");
        scrollContentPanel.add(altarEmpty);
        spritesElements.add(altarEmpty);

        ItemInPalette altarHealth = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_AltarHealth.ordinal()], "ID: " + SpritesAssets.S_AltarHealth.ordinal(), "Altar H");
        scrollContentPanel.add(altarHealth);
        spritesElements.add(altarHealth);

        ItemInPalette altarMana = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_AltarMana.ordinal()], "ID: " + SpritesAssets.S_AltarMana.ordinal(), "Altar M");
        scrollContentPanel.add(altarMana);
        spritesElements.add(altarMana);
    }

    private void FillContentPane_AI(JPanel scrollContentPanel)
    {
        ItemInPalette skeleton = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.DS_Skeleton.ordinal()], "ID: " + SpritesAssets.DS_Skeleton.ordinal(), "Skeleton");
        scrollContentPanel.add(skeleton);
        aiElements.add(skeleton);

        ItemInPalette skeletonBurnt = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.DS_SkeletonBurnt.ordinal()], "ID: " + SpritesAssets.DS_SkeletonBurnt.ordinal(), "Skeleton B");
        scrollContentPanel.add(skeletonBurnt);
        aiElements.add(skeletonBurnt);

        ItemInPalette skeletonElite = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.DS_SkeletonElite.ordinal()], "ID: " + SpritesAssets.DS_SkeletonElite.ordinal(), "Skeleton E");
        scrollContentPanel.add(skeletonElite);
        aiElements.add(skeletonElite);

        ItemInPalette skeletonLord = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.DS_SkeletonLord.ordinal()], "ID: " + SpritesAssets.DS_SkeletonLord.ordinal(), "Skeleton L");
        scrollContentPanel.add(skeletonLord);
        aiElements.add(skeletonLord);
    }
}

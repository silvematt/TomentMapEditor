package tomentme.GUI.Toolbar;

import java.awt.*;
import javax.swing.*;

import tomentme.AssetsManager.AssetManager;
import tomentme.AssetsManager.AssetManager.SpritesAssets;
import tomentme.GUI.Toolbar.Elements.ChangeEditModeButton;
import tomentme.GUI.Toolbar.Elements.ItemInPalette;
import tomentme.TomentEditor.EditMode;

public class PalettePanel 
{
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
        
        ChangeEditModeButton editWall = new ChangeEditModeButton("WALL", EditMode.WALL);
        editWall.setPreferredSize(new Dimension(60, 20));
        paletteButtons.add(editWall);

        ChangeEditModeButton editSprites = new ChangeEditModeButton("SPRITES", EditMode.SPRITE);
        editSprites.setPreferredSize(new Dimension(60, 20));
        paletteButtons.add(editSprites);

        ChangeEditModeButton editAI = new ChangeEditModeButton("AI", EditMode.AI);
        editAI.setPreferredSize(new Dimension(30, 20));
        paletteButtons.add(editAI);

        ChangeEditModeButton editFloorCeiling = new ChangeEditModeButton("F/C", EditMode.FLOOR_CEILING);
        editFloorCeiling.setPreferredSize(new Dimension(30, 20));
        paletteButtons.add(editFloorCeiling);
        
        JPanel psContent = new JPanel();
        psContent.setLayout(new BorderLayout());
        psContent.setBackground(Color.yellow);
        
        JPanel scrollContentPanel = new JPanel(new GridLayout(0,2));
        JScrollPane scrollFrame = new JScrollPane(scrollContentPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFrame.getVerticalScrollBar().setUnitIncrement(8);
        scrollFrame.setPreferredSize(new Dimension( 222,140));
        scrollFrame.setAutoscrolls(true);
        scrollContentPanel.setBorder(null);
        scrollFrame.setBorder(null);

        // Fill items
        ItemInPalette barrel = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_Barrel1.ordinal()], "ID: " + SpritesAssets.S_Barrel1.ordinal(), "Barrel");
        scrollContentPanel.add(barrel);

        ItemInPalette campfire = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_Campfire.ordinal()], "ID: " + SpritesAssets.S_Campfire.ordinal(), "Campfire");
        scrollContentPanel.add(campfire);

        ItemInPalette axePickup = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_PickupAxe.ordinal()], "ID: " + SpritesAssets.S_PickupAxe.ordinal(), "P_Axe");
        scrollContentPanel.add(axePickup);

        ItemInPalette hpotionPickup = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_PickupHealthPotion.ordinal()], "ID: " + SpritesAssets.S_PickupHealthPotion.ordinal(), "Health  ");
        scrollContentPanel.add(hpotionPickup);

        ItemInPalette mpotionPickup = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_PickupManaPotion.ordinal()], "ID: " + SpritesAssets.S_PickupManaPotion.ordinal(), "Mana");
        scrollContentPanel.add(mpotionPickup);

        ItemInPalette tomeFireball = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_TomeFireball1.ordinal()], "ID: " + SpritesAssets.S_TomeFireball1.ordinal(), "TFireball");
        scrollContentPanel.add(tomeFireball);

        ItemInPalette tomeIDart = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_TomeIceDart1.ordinal()], "ID: " + SpritesAssets.S_TomeIceDart1.ordinal(), "TIDart");
        scrollContentPanel.add(tomeIDart);

        ItemInPalette table1 = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_Table1.ordinal()], "ID: " + SpritesAssets.S_Table1.ordinal(), "Table 1");
        scrollContentPanel.add(table1);

        ItemInPalette skull = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_SkullStatic.ordinal()], "ID: " + SpritesAssets.S_SkullStatic.ordinal(), "Skull");
        scrollContentPanel.add(skull);

        ItemInPalette altarEmpty = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_AltarEmpty.ordinal()], "ID: " + SpritesAssets.S_AltarEmpty.ordinal(), "Altar E");
        scrollContentPanel.add(altarEmpty);

        ItemInPalette altarHealth = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_AltarHealth.ordinal()], "ID: " + SpritesAssets.S_AltarHealth.ordinal(), "Altar H");
        scrollContentPanel.add(altarHealth);

        ItemInPalette altarMana = new ItemInPalette(AssetManager.instance.sprites[SpritesAssets.S_AltarMana.ordinal()], "ID: " + SpritesAssets.S_AltarMana.ordinal(), "Altar M");
        scrollContentPanel.add(altarMana);

        psContent.add(scrollFrame);
        toolSections.add(paletteSelection);
        palettePanel.add(paletteButtons);
        palettePanel.add(psContent);
        toolSections.add(palettePanel);
    }
}

package tomentme.AssetsManager;

import javax.swing.ImageIcon;

import tomentme.Main;

import java.io.*;
import java.awt.Image;
import java.awt.image.*;
import javax.imageio.*;

public class AssetManager 
{
    public static final int OBJECTARRAY_DEFAULT_SIZE = 256;

    public static AssetManager instance;
    public static AssetManager GetAssetManager()
    {
        return instance;
    }

    public enum TextureIDs
    {
        EMPTY,
        WallBrick1,
        WallBrick1Dark,
        FloorBrick1,
        CeilingWood1,
        Wall2,
        Gate1,
        Gate1Alt,
        CastleDoor,
        Wall1Ladder,
        FloorBrick2,
        FloorDirt1
    };

    public enum MapEDTextureIDs
    {
        EMPTY,
        Wall,
        DoorHor,
        DoorVer,
        LadderUp,
        LadderDown,
        Sprite,
        AI,
        InvisibleWall,
        Teleporter,
        PlayerStart
    };

    public enum WallAssets
    {
        EMPTY,
        W_Wall,
        W_ThinWallHor,
        W_ThinWallVer,
        W_DoorHor,
        W_DoorVer,
        W_WallTriggerChangeMap,
        W_WallLadder,
        W_WallLadderDown,
        W_WallInvisible
    };

    public enum SpritesAssets
    {
        // 0 = Empty
        S_EMPTY,
        S_Barrel1,
        S_Campfire,
        DS_Skeleton,
        S_Fireball1,
        S_PickupAxe,
        S_PickupHealthPotion,
        S_PickupManaPotion,
        S_IceDart1,
        S_TomeFireball1,
        S_TomeIceDart1,
        S_Table1,
        S_SkullStatic,
        DS_SkeletonElite,
        S_AltarEmpty,
        S_AltarHealth,
        S_AltarMana,
        DS_SkeletonBurnt,
        S_PickupGreatsword,
        DS_SkeletonLord
    }

    // Map-Editor specific
    public ImageIcon[] mapEditorViewport = new ImageIcon[OBJECTARRAY_DEFAULT_SIZE];
    public ImageIcon[] mapEditorPalette = new ImageIcon[OBJECTARRAY_DEFAULT_SIZE];

    // TomentRaycaster
    public ImageIcon[] textures = new ImageIcon[OBJECTARRAY_DEFAULT_SIZE];
    public ImageIcon[] sprites = new ImageIcon[OBJECTARRAY_DEFAULT_SIZE];


    public void InitializeAssetManager()
    {
        LoadMapEditorViewport();
        LoadMapEditorPalette();

        LoadSprites();
    }

    private void LoadMapEditorViewport()
    {
        BufferedImage imageBuff;
        try 
        {
            // ME_Wall
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_wall.bmp")));
            mapEditorViewport[MapEDTextureIDs.Wall.ordinal()] = new ImageIcon(imageBuff);

            // ME_DoorHor
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_doorhor.bmp")));
            mapEditorViewport[MapEDTextureIDs.DoorHor.ordinal()] = new ImageIcon(imageBuff);

            // ME_DoorVer
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_doorver.bmp")));
            mapEditorViewport[MapEDTextureIDs.DoorVer.ordinal()] = new ImageIcon(imageBuff);

            // ME_LadderUp
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_ladderup.bmp")));
            mapEditorViewport[MapEDTextureIDs.LadderUp.ordinal()] = new ImageIcon(imageBuff);

            // ME_LadderDown
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_ladderdown.bmp")));
            mapEditorViewport[MapEDTextureIDs.LadderDown.ordinal()] = new ImageIcon(imageBuff);

            // ME_Sprite
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_sprite.bmp")));
            mapEditorViewport[MapEDTextureIDs.Sprite.ordinal()] = new ImageIcon(imageBuff);

            // ME_AI
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_ai.bmp")));
            mapEditorViewport[MapEDTextureIDs.AI.ordinal()] = new ImageIcon(imageBuff);

            // ME_InvisibleWall
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_invisiblewall.bmp")));
            mapEditorViewport[MapEDTextureIDs.InvisibleWall.ordinal()] = new ImageIcon(imageBuff);

            // ME_Teleporter
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_teleporter.bmp")));
            mapEditorViewport[MapEDTextureIDs.Teleporter.ordinal()] = new ImageIcon(imageBuff);

            // ME_PlayerStart
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_playerstart.bmp")));
            mapEditorViewport[MapEDTextureIDs.PlayerStart.ordinal()] = new ImageIcon(imageBuff);

        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    private void LoadMapEditorPalette()
    {
        BufferedImage imageBuff;
        try 
        {
            // ME_Wall
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_palette_wall.bmp")));
            mapEditorPalette[WallAssets.W_Wall.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_palette_wall_thin_hor.bmp")));
            mapEditorPalette[WallAssets.W_ThinWallHor.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_palette_wall_thin_ver.bmp")));
            mapEditorPalette[WallAssets.W_ThinWallVer.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_palette_door_hor.bmp")));
            mapEditorPalette[WallAssets.W_DoorHor.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_palette_door_ver.bmp")));
            mapEditorPalette[WallAssets.W_DoorVer.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_palette_wall_trigger.bmp")));
            mapEditorPalette[WallAssets.W_WallTriggerChangeMap.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_palette_ladder_up.bmp")));
            mapEditorPalette[WallAssets.W_WallLadder.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_palette_ladder_down.bmp")));
            mapEditorPalette[WallAssets.W_WallLadderDown.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_palette_invisiblewall.bmp")));
            mapEditorPalette[WallAssets.W_WallInvisible.ordinal()] = new ImageIcon(imageBuff);
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    private void LoadSprites()
    {
        BufferedImage imageBuff;
        try 
        {
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/barrel.bmp")));
            sprites[SpritesAssets.S_Barrel1.ordinal()] = new ImageIcon(new ImageIcon(imageBuff).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_campfire.bmp")));
            sprites[SpritesAssets.S_Campfire.ordinal()] = new ImageIcon(new ImageIcon(imageBuff).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_axe_pickup_anim.bmp")));
            sprites[SpritesAssets.S_PickupAxe.ordinal()] = new ImageIcon(new ImageIcon(imageBuff).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_pickup_health_potion.bmp")));
            sprites[SpritesAssets.S_PickupHealthPotion.ordinal()] = new ImageIcon(new ImageIcon(imageBuff).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_pickup_mana_potion.bmp")));
            sprites[SpritesAssets.S_PickupManaPotion.ordinal()] = new ImageIcon(new ImageIcon(imageBuff).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_tome_fireball01.bmp")));
            sprites[SpritesAssets.S_TomeFireball1.ordinal()] = new ImageIcon(new ImageIcon(imageBuff).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_tome_icedart01.bmp")));
            sprites[SpritesAssets.S_TomeIceDart1.ordinal()] = new ImageIcon(new ImageIcon(imageBuff).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/table1.bmp")));
            sprites[SpritesAssets.S_Table1.ordinal()] = new ImageIcon(new ImageIcon(imageBuff).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_skull_static.bmp")));
            sprites[SpritesAssets.S_SkullStatic.ordinal()] = new ImageIcon(new ImageIcon(imageBuff).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/altar_empty.bmp")));
            sprites[SpritesAssets.S_AltarEmpty.ordinal()] = new ImageIcon(new ImageIcon(imageBuff).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/altar_health.bmp")));
            sprites[SpritesAssets.S_AltarHealth.ordinal()] = new ImageIcon(new ImageIcon(imageBuff).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/altar_mana.bmp")));
            sprites[SpritesAssets.S_AltarMana.ordinal()] = new ImageIcon(new ImageIcon(imageBuff).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/skeleton.bmp")));
            sprites[SpritesAssets.DS_Skeleton.ordinal()] = new ImageIcon(new ImageIcon(imageBuff).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/skeleton_burnt.bmp")));
            sprites[SpritesAssets.DS_SkeletonBurnt.ordinal()] = new ImageIcon(new ImageIcon(imageBuff).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/skeleton_elite.bmp")));
            sprites[SpritesAssets.DS_SkeletonElite.ordinal()] = new ImageIcon(new ImageIcon(imageBuff).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/skeleton_lord.bmp")));
            sprites[SpritesAssets.DS_SkeletonLord.ordinal()] = new ImageIcon(new ImageIcon(imageBuff).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}

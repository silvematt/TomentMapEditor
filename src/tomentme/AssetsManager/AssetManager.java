package tomentme.AssetsManager;

import javax.swing.ImageIcon;

import tomentme.Main;

import java.io.*;
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
    public ImageIcon[] mapED = new ImageIcon[OBJECTARRAY_DEFAULT_SIZE];

    // TomentRaycaster
    public ImageIcon[] textures = new ImageIcon[OBJECTARRAY_DEFAULT_SIZE];

    public void InitializeAssetManager()
    {
        LoadMapED();
    }

    private void LoadMapED()
    {
        BufferedImage imageBuff;
        try 
        {
            // ME_Wall
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_wall.bmp")));
            mapED[MapEDTextureIDs.Wall.ordinal()] = new ImageIcon(imageBuff);

            // ME_DoorHor
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_doorhor.bmp")));
            mapED[MapEDTextureIDs.DoorHor.ordinal()] = new ImageIcon(imageBuff);

            // ME_DoorVer
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_doorver.bmp")));
            mapED[MapEDTextureIDs.DoorVer.ordinal()] = new ImageIcon(imageBuff);

            // ME_LadderUp
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_ladderup.bmp")));
            mapED[MapEDTextureIDs.LadderUp.ordinal()] = new ImageIcon(imageBuff);

            // ME_LadderDown
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_ladderdown.bmp")));
            mapED[MapEDTextureIDs.LadderDown.ordinal()] = new ImageIcon(imageBuff);

            // ME_Sprite
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_sprite.bmp")));
            mapED[MapEDTextureIDs.Sprite.ordinal()] = new ImageIcon(imageBuff);

            // ME_AI
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_ai.bmp")));
            mapED[MapEDTextureIDs.AI.ordinal()] = new ImageIcon(imageBuff);

            // ME_InvisibleWall
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_invisiblewall.bmp")));
            mapED[MapEDTextureIDs.InvisibleWall.ordinal()] = new ImageIcon(imageBuff);

            // ME_Teleporter
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_teleporter.bmp")));
            mapED[MapEDTextureIDs.Teleporter.ordinal()] = new ImageIcon(imageBuff);

            // ME_PlayerStart
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_playerstart.bmp")));
            mapED[MapEDTextureIDs.PlayerStart.ordinal()] = new ImageIcon(imageBuff);

        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}

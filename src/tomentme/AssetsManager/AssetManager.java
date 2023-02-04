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
        DoorVer
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

        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}

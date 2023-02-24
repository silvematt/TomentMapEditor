package tomentme.AssetsManager;

import javax.swing.ImageIcon;

import tomentme.Main;

import java.io.*;
import java.awt.Image;
import java.awt.image.*;
import javax.imageio.*;

/*
 * Class to manage assets as textures, it is an implementation of the Flyweight pattern, loads things once and use them throughout the program.
 */
public class AssetManager 
{
    // Default array size
    public static final int OBJECTARRAY_DEFAULT_SIZE = 256;

    // Singleton
    public static AssetManager instance;
    public static AssetManager GetAssetManager()
    {
        return instance;
    }

    // Texture IDs the same as TomentRaycaster
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

    // Texture IDs for MapEditor stuff
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
        PlayerStart,
        ThinHor,
        ThinVer
    };

    // Walls IDs the same as TomentRaycaster
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
        W_WallInvisible;

        // Converts the number in a name
        public static String GetEnumName(int id)
        {
            switch(id)
            {
                case 0:
                    return "EMPTY";

                case 1:
                    return "WALL";

                case 2:
                    return "THIN H";

                case 3:
                    return "THIN V";
                
                case 4:
                    return "DOOR H";

                case 5:
                    return "DOOR V";

                case 6:
                    return "TRIGGER";

                case 7:
                    return "LADDER U";

                case 8:
                    return "LADDER D";

                case 9:
                    return "INVISIBLE";

                default:
                    return "NULL";
            }
        }
    };

    // Sprites ID the same as TomentRaycaster
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
        DS_SkeletonLord;

        // Converts the number in a name
        public static String GetEnumName(int id)
        {
            switch(id)
            {
                case 0:
                    return "EMPTY";

                case 1:
                    return "Barrel";

                case 2:
                    return "Campfire";

                case 3:
                    return "[AI] Skeleton";

                case 4:
                    return "Fireball";
                
                case 5:
                    return "Pickup Axe";

                case 6:
                    return "Pickup Health";

                case 7:
                    return "Pickup Mana";

                case 8:
                    return "Ice Dart";

                case 9:
                    return "Tome Fireball";

                case 10:
                    return "Tome Ice Dart";

                case 11:
                    return "Table";

                case 12:
                    return "Skull";

                case 13:
                    return "Skeleton Elite";

                case 14:
                    return "Altar Empty";

                case 15:
                    return "Altar Health";

                case 16:
                    return "Altar Mana";

                case 17:
                    return "Skeleton Burnt";

                case 18:
                    return "Pickup Greatsword";

                case 19:
                    return "Skeleton Lord";

                default:
                    return "NULL";
            }
        }
    }

    // Map-Editor specific
    public ImageIcon[] mapEditorViewport = new ImageIcon[OBJECTARRAY_DEFAULT_SIZE];
    public ImageIcon[] mapEditorPalette = new ImageIcon[OBJECTARRAY_DEFAULT_SIZE];

    // TomentRaycaster
    public ImageIcon[] textures = new ImageIcon[OBJECTARRAY_DEFAULT_SIZE];
    public ImageIcon[] sprites = new ImageIcon[OBJECTARRAY_DEFAULT_SIZE];


    // Initializes the asset manager
    public void InitializeAssetManager()
    {
        // Loads all the elements one at time
        LoadMapEditorViewport();
        LoadMapEditorPalette();

        LoadTexures();
        LoadSprites();
    }

    // Load viewport related stuff
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

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_palette_wall_thin_hor.bmp")));
            mapEditorViewport[MapEDTextureIDs.ThinHor.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_palette_wall_thin_ver.bmp")));
            mapEditorViewport[MapEDTextureIDs.ThinVer.ordinal()] = new ImageIcon(imageBuff);

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    // Load palette related stuff
    private void LoadMapEditorPalette()
    {
        BufferedImage imageBuff;
        try 
        {
             // ME_EMPTY
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_palette_empty.bmp")));
            mapEditorPalette[MapEDTextureIDs.EMPTY.ordinal()] = new ImageIcon(imageBuff);

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
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    // Load all textures
    private void LoadTexures()
    {
        BufferedImage imageBuff;
        try 
        {
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/wall1.bmp")));
            textures[TextureIDs.WallBrick1.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/wall1alt.bmp")));
            textures[TextureIDs.WallBrick1Dark.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/floor.bmp")));
            textures[TextureIDs.FloorBrick1.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/ceiling1.bmp")));
            textures[TextureIDs.CeilingWood1.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/wall2.bmp")));
            textures[TextureIDs.Wall2.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/gate.bmp")));
            textures[TextureIDs.Gate1.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/gateAlt.bmp")));
            textures[TextureIDs.Gate1Alt.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/castledoors.bmp")));
            textures[TextureIDs.CastleDoor.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/wall1_ladder.bmp")));
            textures[TextureIDs.Wall1Ladder.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/floor2.bmp")));
            textures[TextureIDs.FloorBrick2.ordinal()] = new ImageIcon(imageBuff);

            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/floor_dirt.bmp")));
            textures[TextureIDs.FloorDirt1.ordinal()] = new ImageIcon(imageBuff);

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    // Load all sprites
    private void LoadSprites()
    {
        BufferedImage imageBuff;
        try 
        {
            // ME_EMPTY
            imageBuff = ImageIO.read(new File(Main.path + ("/Data/resources/me_palette_empty.bmp")));
            sprites[SpritesAssets.S_EMPTY.ordinal()] = new ImageIcon(imageBuff);

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
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}

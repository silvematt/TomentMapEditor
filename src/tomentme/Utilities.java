package tomentme;

import tomentme.Map.TMap;
import tomentme.Map.WallObject;

/*
 * Implements useful static methods
 */
public class Utilities 
{
    // Gets the sprite at (y,x) from the requested floor
    public static int GetSpriteFromMap(int curFloor, int y, int x)
    {
        TMap curMap = TomentEditor.instance.currentMap;
        
        switch(curFloor)
        {
            case 0:
                return curMap.spritesMapLevel0[y][x];

            case 1:
                return curMap.spritesMapLevel1[y][x];

            case 2:
                return curMap.spritesMapLevel2[y][x];

            default:
                return curMap.spritesMapLevel0[y][x];
        }
    }

    // Gets the wall at (y,x) from the requested floor
    public static WallObject GetWallFromMap(int curFloor, int y, int x)
    {
        TMap curMap = TomentEditor.instance.currentMap;

        WallObject wallObj;
        switch(curFloor)
        {
            case 0:
                wallObj = curMap.level0[y][x];
                break;

            case 1:
                wallObj = curMap.level1[y][x];
                break;

            case 2:
                wallObj = curMap.level2[y][x];
                break;

            default:
                wallObj = curMap.level0[y][x];
                break;
        }

        return wallObj;
    }

    // Sets the sprite at (y,x) from the requested floor
    public static void SetInSpritesMap(int curFloor, int y, int x, int val)
    {
        TMap curMap = TomentEditor.instance.currentMap;

        switch(curFloor)
        {
            case 0:
                curMap.spritesMapLevel0[y][x] = val;
                break;

            case 1:
                curMap.spritesMapLevel1[y][x] = val;
                break;

            case 2:
                curMap.spritesMapLevel2[y][x] = val;
                break;

            default:
                curMap.spritesMapLevel0[y][x] = val;
                break;
        }
    }

    // Parses a wall at (y,x) from the requested floor
    public static void SetWallParse(int curFloor, int y, int x, WallObject wallToParse)
    {
        TMap curMap = TomentEditor.instance.currentMap;

        switch(TomentEditor.instance.GetCurrentFloor())
        {
            case 0:
                curMap.level0[y][x].PasteValues(wallToParse);
                break;

            case 1:
                curMap.level1[y][x].PasteValues(wallToParse);
                break;

            case 2:
                curMap.level2[y][x].PasteValues(wallToParse);
                break;

            default:
                curMap.level0[y][x].PasteValues(wallToParse);
                break;
        }
    }
}

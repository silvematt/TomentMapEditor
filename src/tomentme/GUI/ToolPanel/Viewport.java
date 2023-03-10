package tomentme.GUI.ToolPanel;

import java.awt.*;

import javax.swing.*;

import tomentme.TomentEditor;
import tomentme.AssetsManager.AssetManager;
import tomentme.AssetsManager.AssetManager.*;
import tomentme.GUI.Elements.*;

import tomentme.Map.*;

/*
 * Represent the Viewport located in the ToolsPanel
 */
public class Viewport 
{
    // Tiles
    public TileButton[][] tiles = new TileButton[TMap.MAP_HEIGHT][TMap.MAP_WIDTH];

    // Constructor
    public Viewport(JPanel content)
    {
        JPanel viewport = new JPanel(new GridLayout(24, 24));
        viewport.setBackground(Color.GRAY);

        // Create and set all tiles
        for(int i = 0; i < TMap.MAP_HEIGHT; i++)
            for(int j = 0; j < TMap.MAP_WIDTH; j++)
            {
                TileButton btn = new TileButton(j,i);
                btn.setBackground(Color.gray);
                btn.setBorder(TileButton.notSelectedBorder);
                viewport.add(btn);

                tiles[j][i] = btn;
            }

        content.add(viewport);
    }

    // Updates the panel, drawing all that changed
    public void UpdateViewport()
    {
        TMap currentMap = TomentEditor.instance.currentMap;
        WallObject wallMap[][];
        int spritesMap[][];

        int currentFloor = TomentEditor.instance.GetCurrentFloor();

        // Select the correct floor to draw
        switch(currentFloor)
        {
            case 0: 
                wallMap = currentMap.level0; 
                spritesMap = currentMap.spritesMapLevel0;
                break;

            case 1: 
                wallMap = currentMap.level1; 
                spritesMap = currentMap.spritesMapLevel1;
                break;

            case 2: 
                wallMap = currentMap.level2; 
                spritesMap = currentMap.spritesMapLevel2;
                break;
            
            default:
                wallMap = currentMap.level0; 
                spritesMap = currentMap.spritesMapLevel0;
                break;
        }

        // Get through all the tilemap and draw the appropriate tile image
        for(int y = 0; y < TMap.MAP_HEIGHT; y++)
            for(int x = 0; x < TMap.MAP_WIDTH; x++)
            {
                // Clear the button
                tiles[x][y].setIcon(null);

                int wallID = wallMap[y][x].assetID;
                
                // Render Wall Map
                if(wallID > WallAssets.EMPTY.ordinal())
                {
                    if(wallID == WallAssets.W_DoorHor.ordinal())
                        tiles[x][y].setIcon(AssetManager.instance.mapEditorViewport[MapEDTextureIDs.DoorHor.ordinal()]);
                    else if(wallID == WallAssets.W_DoorVer.ordinal())
                        tiles[x][y].setIcon(AssetManager.instance.mapEditorViewport[MapEDTextureIDs.DoorVer.ordinal()]);
                    else if(wallID == WallAssets.W_WallLadder.ordinal())
                        tiles[x][y].setIcon(AssetManager.instance.mapEditorViewport[MapEDTextureIDs.LadderUp.ordinal()]);
                    else if(wallID == WallAssets.W_WallLadderDown.ordinal())
                        tiles[x][y].setIcon(AssetManager.instance.mapEditorViewport[MapEDTextureIDs.LadderDown.ordinal()]);
                    else if(wallID == WallAssets.W_WallInvisible.ordinal())
                        tiles[x][y].setIcon(AssetManager.instance.mapEditorViewport[MapEDTextureIDs.InvisibleWall.ordinal()]);
                    else if(wallID == WallAssets.W_WallTriggerChangeMap.ordinal())
                        tiles[x][y].setIcon(AssetManager.instance.mapEditorViewport[MapEDTextureIDs.Teleporter.ordinal()]);
                    else if(wallID == WallAssets.W_ThinWallHor.ordinal())
                        tiles[x][y].setIcon(AssetManager.instance.mapEditorViewport[MapEDTextureIDs.ThinHor.ordinal()]);
                    else if(wallID == WallAssets.W_ThinWallVer.ordinal())
                        tiles[x][y].setIcon(AssetManager.instance.mapEditorViewport[MapEDTextureIDs.ThinVer.ordinal()]);
                    else
                        tiles[x][y].setIcon(AssetManager.instance.mapEditorViewport[MapEDTextureIDs.Wall.ordinal()]);
                }

                // Render Sprites Map
                int spriteID = spritesMap[y][x];

                if(spriteID > SpritesAssets.S_EMPTY.ordinal())
                {
                    // Check for AIs
                    if(spriteID == SpritesAssets.DS_Skeleton.ordinal() || spriteID == SpritesAssets.DS_SkeletonBurnt.ordinal() ||
                        spriteID == SpritesAssets.DS_SkeletonElite.ordinal() || spriteID == SpritesAssets.DS_SkeletonLord.ordinal())
                        {
                            tiles[x][y].setIcon(AssetManager.instance.mapEditorViewport[MapEDTextureIDs.AI.ordinal()]);
                        }
                        else
                            tiles[x][y].setIcon(AssetManager.instance.mapEditorViewport[MapEDTextureIDs.Sprite.ordinal()]);
                }

                // Render Player Spawn
                if(currentMap.playerStartingLevel == currentFloor && x == currentMap.playerStartingGridX && y == currentMap.playerStartingGridY)
                    tiles[x][y].setIcon(AssetManager.instance.mapEditorViewport[MapEDTextureIDs.PlayerStart.ordinal()]);
            }
    }
}

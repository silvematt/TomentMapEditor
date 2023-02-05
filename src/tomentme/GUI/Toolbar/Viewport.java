package tomentme.GUI.Toolbar;

import java.awt.*;
import javax.swing.*;

import tomentme.TomentEditor;
import tomentme.AssetsManager.AssetManager;
import tomentme.AssetsManager.AssetManager.MapEDTextureIDs;
import tomentme.AssetsManager.AssetManager.WallAssets;
import tomentme.GUI.*;
import tomentme.GUI.Elements.*;

import tomentme.Map.*;

public class Viewport 
{

    public TileButton[][] tiles = new TileButton[TMap.MAP_HEIGHT][TMap.MAP_WIDTH];

    public Viewport(JPanel content)
    {
        JPanel viewport = new JPanel(new GridLayout(24, 24));
        viewport.setBackground(Color.GRAY);

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

    public void UpdateViewport()
    {
        WallObject map[][];

        switch(TomentEditor.instance.GetCurrentFloor())
        {
            case 0: 
                map = TomentEditor.instance.currentMap.level0; 
                break;

            case 1: 
                map = TomentEditor.instance.currentMap.level1; 
                break;

            case 2: 
                map = TomentEditor.instance.currentMap.level2; 
                break;
            
            default:
                map = TomentEditor.instance.currentMap.level0; 
                break;
        }

        for(int y = 0; y < TMap.MAP_HEIGHT; y++)
            for(int x = 0; x < TMap.MAP_WIDTH; x++)
            {
                // Clear the button
                tiles[x][y].setIcon(null);

                int aID = map[y][x].assetID;

                if(aID > WallAssets.EMPTY.ordinal())
                {
                    if(aID == WallAssets.W_DoorHor.ordinal())
                        tiles[x][y].setIcon(AssetManager.instance.mapED[MapEDTextureIDs.DoorHor.ordinal()]);
                    else if(aID == WallAssets.W_DoorVer.ordinal())
                        tiles[x][y].setIcon(AssetManager.instance.mapED[MapEDTextureIDs.DoorVer.ordinal()]);
                    else if(aID == WallAssets.W_WallLadder.ordinal())
                        tiles[x][y].setIcon(AssetManager.instance.mapED[MapEDTextureIDs.LadderUp.ordinal()]);
                    else if(aID == WallAssets.W_WallLadderDown.ordinal())
                        tiles[x][y].setIcon(AssetManager.instance.mapED[MapEDTextureIDs.LadderDown.ordinal()]);
                    else
                        tiles[x][y].setIcon(AssetManager.instance.mapED[MapEDTextureIDs.Wall.ordinal()]);
                }
            }
    }
}

package tomentme;

import tomentme.GUI.Elements.TileButton;

public final class TomentME 
{
    // Defines
    public static final int DEF_WINDOW_W = 800;
    public static final int DEF_WINDOW_H = 600;

    // Application Related
    private static TileButton selectedTile;

    public void SetSelectedTile(TileButton _btn)
    {
        selectedTile = _btn;
        
    }
}

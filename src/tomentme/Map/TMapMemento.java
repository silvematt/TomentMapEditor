package tomentme.Map;

/*
 * Implementation of the Memento pattern to save the state of a map and allow undo
 */
public class TMapMemento
{
    private TMap state;

    // Constructor catches the state
    public TMapMemento(TMap toSave)
    {
        state = new TMap();

        for(int y = 0; y < TMap.MAP_HEIGHT; y++)
            for(int x = 0; x < TMap.MAP_WIDTH; x++)
            {
                state.level0[y][x].PasteValues(toSave.level0[y][x]);
                state.level1[y][x].PasteValues(toSave.level1[y][x]);
                state.level2[y][x].PasteValues(toSave.level2[y][x]);

                state.spritesMapLevel0[y][x] = toSave.spritesMapLevel0[y][x];
                state.spritesMapLevel1[y][x] = toSave.spritesMapLevel1[y][x];
                state.spritesMapLevel2[y][x] = toSave.spritesMapLevel2[y][x];

                state.playerStartingGridX = toSave.playerStartingGridX;
                state.playerStartingGridY = toSave.playerStartingGridY;
                state.playerStartingLevel = toSave.playerStartingLevel;
            }
    }

    public TMap GetState()
    {
        return state;
    }
}

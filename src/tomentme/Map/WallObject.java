package tomentme.Map;

public class WallObject 
{
    public static int TEXTURE_ARRAY_TOP =  0;
    public static int TEXTURE_ARRAY_BOTTOM = 1;
    public static int TEXTURE_ARRAY_LEFT = 2;
    public static int TEXTURE_ARRAY_RIGHT = 3;
    public static int TEXTURE_ARRAY_UP = 4;
    public static int TEXTURE_ARRAY_DOWN = 5;

    public int assetID;
    public int[] textureArray; 
    public String data;

    public WallObject()
    {
        this.assetID = 0;
        this.textureArray = new int[6];
        this.data = new String();
    }

    public void PasteValues(WallObject other)
    {
        this.assetID = other.assetID;
        
        for(int i = 0; i < textureArray.length; i++)
            this.textureArray[i] = other.textureArray[i];

        this.data = new String(other.data);
    }
}

package tomentme.Map;

import tomentme.Main;

import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TMap 
{
    public static final int MAP_WIDTH = 24; 
    public static final int MAP_HEIGHT = 24;

    private String ID;
    private String displayName;
    
    public int playerStartingLevel;
    public int playerStartingGridX;
    public int playerStartingGridY;
    private float playerStartingRot;

    public WallObject[][] level0 = new WallObject[MAP_HEIGHT][MAP_WIDTH];
    public WallObject[][] level1 = new WallObject[MAP_HEIGHT][MAP_WIDTH];
    public WallObject[][] level2 = new WallObject[MAP_HEIGHT][MAP_WIDTH];

    private int floorMap[][] = new int[MAP_HEIGHT][MAP_WIDTH];
    private int ceilingMap[][] = new int[MAP_HEIGHT][MAP_WIDTH];

    public int spritesMapLevel0[][] = new int[MAP_HEIGHT][MAP_WIDTH];
    public int spritesMapLevel1[][] = new int[MAP_HEIGHT][MAP_WIDTH];
    public int spritesMapLevel2[][] = new int[MAP_HEIGHT][MAP_WIDTH];

    private float wallLight;
    private float floorLight;
    private int skyID;

    private boolean hasAbsCeiling;
    private int absCeilingLevel;

    public TMap()
    {
        for(int y=0; y<24; y++)
            for(int x=0; x<24; x++)
            {
                this.level0[y][x] = new WallObject();
                this.level1[y][x] = new WallObject();
                this.level2[y][x] = new WallObject();
            }
    }

    public void LoadMap(String _id)
    {
        ID = _id;

        // Load all
        String path = Main.path + "/Data/maps/";
        
        // Get MapID path
        path = path.concat(ID);
        path = path.concat(".tmap");

        System.out.println("Loading map... " + path);

        // Read file
        try 
        {
            File fileObj = new File(path);
            Scanner fileScanner = new Scanner(fileObj);

            String curLine; // current value in file
            String str;     // current value to extract

            // Read ID
            curLine = fileScanner.nextLine();
            str = (curLine.substring(curLine.lastIndexOf("=") + 1));
            str = str.substring(0, str.indexOf(";"));

            this.ID = str;

            // Read Name
            curLine = fileScanner.nextLine();
            str = (curLine.substring(curLine.lastIndexOf("=") + 1));
            str = str.substring(0, str.indexOf(";"));

            this.displayName = str;

            // Read Player Level
            curLine = fileScanner.nextLine();
            str = (curLine.substring(curLine.lastIndexOf("=") + 1));
            str = str.substring(0, str.indexOf(";"));

            this.playerStartingLevel = Integer.parseInt(str);

            // Read Player Starting GridX
            curLine = fileScanner.nextLine();
            str = (curLine.substring(curLine.lastIndexOf("=") + 1));
            str = str.substring(0, str.indexOf(";"));

            this.playerStartingGridX = Integer.parseInt(str);

            // Read Player Starting GridY
            curLine = fileScanner.nextLine();
            str = (curLine.substring(curLine.lastIndexOf("=") + 1));
            str = str.substring(0, str.indexOf(";"));

            this.playerStartingGridY = Integer.parseInt(str);

            // Read Player Starting Rot
            curLine = fileScanner.nextLine();
            str = (curLine.substring(curLine.lastIndexOf("=") + 1));
            str = str.substring(0, str.indexOf(";"));

            this.playerStartingRot = Float.parseFloat(str);

            // Read Wall maps
            ReadWallMapFromFile(fileScanner, this.level0);
            ReadWallMapFromFile(fileScanner, this.level1);
            ReadWallMapFromFile(fileScanner, this.level2);

            // Read Floor Map
            ReadMapFromFile(fileScanner, this.floorMap);

            // Read Ceiling Map
            ReadMapFromFile(fileScanner, this.ceilingMap);

            // Read Sprites maps
            ReadMapFromFile(fileScanner, this.spritesMapLevel0);
            ReadMapFromFile(fileScanner, this.spritesMapLevel1);
            ReadMapFromFile(fileScanner, this.spritesMapLevel2);

            // Read wall lighting
            curLine = fileScanner.nextLine();
            str = (curLine.substring(curLine.lastIndexOf("=") + 1));
            str = str.substring(0, str.indexOf(";"));

            this.wallLight = Float.parseFloat(str);

            // Read floor lighting
            curLine = fileScanner.nextLine();
            str = (curLine.substring(curLine.lastIndexOf("=") + 1));
            str = str.substring(0, str.indexOf(";"));

            this.floorLight = Float.parseFloat(str);

            // Read SkyID
            curLine = fileScanner.nextLine();
            str = (curLine.substring(curLine.lastIndexOf("=") + 1));
            str = str.substring(0, str.indexOf(";"));

            this.skyID = Integer.parseInt(str);

            // Read HasAbsCeiling
            curLine = fileScanner.nextLine();
            str = (curLine.substring(curLine.lastIndexOf("=") + 1));
            str = str.substring(0, str.indexOf(";"));

            this.hasAbsCeiling = str == "0" ? false : true;

            // Read AbsCeilingLevel
            curLine = fileScanner.nextLine();
            str = (curLine.substring(curLine.lastIndexOf("=") + 1));
            str = str.substring(0, str.indexOf(";"));

            this.absCeilingLevel = Integer.parseInt(str);

            fileScanner.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred. File may not exist or is corrupted.");
            e.printStackTrace();
        }

        System.out.println("Map successfully loaded!");
    }

    private void ReadWallMapFromFile(Scanner fileScanner, WallObject map[][])
    {
        String curLine;

        curLine = fileScanner.nextLine(); // Layout =
        curLine = fileScanner.nextLine(); // [ start of map
        curLine = fileScanner.nextLine(); // First Row

        // Find First row

        boolean mapDone = false;
        
        int column = 0;
        int row = 0;
        int indx = 1;
        
        while(!mapDone)
        {
            while(curLine.charAt(indx) != '}')
            {
                indx++;
                int sum = 0;

                // Read AssetID
                do
                {
                    sum *= 10;
                    sum += Character.getNumericValue(curLine.charAt(indx));
                    map[column][row].assetID = sum;
                    indx++;
                } while(curLine.charAt(indx) != ',' && curLine.charAt(indx) != ')');
                indx++;

                // Read Top Texture
                sum = 0;
                do
                {
                    sum *= 10;
                    sum += Character.getNumericValue(curLine.charAt(indx));
                    map[column][row].textureArray[WallObject.TEXTURE_ARRAY_TOP] = sum; // Set int value
                    indx++;
                } while(curLine.charAt(indx) != ',' && curLine.charAt(indx) != ')');
                indx++;
                
                // Read Bottom Texture
                sum = 0;
                do
                {
                    sum *= 10;
                    sum += Character.getNumericValue(curLine.charAt(indx));
                    map[column][row].textureArray[WallObject.TEXTURE_ARRAY_BOTTOM] = sum; // Set int value
                    indx++;
                } while(curLine.charAt(indx) != ',' && curLine.charAt(indx) != ')');
                indx++;

                // Read Left Texture
                sum = 0;
                do
                {
                    sum *= 10;
                    sum += Character.getNumericValue(curLine.charAt(indx));
                    map[column][row].textureArray[WallObject.TEXTURE_ARRAY_LEFT] = sum; // Set int value
                    indx++;
                } while(curLine.charAt(indx) != ',' && curLine.charAt(indx) != ')');
                indx++;

                // Read Right Texture
                sum = 0;
                do
                {
                    sum *= 10;
                    sum += Character.getNumericValue(curLine.charAt(indx));
                    map[column][row].textureArray[WallObject.TEXTURE_ARRAY_RIGHT] = sum; // Set int value
                    indx++;
                } while(curLine.charAt(indx) != ',' && curLine.charAt(indx) != ')');
                indx++;

                // Read Forward Texture
                sum = 0;
                do
                {
                    sum *= 10;
                    sum += Character.getNumericValue(curLine.charAt(indx));
                    map[column][row].textureArray[WallObject.TEXTURE_ARRAY_UP] = sum; // Set int value
                    indx++;
                } while(curLine.charAt(indx) != ',' && curLine.charAt(indx) != ')');
                indx++;

                // Read Back Texture
                sum = 0;
                do
                {
                    sum *= 10;
                    sum += Character.getNumericValue(curLine.charAt(indx));
                    map[column][row].textureArray[WallObject.TEXTURE_ARRAY_DOWN] = sum; // Set int value
                    indx++;
                } while(curLine.charAt(indx) != ',' && curLine.charAt(indx) != ')');
                indx++;

                char[] data = new char[256];
                int dataIndex = 0;
                indx++;
                while(curLine.charAt(indx) != '"')
                {
                    data[dataIndex] = curLine.charAt(indx);
                    dataIndex++;
                    indx++;
                }
                map[column][row].data = new String(data);
                indx++;
                indx++;

                // If next is comma, continue and get next number
                if(curLine.charAt(indx) == ',')
                {
                    indx++;
                    row++;
                }
            }

            // Row end, check if there's a next row
            if(curLine.charAt(indx + 1) == ',')
            {
                // There is a next column
                column++;
                indx = 1;
                row = 0;
                curLine = fileScanner.nextLine();
                continue;
            }
            else if(curLine.charAt(indx + 1) == ']')
            {
                mapDone = true;
            }
        }
    }

    private void ReadMapFromFile(Scanner fileScanner, int map[][])
    {
        String curLine;

        curLine = fileScanner.nextLine(); // Layout =
        curLine = fileScanner.nextLine(); // [ start of map
        curLine = fileScanner.nextLine(); // First Row

        // Find First row

        boolean mapDone = false;
        
        int column = 0;
        int row = 0;
        int indx = 1;

        while(!mapDone)
        {
            while(curLine.charAt(indx) != '}')
            {
                int sum = 0;

                do
                {
                    sum *= 10;
                    sum += Character.getNumericValue(curLine.charAt(indx));
                    map[column][row] = sum;
                    indx++;
                } while(curLine.charAt(indx) != ',' && curLine.charAt(indx) != '}');

                // If next is comma, continue and get next number
                if(curLine.charAt(indx) == ',')
                {
                    indx++;
                    row++;
                }
            }

            // Row End
            if(curLine.charAt(indx+1) == ',')
            {
                column++;
                indx = 1;
                row = 0;
                curLine = fileScanner.nextLine();
                continue;
            }
            else if(curLine.charAt(indx+1) == ']')
            {
                mapDone = true;
            }
        }
    }
}

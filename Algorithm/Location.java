
/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Location) {
            Location objLoc = (Location)obj;
            if ((this.xCoord==objLoc.xCoord)&&(this.yCoord==objLoc.yCoord)) return true;
            else return false;
        }
        return false;
    }

    @Override
    public int hashCode(){
        int hash;
        hash = xCoord + 100*yCoord;
        return hash;
    }

    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }
}

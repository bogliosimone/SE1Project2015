/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map;

/**
 * Offset Coordinate X-Y
 * @author simoneboglio
 */
public class OffsetCoord
{
    private final int x;
    private final int y; 


    /**
     * Create OffsetCoord from X-Y
     * @param x coordinate
     * @param y coordinate
     */
    public OffsetCoord(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Create an Hex from offset coordinate 
     * @param hc  OffsetCoord object
     * @return new Hex object
     */
    public static Hex offsetToCube(OffsetCoord hc){
        int x = hc.x;
        int z = hc.y - (hc.x + (hc.x&1)) / 2;
        int y = -x-z;
        return new Hex(x,y,z);
    }
    /**
     * Create an OffsetCoord from an Hex
     * @param hex Hex object
     * @return  new OffsetCoord
     */
    public static OffsetCoord offsetCoordinatefromCube(Hex hex){
        int col = hex.getX();
        int row = hex.getZ() + (hex.getX() + (hex.getX()&1)) / 2;
        return new  OffsetCoord(col,row);
    }

    @Override
    public String toString(){
        return new String("Hex Offset X="+this.x+" Y="+this.y);
    }

}
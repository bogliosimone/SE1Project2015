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
     * Create an CubeCoord from offset coordinate 
     * @param hc  OffsetCoord object
     * @return new CubeCoord object
     */
    public static CubeCoord offsetToCube(OffsetCoord hc){
        int x = hc.x;
        int z = hc.y - (hc.x + (hc.x&1)) / 2;
        int y = -x-z;
        return new CubeCoord(x,y,z);
    }
    /**
     * Create an OffsetCoord from an CubeCoord
     * @param hex CubeCoord object
     * @return  new OffsetCoord
     */
    public static OffsetCoord offsetCoordinatefromCube(CubeCoord hex){
        int col = hex.getX();
        int row = hex.getZ() + (hex.getX() + (hex.getX()&1)) / 2;
        return new  OffsetCoord(col,row);
    }

    @Override
    public String toString(){
        return new String("CubeCoord Offset X="+this.x+" Y="+this.y);
    }

}
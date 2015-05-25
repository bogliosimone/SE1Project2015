/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map;

/**
 * Hexagon in cube coordinate X-Y-Z
 * @author simoneboglio
 */
public class CubeCoord
{
    private final int x;
    private final int y;
    private final int z;

    /**
     * Create CubeCoord from X-Y-Z
     * @param x coordinate
     * @param y coordinate
     * @param z coordinate
     */
    public CubeCoord(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Create CubeCoord from Coordinate
     * @param coord Coordinate
     */
    public CubeCoord(Coordinate coord){
        this.x = coord.getX();
        this.z = coord.getY()- (coord.getX()+ (coord.getX()&1)) / 2;
        this.y = -this.x-this.z;
    }

    /**
     * Get X coordinate value
     * @return  x coordinate
     */
    public int getX(){
        return this.x;
    }

    /**
     * Get Y coordinate value
     * @return  y coordinate
     */
    public int getY(){
        return this.y;
    }

    /**
     * Get Z coordinate value
     * @return  z coordinate
     */
    public int getZ(){
        return this.z;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        result = prime * result + z;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CubeCoord other = (CubeCoord) obj;
        if (x != other.x || y != other.y || z != other.z)
            return false;
        return true;
    }

    @Override
    public String toString(){
        return new String("Cube coordinate: X="+Integer.toString(this.x)+" Y="+Integer.toString(this.y)+" Z="+Integer.toString(this.z));
    }

}
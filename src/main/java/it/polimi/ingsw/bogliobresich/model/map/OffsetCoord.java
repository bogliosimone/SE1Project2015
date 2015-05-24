/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map;

/**
 * @author simoneboglio
 * 
 */
public class OffsetCoord
{
    private final int col; //number column
    private final int row; //number row

    public OffsetCoord(int col, int row)
    {
        this.col = col;
        this.row = row;
    }

    public static Hex offsetToCube(OffsetCoord h){
        int x = h.col;
        int z = h.row - (h.col + (h.col&1)) / 2;
        int y = -x-z;
        return new Hex(x,y,z);
    }
    public static OffsetCoord offsetCoordinatefromCube(Hex hex){
        int col = hex.getX();
        int row = hex.getZ() + (hex.getX() + (hex.getX()&1)) / 2;
        return new  OffsetCoord(col,row);
    }

    @Override
    public String toString(){
        return new String("Hex Offset X="+this.col+" Y="+this.row);
    }

}
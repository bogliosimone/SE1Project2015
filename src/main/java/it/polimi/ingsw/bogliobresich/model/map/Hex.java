
/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map;


import java.util.ArrayList;


/**
 * @author simoneboglio
 *
 */
public class Hex
{
    private static ArrayList<Hex> directions = new ArrayList<Hex>(){{add(new Hex(1, 0, -1)); add(new Hex(1, -1, 0)); add(new Hex(0, -1, 1)); add(new Hex(-1, 0, 1)); add(new Hex(-1, 1, 0)); add(new Hex(0, 1, -1));}};
    private static ArrayList<Hex> diagonals = new ArrayList<Hex>(){{add(new Hex(2, -1, -1)); add(new Hex(1, -2, 1)); add(new Hex(-1, -1, 2)); add(new Hex(-2, 1, 1)); add(new Hex(-1, 2, -1)); add(new Hex(1, 1, -2));}};
    private final int q;
    private final int r;
    private final int s;
    
    public Hex(int q, int r, int s)
    {
        this.q = q; //x
        this.r = r; //y
        this.s = s; //z
    }
    
    public Hex(Coordinate coord){
        this.q = coord.getX();
        this.s = coord.getY()- (coord.getX()+ (coord.getX()&1)) / 2;
        this.r = -this.q-this.s;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + q;
        result = prime * result + r;
        result = prime * result + s;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Hex other = (Hex) obj;
        if (q != other.q || r != other.r || s != other.s)
            return false;
        return true;
    }
    
    public static Hex add(Hex a, Hex b)
    {
        return new Hex(a.q + b.q, a.r + b.r, a.s + b.s);
    }


    public static Hex subtract(Hex a, Hex b)
    {
        return new Hex(a.q - b.q, a.r - b.r, a.s - b.s);
    }


    public static Hex scale(Hex a, int k)
    {
        return new Hex(a.q * k, a.r * k, a.s * k);
    }



    public static Hex direction(int direction)
    {
        return Hex.directions.get(direction);
    }


    public static Hex neighbor(Hex hex, int direction)
    {
        return Hex.add(hex, Hex.direction(direction));
    }


    public static Hex diagonalNeighbor(Hex hex, int direction)
    {
        return Hex.add(hex, Hex.diagonals.get(direction));
    }


    public static int length(Hex hex)
    {
        return (Math.abs(hex.q) + Math.abs(hex.r) + Math.abs(hex.s)) / 2;
    }


    public static int distance(Hex a, Hex b)
    {
        return Hex.length(Hex.subtract(a, b));
    }

    public int getX(){
        return this.q;
    }

    public int getY(){
        return this.r;
    }

    public int getZ(){
        return this.s;
    }
    
    @Override
    public String toString(){
        return new String("Cube coordinate: X="+Integer.toString(this.q)+" Y="+Integer.toString(this.r)+" Z="+Integer.toString(this.s));
    }

}
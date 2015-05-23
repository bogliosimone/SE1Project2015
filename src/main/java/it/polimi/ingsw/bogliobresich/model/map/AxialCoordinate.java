/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map;

/**
 * @author simoneboglio
 *
 */
public class AxialCoordinate {
    private int q;
    private int r;

    public AxialCoordinate(int q,int r){
        this.q=q;
        this.r=r;
    }

    public Hex axialCoordinateToCube (){
        return new Hex(q,-q-r,r);
    }

    public AxialCoordinate cubeToAxialCoordinate(Hex hex){
        return new AxialCoordinate(hex.getX(),hex.getZ());
    }
}
/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map;

/**
 * Axial coordinate for represent the hexagon position
 * @author simoneboglio
 */
public class AxialCoordinate {
    private int q;
    private int r;

    /**
     * Create an AxialCoordinate with q,r value
     * @param q
     * @param r
     */
    public AxialCoordinate(int q,int r){
        this.q=q;
        this.r=r;
    }


    /**
     *  Convert AxialCoordinate in CubeCoordinate
     * @return
     */
    public CubeCoord axialCoordinateToCube (){
        return new CubeCoord(this.q,-this.q-r,this.r);
    }

    /**
     * Generate a new AxialCoordinate based on CubeCoordinate
     * @param hex
     * @return
     */
    public static AxialCoordinate cubeToAxialCoordinate(CubeCoord hex){
        return new AxialCoordinate(hex.getX(),hex.getZ());
    }
}
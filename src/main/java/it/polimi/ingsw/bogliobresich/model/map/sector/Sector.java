/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map.sector;

import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

/**
 * Represent a Sector, it have Coordinate and can be crossable
 * @author simoneboglio
 */

public abstract class Sector {
    protected boolean isCrossable = false; //default a Sector can't be crossable
    protected final Coordinate coordinate;
    protected static final int MAXVALUE = Integer.MAX_VALUE; //constant act to represent the infinite value
    protected int distance = MAXVALUE; //the actual distance from another sector, used for calculate the neighbors in a map of Sector

    /**
     * Create a Sector with letter-number as coordinate 
     * @param letter
     * @param number
     */
    public Sector(char letter, int number){
        this.coordinate = new Coordinate (letter,number);
        setCrossable();
    }
    
    /**
     * Create a Sector with x-y as coordinate and the default crossable value
     * @param x
     * @param y
     */
    public Sector(int x, int y){
        this.coordinate = new Coordinate (x,y);
        setCrossable();
    }

    /**
     * Set crossable to the default value=false
     */
    protected void setCrossable (){
        this.isCrossable=false;
    }

    /**
     * Return the crossable statue of the Sector
     * @return  true if Sector is crossable, fals instead
     */
    public boolean isCrossable(){
        return this.isCrossable;
    }

    /**
     * Get the letter coordinate of the Sector
     * @return  letter coordinate of the Sector
     */
    public char getCoordianteLetter(){
        return this.coordinate.getLetter();
    }
    
    /**
     * Get the number coordinate of the Sector
     * @return  number coordinate of the Sector
     */
    public int getCoordinateNumber(){
        return this.coordinate.getNumber();
    }

    /**
     * Get the x coordinate of the Sector
     * @return  x coordinate of the Sector
     */
    public int getCoordinateX(){
        return this.coordinate.getX();
    }
    
    /**
     * Get the y coordinate of the Sector
     * @return  y coordinate of the Sector
     */
    public int getCoordinateY(){
        return this.coordinate.getY();
    }

    /**
     * Reset the distance to the default value
     */
    public void resetDistance(){
        this.distance= MAXVALUE;
    }

    /**
     * Set the distance to the distance value
     * @param distance
     */
    public void setDistance(int distance){
        this.distance= distance;
    }

    /**
     * Get the distance value
     * @return  value of distance
     */
    public int getDistance(){
        return this.distance;
    }
    
    @Override
    public String toString(){
        return new String (this.coordinate.toString()+" Crossable = "+this.isCrossable );
    }

}
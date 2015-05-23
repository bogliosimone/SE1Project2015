/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map.sector;

import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

/**
 * @author simoneboglio
 *
 */

public abstract class Sector {
    protected boolean crossable = false;
    protected final Coordinate coordinate;
    protected final int maxValue = Integer.MAX_VALUE;
    protected int distance = maxValue;

    public Sector(char letter, int number){
        this.coordinate = new Coordinate (letter,number);
        setCrossable();
    }
    
    public Sector(int x, int y){
        this.coordinate = new Coordinate (x,y);
        setCrossable();
    }

    protected void setCrossable (){
        this.crossable=false;
    }

    public boolean isCrossable(){
        return this.crossable;
    }

    public char getCoordianteLetter(){
        return this.coordinate.getLetter();
    }

    public int getCoordinateNumber(){
        return this.coordinate.getNumber();
    }

    public int getCoordinateX(){
        return this.coordinate.getX();
    }

    public int getCoordinateY(){
        return this.coordinate.getY();
    }

    public void resetDistance(){
        this.distance= maxValue;
    }

    public void setDistance(int distance){
        this.distance= distance;
    }

    public int getDistance(){
        return this.distance;
    }
    
    @Override
    public String toString(){
        return new String (this.coordinate.toString()+" Crossable = "+this.crossable + " Distance = "+this.distance);
    }

}
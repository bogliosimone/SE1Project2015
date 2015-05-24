/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map.sector;

/**
 * Subclass of Sector act to represent the type SafeSector, is crossable
 * @author simoneboglio
 */
public class SafeSector extends Sector {

    /**
     * Constructor act to create a SafeSector with letter-number as coordinate
     * @param letter
     * @param number
     */

    public SafeSector(char letter, int number){
        super(letter,number);
    }

    /**
     * Constructor act to create a SafeSector with x-y as coordinate
     * @param x
     * @param y
     */
    public SafeSector(int x,int y){
        super(x,y);
    }
    
    @Override
    protected void setCrossable(){
        this.isCrossable=true;
    }
}
/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map.sector;

/**
 * Subclass of Sector act to represent the type BaseSector, isn't crossable
 * @author simoneboglio
 */

public abstract class BaseSector extends Sector {

    /**
     * Constructor act to create a BaseSector with letter-number as coordinate
     * @param letter
     * @param number
     */
    public BaseSector(char letter, int number){
        super(letter,number);
    }

    /**
     * Constructor act to create a BaseSector with x-y as coordinate
     * @param x
     * @param y
     */
    public BaseSector(int x, int y){
        super(x,y);
    }
}
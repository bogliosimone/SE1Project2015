/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map.sector;

/**
 * Subclass of Base Sector act to represent the type DisableSector, isn't crossable
 * @author simoneboglio
 */
public class DisableSector extends Sector {

    /**
     * Constructor act to create a DisableSector with letter-number as coordinate
     * @param letter
     * @param number
     */
    public DisableSector(char letter, int number){
        super(letter,number);
    }
    /**
     * Constructor act to create a DisableSector with x-y as coordinate
     * @param x
     * @param y
     */
    public DisableSector(int x, int y){
        super(x,y);
    }
}
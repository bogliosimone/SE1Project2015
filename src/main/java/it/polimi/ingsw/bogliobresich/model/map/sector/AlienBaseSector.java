/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map.sector;

/**
 * Subclass of Base Sector act to represent the type AlienBaseSector, isn't crossable
 * @author simoneboglio
 */
public class AlienBaseSector extends BaseSector {
    
    /**
     * Constructor act to create an AlienBaseSector with letter-number as coordinate
     * @param letter
     * @param number
     */
    public AlienBaseSector (char letter, int number){
        super(letter,number);
    }

    /**
     * Constructor act to create an AlienBaseSector with x-y as coordinate
     * @param x
     * @param y
     */
    public AlienBaseSector (int x, int y){
        super(x,y);
    }

}
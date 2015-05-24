/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map.sector;

/**
 * Subclass of Base Sector act to represent the type HumanBaseSector, isn't crossable
 * @author simoneboglio
 */
public class HumanBaseSector extends BaseSector {
    /**
     * Constructor act to create an HumanBasSector with letter-number as coordinate
     * @param letter
     * @param number
     */
    public HumanBaseSector(char letter,int number){
        super(letter,number);
    }
    /**
     * Constructor act to create an HumanBasSector with x-y as coordinate
     * @param x
     * @param y
     */
    public HumanBaseSector(int x, int y){
        super(x,y);
    }
}
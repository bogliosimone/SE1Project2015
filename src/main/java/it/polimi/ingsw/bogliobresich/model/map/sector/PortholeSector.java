/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map.sector;

/**
 * Subclass of Sector act to represent the type PortholeSector, this sector can be change his crossable status 
 * @author simoneboglio
 */
public class PortholeSector extends Sector {

    /**
     * Constructor act to create a PortholeSector with letter-number as coordinate
     * @param letter
     * @param number
     */
    public PortholeSector(char letter, int number){
        super(letter,number);
    }

    /**
     * Constructor act to create a PortholeSector with x-y as coordinate
     * @param letter
     * @param number
     */
    public PortholeSector(int x,int y){
        super(x,y);
    }
    
    @Override
    protected void setCrossable(){
        this.isCrossable=true;
    }

    /**
     * Change the crossable statue of PortholeSector
     * @param crossable
     */
    public void setCrossable(boolean crossable){
        this.isCrossable= crossable;
    }
}
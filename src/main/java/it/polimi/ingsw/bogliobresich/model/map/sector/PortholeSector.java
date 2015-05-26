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
     * number of porthole
     */
    int portholeNumber;

    /**
     * Constructor act to create a PortholeSector with letter-number as coordinate
     * @param letter
     * @param number
     * @param portholeNumber
     */
    public PortholeSector(char letter, int number, int portholeNumber){
        super(letter,number);
        this.portholeNumber=portholeNumber;
    }

    /**
     * Constructor act to create a PortholeSector with x-y as coordinate
     * @param letter
     * @param number
     * @param portholeNumber
     */
    public PortholeSector(int x,int y,int portholeNumber){
        super(x,y);
        this.portholeNumber=portholeNumber;
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
    
    @Override
    public String toString(){
        return new String (this.coordinate.toString()+" phNumb: "+this.portholeNumber+" Crossable = "+this.isCrossable );
    }
}
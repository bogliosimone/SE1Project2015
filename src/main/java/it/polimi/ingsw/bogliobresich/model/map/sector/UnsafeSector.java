/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map.sector;

/**
 * Subclass of Sector act to represent the type UnsafeSector, is crossable
 * @author simoneboglio
 */
public class UnsafeSector extends Sector {
    /**
     * Constructor act to create a UnsafeSector with letter-number as coordinate
     * @param letter
     * @param number
     */
    public UnsafeSector(char letter,int number){
        super(letter,number);
    }
    /**
     * Constructor act to create a UnsafeSector with x-y as coordinate
     * @param x
     * @param y
     */
    public UnsafeSector(int x,int y){
        super(x,y);
    }
    
    @Override
    protected void setCrossable(){
        this.isCrossable=true;
    }
}
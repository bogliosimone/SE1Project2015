/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map.sector;

/**
 * @author simoneboglio
 */
public class PortholeSector extends Sector {

    public PortholeSector(char letter, int number){
        super(letter,number);
    }

    public PortholeSector(int x,int y){
        super(x,y);
    }
    
    @Override
    protected void setCrossable(){
        this.crossable=true;
    }

    /**
     * @param crossable
     */
    public void setCrossable(boolean crossable){
        this.crossable= crossable;
    }
}
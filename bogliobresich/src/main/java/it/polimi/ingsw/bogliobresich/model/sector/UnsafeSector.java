/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.sector;

/**
 * @author simoneboglio
 *
 */
public class UnsafeSector extends Sector {
    public UnsafeSector(char letter,int number){
        super(letter,number);
    }
    public UnsafeSector(int x,int y){
        super(x,y);
    }
    
    @Override
    protected void setCrossable(){
        this.crossable=true;
    }
}
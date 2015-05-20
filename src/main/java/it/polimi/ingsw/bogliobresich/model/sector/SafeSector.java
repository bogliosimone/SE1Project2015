/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.sector;

/**
 * @author Matteo
 * @author simoneboglio
 *
 */
public class SafeSector extends Sector {
    public SafeSector(char letter, int number){
        super(letter,number);
    }

    public SafeSector(int x,int y){
        super(x,y);
    }
    
    @Override
    protected void setCrossable(){
        this.crossable=true;
    }
}
/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map.sector;

/**
 * @author Matteo
 *
 */
public abstract class BaseSector extends Sector {
    public BaseSector(char letter, int number){
        super(letter,number);
    }
    public BaseSector(int x, int y){
        super(x,y);
    }
}
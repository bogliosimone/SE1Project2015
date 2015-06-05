/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.player;

/**
 * @author simoneboglio
 *
 */
public class AlienPlayer extends Player {
    protected boolean isFeed=false;
    static final int MOVEMENTSTEPALIENFEED=3;
    
    AlienPlayer(int idPlayer,String nickName){
        super(idPlayer,nickName);
        this.movementStep=2;
        this.canPlayObject=false;
        this.canAttack=true;
    }
    
    void Feed(){
        this.movementStep=MOVEMENTSTEPALIENFEED;
    }
}

/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.player;

import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

/**
 * @author simoneboglio
 *
 */
public class AlienPlayer extends Player {
    protected boolean isFeed=false;
    static final int MOVEMENTSTEPALIENFEED=3;
    
    public AlienPlayer(int idPlayer,String nickName,Coordinate coordinate,CharacterCard characterCard){
        super(idPlayer,nickName,coordinate,characterCard);
        this.movementStep=2;
        this.canPlayObject=false;
        this.canAttack=true;
    }
    
    void feed(){
        this.movementStep=MOVEMENTSTEPALIENFEED;
    }
}

/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.player;

import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;

/**
 * @author simoneboglio
 *
 */

public class AlienPlayer extends Player {
    
    private static final long serialVersionUID = -269798935049307699L;
    protected boolean isFeed=false;
    static final int MOVEMENTSTEPALIENFEED=3;
    
    public AlienPlayer(User user,Coordinate coordinate,CharacterCard characterCard){
        super(user,coordinate,characterCard);
        this.movementStep=2;
        this.canPlayObject=false;
        this.canAttack=true;
        this.isFeed=false;
    }
    
    public boolean isFeed(){
        return this.isFeed;
    }
    
    public void feed(){
        this.isFeed=true;
        this.movementStep=MOVEMENTSTEPALIENFEED;
    }
}

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
    
    @Override
    public String toString(){
        return new String("Alieno: "+this.user.getNickname()+" Personaggio: "+ this.characterCard.toString()  +" coordinate: "+this.coordinate);
    }
}

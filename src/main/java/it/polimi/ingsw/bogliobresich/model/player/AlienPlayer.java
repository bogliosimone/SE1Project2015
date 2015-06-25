/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.player;

import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;

/**
 * class for player with nature alien, he can't play object, he can attack and move by 2 step, 3 step if he get feed
 * this class is serializable
 * @author simoneboglio
 *
 */

public class AlienPlayer extends Player {

    /**
     * serial id
     */
    private static final long serialVersionUID = -269798935049307699L;
    /**
     * boolean for feed the alien
     */
    protected boolean isFeed=false;
    /**
     * default movement step for alien feeded
     */
    static final int MOVEMENTSTEPALIENFEED=3;

    /**
     * create a player with nature alien
     * @param user info of the player
     * @param coordinate where the player is locate in the map
     * @param characterCard character that he play in a game
     */
    public AlienPlayer(User user,Coordinate coordinate,CharacterCard characterCard){
        super(user,coordinate,characterCard);
        this.movementStep=2;
        this.canPlayObject=false;
        this.canAttack=true;
        this.isFeed=false;
    }

    /**
     * return true if alien is feed (3 step movemente), false instead
     * @return true if the alien is feed
     */
    public boolean isFeed(){
        return this.isFeed;
    }

    /**
     * feed the alien for change movement step from 2 to 3
     */
    public void feed(){
        this.isFeed=true;
        this.movementStep=MOVEMENTSTEPALIENFEED;
    }
}

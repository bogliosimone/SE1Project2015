/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.notification;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SectorCard;
import it.polimi.ingsw.bogliobresich.model.commands.Commands;
import it.polimi.ingsw.bogliobresich.model.commands.MovesAvaiable;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.util.List;

/**
 * This class provides a skeletal implementation of a notification.<br />
 * The notification contains who will receive the notification, the command or the type of the notification, and a content.
 * The arguments allowed as content are:<br />
 * - Player <br />
 * - User <br />
 * - List <Player> <br />
 * - List <User> <br />
 * - String <br />
 * - Coordinate <br />
 * - Sector <br />
 * - Integer <br />
 * - MovesAvaiable <br />
 * - ItemCard <br />
 * - SectorCard
 * 
 * @author matteo bresich
 * @author simone boglio
 *  
 * @version 1.0
 * @see it.polimi.ingsw.bogliobresich.model.cards.ItemCard
 * @see it.polimi.ingsw.bogliobresich.model.cards.SectorCard
 * @see it.polimi.ingsw.bogliobresich.model.map.Coordinate
 * @see it.polimi.ingsw.bogliobresich.model.match.User
 * @see it.polimi.ingsw.bogliobresich.model.player.Player
 */
public interface Notification <argument> {
    
    /**
     * @return the command that indicates what to do with the notification.
     */
    public Commands getCommand();
    
    /**
     * @return a generic argument of the notification.
     */
    public argument getArgument();
    //Get the argument of the notification
    /**
     * @return a player as an argument of the notification.
     */
    public Player getPlayer(); 
    /**
     * @return a list of users as an argument of the notification.
     */
    public List<User> getListOfUsers();
    /**
     * @return a list of players as an argument of the notification.
     */
    public List<Player> getListOfPlayers();
    /**
     * @return a string message as an argument of the notification.
     */
    public String getString();
    /**
     * @return a coordinate as an argument of the notification.
     */
    public Coordinate getCoordinate();
    /**
     * @return an item card as an argument of the notification.
     */
    public ItemCard getItemCard();
    /**
     * @return all moves available as an argument of the notification.
     */
    public MovesAvaiable getMovesAvaiable();
    /**
     * @return an user as an argument of the notification.
     */
    public User getUser();
    /**
     * @return a sector card as an argument of the notification.
     */
    public SectorCard getSectorCard();
    /**
     * @return an integer as an argument of the notification.
     */
    public Integer getInteger();
    
    /**
     * @return true if the notification is a broadcast notification.
     */
    public boolean isBroadcast();
    /**
     * It indicates who is the addressee.
     * @return the addressee of the notification.
     */
    public User getNotificationReciver();
}

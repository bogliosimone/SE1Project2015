/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;

import java.util.List;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SectorCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author matteobresich
 *
 */
public interface Notification <Argument> {
    
    /**
     * @return the command that indicates what to do with the notification.
     */
    public Commands getCommand();
    
    /**
     * @return a generic argument of the notification.
     */
    public Argument getArgument();
    //Get the argument of the notification
    public Player getPlayer(); 
    public List<User> getListOfUsers();
    public List<Player> getListOfPlayers();
    public String getString();
    public Coordinate getCoordinate();
    public ItemCard getItemCard();
    public MovesAvaiable getMovesAvaiable();
    public User getUser();
    public SectorCard getSectorCard();
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

/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;

import java.util.List;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author matteobresich
 *
 */
public interface NotificationQueue {
    
    public void addObserver(Object o);
    //Enter a notification
    public void addNotification(NotificationMessage n);
    public NotificationMessage pollNotification();
    
    //Get the notification command
    /**
     * Looks at the notification at the top of the queue without removing it from the queue.
     * @return the command that indicates what to do with the notification.
     */
    public Commands getNotificationCommand();
    
    //Get the argument of the notification
    public Player getPlayerArgument();
    public Coordinate getCoordinateArgument();
    public ItemCard getItemCardArgument();
    public List<User> getListOfUsers();
    
    public String getGenericMessage();
    
    //Utility
    public boolean isEmpty();

}

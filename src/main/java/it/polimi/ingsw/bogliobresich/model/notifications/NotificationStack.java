/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author matteobresich
 *
 */
public interface NotificationStack {
    
    //Enter a notification
    public void pushNotification(NotificationMessage n);
    public NotificationMessage popNotification();
    
    //Get the notification command
    /**
     * Looks at the notification at the top of the stack without removing it from the stack.
     * @return the command that indicates what to do with the notification.
     */
    public Commands getNotificationCommand();
    
    //Get the argument of the notification
    public Player getPlayerArgument();
    public Coordinate getCoordinateArgument();
    public ItemCard getItemCardArgument();
    public String getGenericMessage();
    
    //Utility
    public boolean isEmpty();

}

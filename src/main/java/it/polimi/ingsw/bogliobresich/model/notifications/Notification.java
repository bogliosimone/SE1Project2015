/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;

import it.polimi.ingsw.bogliobresich.model.match.User;

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

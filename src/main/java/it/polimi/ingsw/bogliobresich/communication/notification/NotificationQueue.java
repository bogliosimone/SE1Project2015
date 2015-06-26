/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.notification;

import it.polimi.ingsw.bogliobresich.model.commands.Commands;



/**
 * This class provides a skeletal implementation of a notification queue.
 * 
 * @author matteo bresich
 * @author simone boglio
 *  
 * @version 1.0
 * 
 */
public interface NotificationQueue extends Cloneable{
    
    /**
     * Add an observer to the queue
     * @param o the observer
     */
    public void addObserver(Object o);
    //Enter a notification
    /**
     * Add a notification message to the queue
     * @param n the notification message to add
     */
    public void addNotification(NotificationMessage n);
    
    /**
     * @return the notification message in the queue. If the notification queue is empty return null;
     */
    public NotificationMessage pollNotification();
    
    //Get the notification command
    /**
     * Looks at the notification at the top of the queue without removing it from the queue.
     * @return the command that indicates what to do with the notification.
     */
    public Commands getNotificationCommand();
    /**
     * Removes all of the elements from this collection (optional operation). The collection will be empty after this method returns.
     */
    public void clear();
    
    /**
     * @return a notification queue. The notification queue returned by this method should be independent of this object (which is being cloned).
     */
    public NotificationQueue clone();
    
    /**
     * @return true if the collection is empty.
     */
    public boolean isEmpty();

}

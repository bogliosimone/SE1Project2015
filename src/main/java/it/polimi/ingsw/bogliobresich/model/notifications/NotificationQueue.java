/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;


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
    public void clear();
    
    //Utility
    public boolean isEmpty();

}

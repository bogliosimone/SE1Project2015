/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;

import it.polimi.ingsw.bogliobresich.model.match.User;

/**
 * @author matteobresich
 *
 */
public class NotificationMessage implements Notification<Object> {
    
    private Commands command = null;
    private Object argument = null;
    private boolean isBroadcast = false;
    private User addressee = null;
    
    /**
     * Class constructor. The constructor create a notification message.
     * @param type indicates the type of notification
     * @param argument is the argument to send.
     * @param isBroadcast indicates if the notification is broadcast.
     * @param addressee is who will receive the notification.
     */
    public NotificationMessage(Commands type, Object argument, boolean isBroadcast, User addressee) {
        this.command = type;
        this.argument = argument;
        this.isBroadcast = isBroadcast;
        this.addressee = addressee;
    }
    
    /**
     * Class constructor. The constructor create a notification message for the server.
     * @param type indicates the type of notification
     * @param argument is the argument to send to the server
     */
    public NotificationMessage(Commands type, Object argument) {
        this(type, argument, false, null);
    }
    
    @Override
    public Commands getCommand() {
        return command;
    }

    @Override
    public Object getArgument() {
        return argument;
    }

    @Override
    public boolean isBroadcast() {
        return isBroadcast;
    }

    @Override
    public User getNotificationReciver() {
        return addressee;
    }

}

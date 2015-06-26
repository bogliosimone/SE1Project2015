/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.communication.notification.Notification;

/**
 * @author matteobresich
 *
 */
public interface ServerCommunicationStrategy {

    public void sendNotification(Notification n);
    
}

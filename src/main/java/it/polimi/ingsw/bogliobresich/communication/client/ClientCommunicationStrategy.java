/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.client;

import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.client.exception.AddToMatchException;
import it.polimi.ingsw.bogliobresich.communication.client.exception.LoginException;
import it.polimi.ingsw.bogliobresich.communication.client.exception.SendCommandException;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;

/**
 * @author matteobresich
 *
 */
public interface ClientCommunicationStrategy {
    
    public void doLogin(String url, String nickname, String password) throws LoginException;
    public void addMeMatch() throws AddToMatchException;
    public void sendCommand(ClientCommand command) throws SendCommandException;
    public void ErrorToClient(NotificationMessage notification);

}

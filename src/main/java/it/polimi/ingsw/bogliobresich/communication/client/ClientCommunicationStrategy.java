/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.client;

import it.polimi.ingsw.bogliobresich.communication.client.exception.AddToMatchException;
import it.polimi.ingsw.bogliobresich.communication.client.exception.LoginException;

/**
 * @author matteobresich
 *
 */
public interface ClientCommunicationStrategy {
    
    //public void sendCommand(Notification n);
    public void doLogin(String url, String nickname, String password) throws LoginException;
    public void addMeMatch() throws AddToMatchException;

}

/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.client;

import it.polimi.ingsw.bogliobresich.communication.ClientCommand;

/**
 * @author matteobresich
 *
 */
public interface Client {
    
    public void doLogin(String nickname, String password);
    public void sendCommand(ClientCommand command);

}

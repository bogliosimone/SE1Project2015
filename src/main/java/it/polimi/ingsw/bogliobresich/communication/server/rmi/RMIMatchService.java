/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.client.RemoteObserver;
import it.polimi.ingsw.bogliobresich.model.match.User;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * @author matteobresich
 *
 */
public interface RMIMatchService extends Remote {
    
    void addObserver(User user, RemoteObserver o) throws RemoteException;
    String getMatchHandlerID() throws RemoteException;
    void doAction(User user, ClientCommand command) throws RemoteException;
    
}

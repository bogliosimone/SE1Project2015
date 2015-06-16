/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.ClientCommands;
import it.polimi.ingsw.bogliobresich.communication.client.RemoteObserver;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
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
    void doAction(User user, ClientCommands doRequest, Coordinate coordinate) throws RemoteException;
    
}

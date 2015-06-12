/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.client.RemoteObserver;
import it.polimi.ingsw.bogliobresich.communication.server.GameProtocol;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * @author matteobresich
 *
 */
public interface RMIMatchHandlerService extends Remote {

    void addObserver(RemoteObserver o) throws RemoteException;
    String getMatchHandlerID() throws RemoteException;
    void doAction(User user, GameProtocol doMoveRequest) throws RemoteException;
}

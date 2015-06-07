/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author matteobresich
 *
 */
public interface RemoteObserver extends Remote {

    void update(Object observable, Object updateMsg) throws RemoteException;

}

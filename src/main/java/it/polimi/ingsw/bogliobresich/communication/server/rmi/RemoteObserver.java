/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author matteobresich
 *
 */
public interface RemoteObserver extends Remote {

    void update(Object observable, Object updateMsg) throws RemoteException;

}

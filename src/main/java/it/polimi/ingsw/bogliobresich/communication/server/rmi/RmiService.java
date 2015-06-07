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
public interface RmiService extends Remote {

    void addObserver(RemoteObserver o) throws RemoteException;

}

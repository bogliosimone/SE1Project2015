/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.server.Server;
import it.polimi.ingsw.bogliobresich.communication.server.ServerUtil;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author matteobresich
 *
 */
public class RmiServer implements RmiService {

    @Override
    public void addObserver(RemoteObserver o) throws RemoteException {
        // TODO Auto-generated method stub
        
    }

}

package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.client.RemoteObserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiService extends Remote {

    void addObserver(RemoteObserver o) throws RemoteException;
    void doAction(int playerId) throws RemoteException;

}

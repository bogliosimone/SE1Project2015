package it.polimi.ingsw.bogliobresich.communication.client;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObserver extends Remote {

    void update(Serializable observable, Object updateMsg) throws RemoteException;

}

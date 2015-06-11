package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIConnectionService extends Remote {

    RMIMatchHandlerService connectToMatch(String nickname) throws RemoteException;

}

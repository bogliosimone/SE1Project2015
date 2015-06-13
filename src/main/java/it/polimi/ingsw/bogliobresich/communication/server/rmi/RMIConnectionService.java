package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.model.match.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIConnectionService extends Remote {

    User login(String nickname,String password) throws RemoteException;
    RMIMatchHandlerService connectToMatch(User user) throws RemoteException;

}

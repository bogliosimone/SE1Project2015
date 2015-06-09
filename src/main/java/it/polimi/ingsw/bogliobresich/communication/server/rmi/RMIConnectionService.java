package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.server.MatchHandler;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIConnectionService extends Remote {

    MatchHandler connectToMatch(String nickname) throws RemoteException;

}

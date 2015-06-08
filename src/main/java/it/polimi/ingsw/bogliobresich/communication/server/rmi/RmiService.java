package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.client.RemoteObserver;
import it.polimi.ingsw.bogliobresich.communication.server.MatchHandler;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiService extends Remote {

    boolean connect(String nickname) throws RemoteException;
    void addObserver(RemoteObserver o) throws RemoteException;
    void doAction(Player p, Action action) throws RemoteException;

}

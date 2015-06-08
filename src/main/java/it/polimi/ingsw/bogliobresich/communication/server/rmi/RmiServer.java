package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.client.RemoteObserver;
import it.polimi.ingsw.bogliobresich.communication.server.MatchHandler;
import it.polimi.ingsw.bogliobresich.communication.server.Matches;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class RmiServer extends Observable implements RmiService, Serializable {    
    /**
     * 
     */
    private static final long serialVersionUID = -1443539127775650583L;
    RmiObservableObject test;
    
    public RmiServer() {
        test = new RmiObservableObject();
    }

    @Override
    public boolean connect(String nickname) throws RemoteException {
        return Matches.getInstance().connectUser(nickname);
    }

    @Override
    public void doAction(Player p, Action action) throws RemoteException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addObserver(RemoteObserver o) throws RemoteException {
        test.addObserver(o);
    }

    
}

package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.server.MatchHandler;
import it.polimi.ingsw.bogliobresich.communication.server.MatchesHandler;
import it.polimi.ingsw.bogliobresich.communication.server.Server;

import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;

public class RMIConnectionServer extends Observable implements RMIConnectionService, Serializable {    
    /**
     * 
     */
    private static final long serialVersionUID = -1443539127775650583L;
    private Registry rmiRegistry;
    private RMIConnectionService rmiConnectionService;
    MatchesHandler matchesHandler = MatchesHandler.getInstance();
    
    public RMIConnectionServer(String name,int port) {
        Server.serviceMessage("RMI CONNECTION SERVER START");
        try {
            rmiRegistry = LocateRegistry.createRegistry(port);
            rmiConnectionService = (RMIConnectionService) UnicastRemoteObject.exportObject(this, port);
            try {
                rmiRegistry.bind(name, rmiConnectionService);
                Server.serviceMessage("RMI CONNECTION SERVER STARTED\t\t[ OK ]");
            } catch (RemoteException | AlreadyBoundException e) {
                e.printStackTrace();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RMIMatchHandlerService connectToMatch(String nickname) throws RemoteException {
        return matchesHandler.connectUser(nickname);
    }
}

package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.server.MatchesHandler;
import it.polimi.ingsw.bogliobresich.communication.server.Server;
import it.polimi.ingsw.bogliobresich.model.match.User;

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
    private boolean isInitialized = false;
    private Registry rmiRegistry;
    private RMIConnectionService rmiConnectionService;
    MatchesHandler matchesHandler = MatchesHandler.getInstance();
    
    public RMIConnectionServer(String name,int port) {
        Server.serviceMessage("RMI CONNECTION SERVER START");
        Server.serviceMessage("RMI CONNECTION SERVER PORT: " + port);
        try {
            rmiRegistry = LocateRegistry.createRegistry(port);
            rmiConnectionService = (RMIConnectionService) UnicastRemoteObject.exportObject(this, port);
            try {
                rmiRegistry.bind(name, rmiConnectionService);
                isInitialized = true;
                Server.serviceMessage("RMI CONNECTION SERVER STARTED\t\t[ OK ]");
            } catch (RemoteException | AlreadyBoundException e) {
                e.printStackTrace();
            }
        } catch (RemoteException e) {
            Server.errorMessage("RMI CONNECTION ERROR!\t\t[Fail]");
        }
    }
    
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public User login(String nickname, String password) throws RemoteException {
        Server.connectionMessage("CONNECTION REQUEST BY: " + nickname);
        return new User(Server.getUserID(),nickname, password);
    }
    
    @Override
    public RMIMatchHandlerService connectToMatch(User user) throws RemoteException {
        Server.connectionMessage("MATCH REQUEST BY: " + user);
        return matchesHandler.connectUser(user);
    }
}

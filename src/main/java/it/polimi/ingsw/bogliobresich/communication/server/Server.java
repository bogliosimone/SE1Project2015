/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.bogliobresich.communication.server.rmi.RmiServer;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RmiService;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.User;

/**
 * @author matteobresich
 *
 */
public class Server implements Runnable {
    
    private static Server instance;
    
    public static synchronized Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    
    private Registry rmiRegistry;
    private RmiService rmiService;
    
    private Matches matches = null;
    
    @Override
    public void run() {
        initGeneralServer();
        initRMIServer();
        initSocketServer();
    }
    
    private void initGeneralServer() {
        System.out.println(CommunicationUtil.getLocalIp());
        matches = Matches.getInstance();
        Matches.getInstance().addNewMatch();
    }
    
    private void initRMIServer() {
        try {
            System.out.println("RMI START");
            rmiRegistry = LocateRegistry.createRegistry(CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT);
            rmiService = (RmiService) UnicastRemoteObject.exportObject(new RmiServer(), CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT);
            rmiRegistry.bind(CommunicationUtil.REMOTE_MATCH_OBJECT_NAME, rmiService);
            System.out.println("RMI SERVER STARTED");
        }
        catch (Exception ex) {
            System.err.println("RMI exception:");
            ex.printStackTrace();
        }
        finally {
            rmiRegistry = null;
        }
    }
    private void initSocketServer() {
        //TODO
    }
    
    public synchronized void shutdownNow() {
        if(matches != null) {
            matches.shutdownNow();
        }
    }
}

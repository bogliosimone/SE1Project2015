/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIConnectionServer;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIConnectionService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author matteobresich
 *
 */
public class Server implements Runnable {
    
    private static Server instance;
    private Registry rmiRegistry;
    private RMIConnectionService rmiConnectionService;
    private MatchesHandler matchesHandler = null;
    
    public static synchronized Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    @Override
    public void run() {
        initGeneralServer();
        initRMIServer();
        initSocketServer();
    }
    
    private void initGeneralServer() {
        Server.serviceMessage("---SERVER START ---");
        Server.serviceMessage("IP:" + CommunicationUtil.getLocalIp());
        matchesHandler = MatchesHandler.getInstance();
    }
    
    private void initRMIServer() {
        try {
            Server.serviceMessage("SERVER > RMI CONNECTION SERVER START");
            rmiRegistry = LocateRegistry.createRegistry(CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT);
            rmiConnectionService = (RMIConnectionService) UnicastRemoteObject.exportObject(new RMIConnectionServer(), CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT);
            rmiRegistry.bind(CommunicationUtil.REMOTE_CONNECTION_NAME, rmiConnectionService);
            Server.serviceMessage("SERVER > RMI SERVER STARTED");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            rmiRegistry = null;
        }
    }
    private void initSocketServer() {
        //TODO
        Server.serviceMessage("SOCKET CONNECTION SERVER START");
        Server.serviceMessage("SOCKET SERVER FAIL");
    }
    
    public static void serviceMessage(Object msg) {
        System.out.println("| SERVER > " + msg);
    }
    
    public synchronized void shutdownNow() {
        if(matchesHandler != null) {
            matchesHandler.shutdownNow();
        }
    }
}

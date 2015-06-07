/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.bogliobresich.communication.server.rmi.RmiServer;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RmiService;

/**
 * @author matteobresich
 *
 */
public class Server implements Runnable {
    
    private static Server instance;
    private Registry rmiRegistry;
    private RmiService rmiService;
    
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
        System.out.println(CommunicationUtil.getLocalIp());
        WaitingRoom room = new WaitingRoom();
        System.out.println("ROOM CREATED");
    }
    
    private void initRMIServer() {
        try {
            System.out.println("RMI START");
            rmiRegistry = LocateRegistry.createRegistry(CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT);
            rmiService = (RmiService) UnicastRemoteObject.exportObject(new RmiServer(), CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT);
            rmiRegistry.bind("RmiService", rmiService);
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
}

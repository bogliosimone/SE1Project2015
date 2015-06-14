/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIConnectionServer;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIConnectionService;
import it.polimi.ingsw.bogliobresich.communication.server.socket.SocketConnectionServer;

import java.rmi.registry.Registry;
import java.util.concurrent.ExecutorService;

/**
 * @author matteobresich
 *
 */
public class Server implements Runnable {
    
    private static Server instance;
    private MatchesHandler matchesHandler = null;
    private static int lastUserAdded = 0;
    
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
        RMIConnectionServer rmiConnectionServer = new RMIConnectionServer(CommunicationUtil.REMOTE_CONNECTION_NAME, CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT);
    }
    private void initSocketServer() {
        SocketConnectionServer socketConnectionServer = new SocketConnectionServer(CommunicationUtil.SOCKET_REQUEST_SERVER_TCP_PORT);
    }
    
    public static void serviceMessage(Object msg) {
        System.out.println("| SERVER > " + msg);
    }
    
    public static void errorMessage(Object msg) {
        System.out.println("| ERROR! > " + msg);
    }
    public static void connectionMessage(Object msg) {
        System.out.println("| LOGIN  > " + msg);
    }
    
    public static synchronized int getUserID() {
        return lastUserAdded++;
    }
    
    public synchronized void shutdownNow() {
        if(matchesHandler != null) {
            matchesHandler.shutdownNow();
        }
    }
}

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

    private RMIConnectionServer rmiConnectionServer;
    private SocketConnectionServer socketConnectionServer;
    
    private static String selectedMap;
    
    public static final boolean SERVER_DEBUG = true;

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
        Server.serviceMessage("IP:" + ServerUtils.getLocalIp());
        matchesHandler = MatchesHandler.getInstance();
    }

    private void initRMIServer() {
        rmiConnectionServer = new RMIConnectionServer(ServerUtils.REMOTE_CONNECTION_NAME, ServerUtils.RMI_REQUEST_SERVER_TCP_PORT);
    }
    private void initSocketServer() {
        socketConnectionServer = new SocketConnectionServer(ServerUtils.getLocalIp(),ServerUtils.SOCKET_REQUEST_SERVER_TCP_PORT);
    }

    public synchronized static void serviceMessage(Object msg) {
        System.out.println("| SERVER > " + msg);
    }

    public synchronized static void errorMessage(Object msg) {
        System.out.println("| ERROR! > " + msg);
    }
    public synchronized static void connectionMessage(Object msg) {
        System.out.println("| LOGIN  > " + msg);
    }

    public synchronized static void debugMessage(Object msg) {
        if(SERVER_DEBUG) {
            System.out.println("| COMMUNICATION  > " + msg);
        }
    }


    public static synchronized int getUserID() {
        return lastUserAdded++;
    }

    public synchronized void shutdownNow() {
        if(matchesHandler != null) {
            matchesHandler.shutdownNow();
        }
    }

    public static String getSelectedMap() {
        return selectedMap;
    }

    public static void selectMap(String map) {
        Server.serviceMessage("MAP SELECTED: " + map);
        selectedMap = map;
    }
}

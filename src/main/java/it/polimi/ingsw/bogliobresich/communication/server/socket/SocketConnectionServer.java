/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server.socket;

import it.polimi.ingsw.bogliobresich.communication.server.Server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author matteobresich
 *
 */
public class SocketConnectionServer implements Runnable {
    
    private boolean isInitialized = false;
    private int port;
    private String address;
    private ServerSocket serverSocket;
    
    public SocketConnectionServer(String address, int port) {
        Server.serviceMessage("SOCKET CONNECTION SERVER START");
        this.address = address;
        this.port = port;
        Server.serviceMessage("SOCKET CONNECTION PORT: " + port);
        Server.serviceMessage("SOCKET CONNECTION ADDRESS: " + address);
        try {
        serverSocket = new ServerSocket(port);
        isInitialized = true;
        Server.serviceMessage("SOCKET CONNECTION SERVER STARTED\t\t[ OK ]");
        }
        catch (IOException e) {
            Server.errorMessage("SOCKET CONNECTION SERVER ERROR!\t[Fail]");
            return;
        }
    }
    
    public boolean isInitialized() {
        return isInitialized;
    }
    
    /**
     * Releasing the Socket connection server resources.
     */
    public void close() {
        
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

}

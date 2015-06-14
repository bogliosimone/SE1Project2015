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
    private ServerSocket serverSocket;
    
    public SocketConnectionServer(int port) {
        Server.serviceMessage("SOCKET CONNECTION SERVER START");
        Server.serviceMessage("SOCKET CONNECTION SERVER PORT: " + port);
        this.port = port;
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


    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

}

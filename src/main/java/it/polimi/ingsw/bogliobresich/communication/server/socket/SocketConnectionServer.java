/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server.socket;

import it.polimi.ingsw.bogliobresich.communication.server.MatchesHandler;
import it.polimi.ingsw.bogliobresich.communication.server.Server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author matteobresich
 *
 */
public class SocketConnectionServer implements Runnable {
    
    private int port;
    private ServerSocket serverSocket;
    
    public SocketConnectionServer(int port) {
        Server.serviceMessage("SOCKET CONNECTION SERVER START");
        this.port = port;
        try {
        serverSocket = new ServerSocket(port);
        Server.serviceMessage("SOCKET CONNECTION SERVER STARTED\t\t[ OK ]");
        }
        catch (IOException e) {
            Server.serviceMessage("SOCKET ");
            return;
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

}

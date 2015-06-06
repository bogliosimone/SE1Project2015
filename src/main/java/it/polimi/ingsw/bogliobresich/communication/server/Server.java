/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.ServerUtil;

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

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println(ServerUtil.getLocalIp());
        
    }
}

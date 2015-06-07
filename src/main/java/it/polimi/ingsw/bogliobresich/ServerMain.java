/**
 * 
 */
package it.polimi.ingsw.bogliobresich;

import it.polimi.ingsw.bogliobresich.communication.server.Server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* This class is the entry point of the Server Process.
* Main method launches all server components. 
*/
public class ServerMain {
    public static void main (String [] args) {
        Server server;
        ExecutorService executor;
        executor = null;
        
        try {
            executor = Executors.newSingleThreadExecutor();
            server = Server.getInstance();
            executor.submit(server);
        }
        catch (Exception e) {
            System.out.println(e);
          //TODO 
        }
        finally {
            if(executor != null) {
                executor.shutdownNow();
            }   
        } 
    }

    /***/
    private ServerMain () 
    {}
}

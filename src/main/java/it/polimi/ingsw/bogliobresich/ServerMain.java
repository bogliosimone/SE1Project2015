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
        Server server = null;
        ExecutorService executor = null;
        
        try {
            executor = Executors.newSingleThreadExecutor();
            server = Server.getInstance();
            Server.selectMap("fermi.txt");
            executor.submit(server);
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(server != null) {
                server.shutdownNow();
            }
            if(executor != null) {
                executor.shutdownNow();
            }   
        } 
    }

    /***/
    private ServerMain () 
    {}
}

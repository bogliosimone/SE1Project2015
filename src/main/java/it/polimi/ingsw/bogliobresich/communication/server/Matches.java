/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author matteobresich
 *
 */
public class Matches {
    
    private static Matches instance = null;
    ExecutorService executor = Executors.newCachedThreadPool();
    
    public static synchronized Matches getInstance() {
        if(instance == null) {
            instance = new Matches();
            return instance;
        }
        return instance; 
    }
    
    public synchronized void shutdownNow() {
        executor.shutdownNow();
    }
    
    public synchronized void addMatch(Vector users) {
        MatchHandler m = new MatchHandler();
        executor.submit(m);
    }
    
}

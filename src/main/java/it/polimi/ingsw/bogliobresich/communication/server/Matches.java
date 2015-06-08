/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

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
    
    public synchronized void addNewMatch() {
        try {
            MatchHandler m = new MatchHandler();
            executor.submit(m);
            System.out.println("PARTITA " + m.toString() + " AVVIATA!");
        }
        catch(RejectedExecutionException e) {
            System.err.println("MatchHandler cannot be accepted for execution!");
        }
        catch(NullPointerException e) {
            System.err.println("MatchHandler is null!");
        }
        
    }
    
}

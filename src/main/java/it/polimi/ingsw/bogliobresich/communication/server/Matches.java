/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.communication.client.RemoteObserver;
import it.polimi.ingsw.bogliobresich.model.match.User;

import java.rmi.RemoteException;
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
    
    MatchHandler lastMatchHandlerAdded = null;
    
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
    
    public synchronized boolean connectUser(String nickname,RemoteObserver o) throws RemoteException {
        MatchHandler m = lastMatchHandlerAdded.addUser(new User(nickname),o);
        return true;
    }
    
    public synchronized void addNewMatch() {
        try {
            lastMatchHandlerAdded = new MatchHandler();
            executor.submit(lastMatchHandlerAdded);
            System.out.println("PARTITA " + lastMatchHandlerAdded.toString() + " AVVIATA!");
        }
        catch(RejectedExecutionException e) {
            System.err.println("MatchHandler cannot be accepted for execution!");
        }
        catch(NullPointerException e) {
            System.err.println("MatchHandler is null!");
        }
    }
}

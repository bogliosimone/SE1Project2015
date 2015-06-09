/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.model.match.User;

import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author matteobresich
 *
 */
public class MatchesHandler{
    
    private static MatchesHandler instance = null;
    private ExecutorService executor = Executors.newCachedThreadPool();
    
    private static MatchHandler lastMatchHandlerAdded = null;
    private static int lastMatchHandlerIDAdded = 0;
    
    public static synchronized MatchesHandler getInstance() {
        if(instance == null) {
            instance = new MatchesHandler();
            lastMatchHandlerAdded = instance.addNewMatch();
            return instance;
        }
        return instance; 
    }
    
    public synchronized void shutdownNow() {
        executor.shutdownNow();
    }
    
    public synchronized MatchHandler connectUser(String nickname) throws RemoteException {
        if(lastMatchHandlerAdded == null) {
            throw new RemoteException("Unable to connect: Match Handler not initialized!");
        }
        if(lastMatchHandlerAdded.isMatchStarded()) {
            lastMatchHandlerAdded = addNewMatch();
        }
        lastMatchHandlerAdded.addUser(new User(nickname));
        return lastMatchHandlerAdded;
    }
    
    public synchronized MatchHandler addNewMatch() {
        MatchHandler last = null;
        try {
            last = new MatchHandler(lastMatchHandlerIDAdded);
            lastMatchHandlerIDAdded++;
            executor.submit(last);
            Server.serviceMessage("MATCHHANDLER: " + last.toString() + " \t\t[ OK ]");
            
        }
        catch(RejectedExecutionException e) {
            e.printStackTrace();
        }
        catch(NullPointerException e) {
            e.printStackTrace();
        }
        return last;
    }
}

/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIMatchHandlerService;
import it.polimi.ingsw.bogliobresich.model.match.User;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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
    private static int lastUserAdded = 0;
    
    
    public static synchronized MatchesHandler getInstance() {
        if(instance == null) {
            instance = new MatchesHandler();
            return instance;
        }
        return instance; 
    }
    
    public synchronized void shutdownNow() {
        executor.shutdownNow();
    }
    
    public static synchronized int getUserID() {
        return lastUserAdded++;
    }
    
    public synchronized MatchHandler connectUser(String nickname) throws RemoteException {
        if(lastMatchHandlerAdded == null) {
            lastMatchHandlerAdded = addNewMatch();
        }
        if(lastMatchHandlerAdded.isMatchStarded()) {
            lastMatchHandlerAdded = addNewMatch();
        }
        if(lastMatchHandlerAdded == null) {
            throw new RuntimeException();
        }
        lastMatchHandlerAdded.addUser(new User(getUserID(),nickname));
        return lastMatchHandlerAdded;
    }

    public synchronized MatchHandler addNewMatch() {
        MatchHandler last = null;
        try {
            Server.serviceMessage("MATCHESHANDLER: NEW MATCH");
            last = new MatchHandler();
            
            Registry rmiRegistry;
            try {
                rmiRegistry = LocateRegistry.getRegistry(CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT);
                RMIMatchHandlerService rmiService = (RMIMatchHandlerService) UnicastRemoteObject.exportObject(last, CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT);
                try {
                    rmiRegistry.bind(last.getID(), rmiService);
                    Server.serviceMessage("MATCHHANDLER: RMI MATCH SERVER\t\t[ OK ]");
                } catch (AlreadyBoundException e) {
                    e.printStackTrace();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
                            
            executor.submit(last);
            Server.serviceMessage("MATCHESHANDLER: NEW " + last.toString() + "\t\t[ OK ]");
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

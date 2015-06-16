/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIMatchService;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIMatchServiceHandler;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.AddPlayerAction;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationQueue;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationQueueHandler;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

/**
 * @author matteobresich
 *
 */
public class MatchHandler implements Runnable, Observer {

    private Match match = null;
    private static int lastMatchHandlerIDAdded = 0;
    private int matchID = 0;
    private NotificationQueue notificationQueue = null;
    private ServerCommunication RMI;
    
    
    
    /**
     * Class constructor
     *
     */
    public MatchHandler() {
        notificationQueue = new NotificationQueueHandler();
        notificationQueue.addObserver(this);
        RMI = new RMIMatchServiceHandler(this);
        this.matchID = lastMatchHandlerIDAdded;
        this.match = new Match(matchID,notificationQueue);
        lastMatchHandlerIDAdded++;
    }
    
    /**
     * @return the match handler id
     */
    public String getID() {
        return "MatchHandlerID:" + matchID;
    }
    
    /**
     * @param p is the player that want to do an action
     * @param action is what the player would do
     */
    void executeAction(Player p, Action action) {
        match.doAction(p, action);
    }
    
    /**
     * Adds a user into a match.
     * @param user is the entity that will be added into the match
     * @return MatchHandler the handler of the match in which the user is added
     * @throws RemoteException
     */
    public MatchHandler addUser(User user) throws RemoteException {
            executeAction(null, new AddPlayerAction(user));
        return this;
    }
    
    /**
     * @param user that is a player in the match
     * @return player if the user is in the match null otherwise.
     */
    public Player getPlayerByUser(User user) {
        for(Player p : match.getAllPlayer()) {
            if(p.getUser().equals(user)) {
                return p;
            }
        }
        return null; 
    }
    
    
    /**
     * @return true if the match is in play state
     */
    public boolean isMatchStarted() {
        if(match.isActive()){
            return true;
        }
        return false;
    }
    

    @Override
    public void run() {
        //TODO 
        while(!match.isEnd()) {
            try {
                Server.serviceMessage(this.toString() + " IS ALIVE");
                Thread.sleep(15 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
        }
        Server.serviceMessage(this.toString() + " ENDED");
    }
    
    @Override
    public String toString() {
        return "MATCH [ID=" + matchID + "]";
    }
    
    
    //TODO sistemare
    public RMIMatchService getRMIMatchServiceHandler() {
        return (RMIMatchService) RMI;
    }


    /* 
     * @see NotificationQueue
     */
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof NotificationQueue) {
            
        }
    }
}

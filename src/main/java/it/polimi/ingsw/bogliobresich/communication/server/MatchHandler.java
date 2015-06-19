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
import it.polimi.ingsw.bogliobresich.model.notifications.Notification;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationQueue;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationQueueHandler;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * @author matteobresich
 *
 */
public class MatchHandler implements Runnable, Observer {

    private Match match = null;
    private static int lastMatchHandlerIDAdded = 0;
    private int matchID = 0;
    private NotificationQueue notificationQueue = null;
    private ServerCommunicationStrategy RMI;
    private ServerCommunicationStrategy Socket;
    
    private List<User> users = new Vector<User>();
    
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
     * @param p is the player that want to do an action
     * @param action is what the player would do
     */
    void executeAction(Player p, Action action) {
        match.doAction(p, action);
    }
    
    /**
     * @param user
     */
    public void addUser(User user) {
        users.add(user);
        executeAction(null, new AddPlayerAction(user));
    }
    
    /**
     * @return the match handler id
     */
    public String getID() {
        return "MatchHandlerID:" + matchID;
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
    
    public void sendNotification(Notification n) {
        RMI.sendNotification(n);
    }
    

    @Override
    public void run() {
        //TODO 
        while(!match.isEnd()) {
            try {
                Server.serviceMessage(this.toString() + " IS ALIVE");
                Thread.sleep(100 * 1000);
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
            synchronized(this) {
            NotificationQueue queue = ((NotificationQueue)o);
            Notification notification = queue.pollNotification();
            sendNotification(notification);
            }
        }
    }
}

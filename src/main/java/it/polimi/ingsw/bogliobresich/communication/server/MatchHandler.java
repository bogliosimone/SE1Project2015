/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.AddPlayerAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * @author matteobresich
 *
 */
public class MatchHandler implements Runnable,Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3783668816203597145L;
    private transient Match match = null;
    private transient  static int lastMatchHandlerIDAdded = 0;
    private int matchID = 0;
    
    /**
     * Class constructor
     *
     */
    public MatchHandler() {
        this.matchID = lastMatchHandlerIDAdded;
        this.match = new Match(matchID);
        lastMatchHandlerIDAdded++;
    }
    
    public boolean isMatchStarded() {
        return isMatchActive();
    }
    
    public MatchHandler addUser(User usr) throws RemoteException {
            doAction(null, new AddPlayerAction(usr));
        return this;
    }
    
    
    /**
     * @return true if the match is in play state
     */
    public boolean isMatchActive() {
        if(match.isActive()){
            return true;
        }
        return false;
    }
    
    public void doAction(Player p, Action action) {
        match.doAction(p, action);
    }

    @Override
    public void run() {
        //TODO 
        while(!match.isEnd()) {
            try {        
                Thread.sleep(5 * 1000);
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
}

/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.communication.client.RemoteObserver;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIMatchHandlerService;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.AddPlayerAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * @author matteobresich
 *
 */
public class MatchHandler extends Observable implements Runnable, RMIMatchHandlerService, Serializable {

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
    
    /**
     * @return the match handler id
     */
    public String getID() {
        return "MatchHandlerID:" + matchID;
    }
    
    
    /**
     * Adds a user into a match
     * @param user is the user that will be added into the match
     * @return MatchHandler the handler of the match where the user is added
     * @throws RemoteException
     */
    public MatchHandler addUser(User user) throws RemoteException {
            doAction(null, new AddPlayerAction(user));
        return this;
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
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
            setChanged();
            notifyObservers(new Date());
        }
        
        Server.serviceMessage(this.toString() + " ENDED");
    }
    
    @Override
    public String toString() {
        return "MATCH [ID=" + matchID + "]";
    }

    
    
    private class WrappedObserver implements Observer, Serializable {

        /**
         * 
         */
        private static final long serialVersionUID = 2747416104063483478L;
        private RemoteObserver ro = null;

        public WrappedObserver(RemoteObserver ro) {
            this.ro = ro;
        }

        @Override
        public void update(Observable o, Object arg) {
            try {
                ro.update((Serializable) o, arg);
            } catch (RemoteException e) {
                Server.serviceMessage("REMOTE EXCEPTION REMOVING OBSERVER:" + this);
                o.deleteObserver(this);
            }
        }

    }

    
    
    @Override
    public void addObserver(RemoteObserver o) throws RemoteException {
        WrappedObserver mo = new WrappedObserver(o);
        addObserver(mo);
        Server.serviceMessage("ADDED OBSERVER:" + mo);
    }

    @Override
    public void doAction(Player p, Action action) throws RemoteException {
        match.doAction(p, action);
    }
    
    @Override
    public String getMatchHandlerID() throws RemoteException {
        return this.getID();
    }

   
}

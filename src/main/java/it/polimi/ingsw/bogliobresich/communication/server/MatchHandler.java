/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.communication.client.RemoteObserver;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RmiObservableBroadcastMessageHandler;
import it.polimi.ingsw.bogliobresich.model.cards.TeleportItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.AddPlayerAction;
import it.polimi.ingsw.bogliobresich.model.match.action.AttackAction;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author matteobresich
 *
 */
public class MatchHandler implements Runnable {
    
    private Match match;
    RmiObservableBroadcastMessageHandler broadcastMessages = null;
    
    public MatchHandler() {
        this.match = new Match();
        this.broadcastMessages = new RmiObservableBroadcastMessageHandler();
    }
    
    public MatchHandler(Match m) {
        this.match = m;
    }
    
    public MatchHandler addUser(User usr, RemoteObserver o) throws RemoteException {
        if(isMatchActive()) {
            Matches.getInstance().addNewMatch();
        } else {
            match.doAction(null, new AddPlayerAction(usr));
            broadcastMessages.addObserver(o);
        }
        return this;
    }
    
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
        
        match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('L',5)));
        match.doAction(match.getCurrentPlayer(), new AttackAction());
        
        System.out.println("FINE PARTITA");
    }

}

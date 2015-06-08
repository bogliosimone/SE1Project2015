/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.model.cards.TeleportItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.match.action.AddPlayerAction;
import it.polimi.ingsw.bogliobresich.model.match.action.AttackAction;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;

import java.util.ArrayList;

/**
 * @author matteobresich
 *
 */
public class MatchHandler implements Runnable {
    
    private ArrayList users;
    private Match match;
    
    public MatchHandler() {
        this.match = new Match();
    }
    
    public MatchHandler(Match m) {
        this.match = m;
    }
    
    public void addUser(User usr) {
        if(isMatchActive()) {
            Matches.getInstance().addNewMatch();
        } else {
            match.doAction(null, new AddPlayerAction(usr));
        }
        
    }
    
    public boolean isMatchActive() {
        if(match.isActive()){
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        
        addUser(new User("pippo"));
        addUser(new User("pluto"));
        addUser(new User("paperino"));
        
        
        match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('L',5)));
        match.doAction(match.getCurrentPlayer(), new AttackAction());
        
        System.out.println("FINE PARTITA");
    }

}

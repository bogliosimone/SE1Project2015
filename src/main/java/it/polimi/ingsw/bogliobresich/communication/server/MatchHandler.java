/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.model.cards.TeleportItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.match.action.RumorCoordinate;

import java.util.ArrayList;

/**
 * @author matteobresich
 *
 */
public class MatchHandler implements Runnable {
    
    private ArrayList users;
    private Match match;
    
    public MatchHandler(Match m) {
        this.match = m;
    }

    @Override
    public void run() {
        match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('L',5)));
        match.doAction(match.getCurrentPlayer(), new PlayItemAction(new TeleportItemCard(1)));
        match.doAction(match.getCurrentPlayer(), new RumorCoordinate(new Coordinate('C',4)));
        
        System.out.println("FINE PARTITA");
    }

}

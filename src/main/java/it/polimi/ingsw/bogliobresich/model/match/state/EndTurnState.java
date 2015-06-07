/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class EndTurnState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(action instanceof EndPhaseAction){
           
        }
        match.serviceMessage("Mossa non disponibile nella fase finale del turno turno");
        return;

    }

}

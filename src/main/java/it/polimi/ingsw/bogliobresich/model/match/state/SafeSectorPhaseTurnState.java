package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.AttackAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndTurnAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.match.action.SafeSectorAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

public class SafeSectorPhaseTurnState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(!player.equals(match.getCurrentPlayer())){
            match.notifyPlayer(player, "Non è il tuo turno");
            return;
        }
        if(action instanceof SafeSectorAction){
            if(!player.canAttack()){
               match.setState(new EndPhaseTurnState()); 
               match.doAction(player, new EndPhaseAction());
            }
            return;
        }
        if(action instanceof PlayItemAction){
            match.setState(new EndPhaseTurnState()); 
            match.doAction(player, action);
            return;
        }
        if(action instanceof EndTurnAction){
            match.setState(new EndTurnState()); 
            match.doAction(player, action);
            return;
        }
        if(action instanceof AttackAction){
            match.setState(new AttackPhaseTurnState()); 
            match.doAction(player, action);
            return;
        }
        match.notifyPlayer(player, "Mossa non consentita durante SafeSectorPhase");
        return;
    }

}

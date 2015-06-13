/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndTurnAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class EndPhaseTurnState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(player == null){
            match.serviceMessage("Comando non valido");
            return;
        }
        
        if(action instanceof EndPhaseAction){
            match.notifyPlayer(player, "Gioca carta o concludi turno");
            return;
        }
        
        if(action instanceof EndTurnAction){
            match.setState(new EndTurnState()); 
            match.doAction(player, action);
            return;
        }
        
        if(action instanceof PlayItemAction){
            ItemCard card=((PlayItemAction) action).getItemCard();
            if(card.isPlayableEndPhase()&&player.canPlayObject()){
                card = match.playItemCard(player, card);
                if(card!=null){
                    match.notifyAllPlayer("ha giocato la carta: "+card.toString());
                    return;
                }
            }
            match.notifyPlayer(player, "Non puoi giocare questa carta");
            return; 
        }
        match.serviceMessage("Mossa non disponibile nella end phase del turno");
        return;
    }

}

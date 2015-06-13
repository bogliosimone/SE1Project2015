/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.AttackAction;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawSectorAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.match.action.UnsafeSectorAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class UnsafeSectorPhaseTurnState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(player==null){
            match.serviceMessage("Comando non valido");
            return;
        }

        if(action instanceof UnsafeSectorAction){
            match.notifyPlayer(player, "Attacchi, giochi un oggetto, concludi movimento o peschi una carta settore?");
            return;
        }

        if(action instanceof AttackAction && player.canAttack()){
            match.setState(new AttackPhaseTurnState()); 
            match.doAction(player, action);
            return;
        }

        if(action instanceof DrawSectorAction && player.canDrawSectorCard()){
            match.setState(new DrawSectorPhaseState());
            match.doAction(player, action);
            return;
        }    

        if(action instanceof PlayItemAction && player.canPlayObject()){
            ItemCard card=((PlayItemAction) action).getItemCard();
            if(card.isPlayableMovePhase()&&player.canPlayObject()){
                card = match.playItemCard(player, card);
                if(card!=null){
                    match.notifyAllPlayer("ha giocato la carta: "+card.toString());
                    return;
                }
            }
            match.notifyPlayer(player, "Non puoi giocare questa carta");
            return; 
        }

        if(action instanceof EndPhaseAction && !player.canDrawSectorCard()){
            match.setState(new EndPhaseTurnState()); 
            match.doAction(player, action);
            return;
        }

        match.notifyPlayer(player, "Mossa non consentita durante UnsafeSectorPhase");
    }

}

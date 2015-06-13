/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.DiscardAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class HandFullState implements State {


    @Override
    public void doAction(Match match, Player player, Action action) {
        if(player == null){
            match.serviceMessage("Comando non valido");
        }
        
        if(action instanceof DiscardAction){
            ItemCard cardToDiscard = ((DiscardAction)action).getCardToDiscard();
            if(match.discardItemCardInItemDeck(player, cardToDiscard)){
                match.notifyAllPlayer(player.getNickName()+" ha scartato una carta oggetto");
                if(!player.getHand().isFull()){
                    match.setState(new EndPhaseTurnState());
                    match.doAction(player, new EndPhaseAction());
                }
            }
            else 
                match.notifyPlayer(player, "non possiedi questa carta");
            return;
        }
        
        if(action instanceof PlayItemAction){
            ItemCard card=((PlayItemAction) action).getItemCard();
            if(card.isPlayableEndPhase()&&player.canPlayObject()){
                card = match.playItemCard(player, card);
                if(card!=null){
                    match.notifyAllPlayer("ha giocato la carta: "+card.toString());
                    if(!player.getHand().isFull()){
                        match.setState(new EndPhaseTurnState());
                        match.doAction(player, new EndPhaseAction());
                        return;
                    }
                }
            }
            match.notifyPlayer(player, "Non puoi giocare questa carta");
            return; 
        }
        match.notifyPlayer(player, "Mossa non disponibile durante la HandFullPhase");
        return;
    }

}

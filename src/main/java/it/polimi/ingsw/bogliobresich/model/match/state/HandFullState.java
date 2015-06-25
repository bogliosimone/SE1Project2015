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
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.notifications.MovesAvaiable;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * state where the player need to discard or play a card cause his hand is full
 * @author simoneboglio
 *
 */
public class HandFullState implements State {


    @Override
    public void doAction(Match match, Player player, Action action) {
        if(player == null){
            match.serviceMessage(Commands.GENERIC_ERROR,"Azione non disponibile in fase di handFull");
            match.serviceMessage("Comando non valido");
        }
        
        if(action instanceof DiscardAction){
            ItemCard cardToDiscard = ((DiscardAction)action).getCardToDiscard();
            if(match.discardItemCardInItemDeck(player, cardToDiscard)){
                match.notifyAllPlayer(player.getNickName()+" ha scartato una carta oggetto");
                match.notifyAllPlayer(Commands.GENERIC_MESSAGE, player.getNickName()+" ha scartato una carta oggetto");
                match.notifyPlayer(Commands.DISCARD_CARD, cardToDiscard, player);
                if(!player.getHand().isFull()){
                    match.setState(new EndPhaseTurnState());
                    match.doAction(player, new EndPhaseAction());
                }
            }
            else{
                match.notifyPlayer(player, "non possiedi questa carta");
                match.notifyPlayer(Commands.CANT_DISCARD_CARD, null, player);
                match.notifyPlayer(Commands.MOVES_AVAIABLE, currentMoves(match,player),player);
            }
            return;
        }
        
        if(action instanceof PlayItemAction){
            ItemCard card=((PlayItemAction) action).getItemCard();
            if(card.isPlayableEndPhase()&&player.canPlayObject()){
                card = match.playItemCard(player, card);
                if(card!=null){
                    match.notifyAllPlayer("ha giocato la carta: "+card.toString());
                    match.notifyAllPlayer(Commands.ITEM_PLAYED, player.getNickName()+" ha giocato la carta: "+card.getName()+card.getInfo());
                    match.notifyPlayer(Commands.DISCARD_CARD, card, player);
                    if(!player.getHand().isFull()){
                        match.setState(new EndPhaseTurnState());
                        match.doAction(player, new EndPhaseAction());
                        return;
                    }
                }
            }
            match.notifyPlayer(player, "Non puoi giocare questa carta");
            match.notifyPlayer(Commands.CANT_PLAY_CARD, null, player);
            match.notifyPlayer(Commands.MOVES_AVAIABLE, currentMoves(match,player),player);
            return;
        }
        match.notifyPlayer(player, "Mossa non disponibile durante la HandFullPhase");
        match.notifyPlayer(Commands.MOVE_NO_AVAIABLE, null, player);
        match.notifyPlayer(Commands.MOVES_AVAIABLE, currentMoves(match,player),player);
        return;
    }

    private MovesAvaiable currentMoves(Match match,Player player){
        MovesAvaiable move=new MovesAvaiable();
        if(player.canPlayObject())
            move.setCanPlayItem(true);
        move.setCanDiscardItemCard(true);
        return move;
    }
}

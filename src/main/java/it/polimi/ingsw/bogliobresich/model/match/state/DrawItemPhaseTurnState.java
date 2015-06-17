package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawItemCardAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.notifications.MovesAvaiable;
import it.polimi.ingsw.bogliobresich.model.player.ItemHand;
import it.polimi.ingsw.bogliobresich.model.player.Player;

public class DrawItemPhaseTurnState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(player == null){
            match.serviceMessage(Commands.GENERIC_ERROR,"Azione non disponibile in fase di drawItemCard");
            match.serviceMessage("Comando non valido");
            return;
        }
        
        if(action instanceof DrawItemCardAction){
             match.notifyPlayer(player, "La tua carta settore contiene un oggetto");
             match.notifyPlayer(Commands.PLAYER_MESSAGE,"La tua carta settore contiene un oggetto",player);
             Deck deckItem= match.getItemDeck();
             Card tempCard;
             try {
                 tempCard = deckItem.drawCard();
                 if(!(tempCard instanceof ItemCard)){
                     match.serviceMessage("Fatal error draw item card");
                     match.serviceMessage(Commands.FATAL_ERROR, "errore nell'item deck, FATAL ERROR");
                     return;
                 }
                 ItemCard newCard= (ItemCard) tempCard; //carta pescata
                 ItemHand hand=player.getHand();
                 hand.addCard(newCard);
                 match.notifyPlayer(player, " hai pescato la carta oggetto "+newCard.toString());
                 match.notifyPlayer(Commands.DRAW_CARD, newCard, player);
                 if(hand.isFull()){
                     match.notifyPlayer(player, "hai la mano piena");
                     match.notifyPlayer(Commands.HAND_FULL, null,player);
                     match.notifyPlayer(Commands.MOVES_AVAIABLE, currentMoves(match,player),player);
                     match.setState(new HandFullState());
                 }
                 else{
                     match.setState(new EndPhaseTurnState());
                     match.doAction(player, new EndPhaseAction());
                 }
             } catch (CardFinishedException e) {
                 match.notifyPlayer(player, "le carte oggetto sono finite");
                 match.notifyPlayer(Commands.CARDS_END, null,player);
                 match.setState(new EndPhaseTurnState());
                 match.doAction(player, new EndPhaseAction());
             } 
             return;
         }

         
         match.notifyPlayer(player, "Mossa non disponibile durante la drawPhase");
         match.notifyPlayer(Commands.MOVE_NO_AVAIABLE, null, player);
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

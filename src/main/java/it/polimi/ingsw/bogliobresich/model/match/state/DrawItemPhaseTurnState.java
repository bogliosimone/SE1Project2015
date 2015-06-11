package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawItemCardAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.player.ItemHand;
import it.polimi.ingsw.bogliobresich.model.player.Player;

public class DrawItemPhaseTurnState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(player == null){
            match.serviceMessage("Comando non valido");
            return;
        }
        
        if(action instanceof DrawItemCardAction){
             match.notifyPlayer(player, "La tua carta settore contiene un oggetto");
             Deck deckItem= match.getItemDeck();
             Card tempCard;
             try {
                 tempCard = deckItem.drawCard();
                 if(!(tempCard instanceof ItemCard)){
                     match.serviceMessage("Fatal error draw item card");
                     return;
                 }
                 ItemCard newCard= (ItemCard) tempCard; //carta pescata
                 ItemHand hand=player.getHand();
                 hand.addCard(newCard);
                 match.notifyPlayer(player, " hai pescato la carta oggetto "+newCard.toString());
                 if(hand.isFull()){
                     match.notifyPlayer(player, "hai la mano piena, devi scartare una carta");
                     match.setState(new HandFullState());
                 }
                 else{
                     match.setState(new EndPhaseTurnState());
                     match.doAction(player, new EndPhaseAction());
                 }
             } catch (CardFinishedException e) {
                 match.notifyPlayer(player, "le carte oggetto sono finite");
                 match.setState(new EndPhaseTurnState());
                 match.doAction(player, new EndPhaseAction());
             } 
             return;
         }

         
         match.notifyPlayer(player, "Mossa non disponibile durante la drawPhase");
         return;
    }

}

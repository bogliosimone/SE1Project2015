package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.DiscardAction;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawItemCardAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.player.ItemHand;
import it.polimi.ingsw.bogliobresich.model.player.Player;

public class DrawPhaseTurnState implements State {
    boolean handIsFull=false;

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(!player.equals(match.getCurrentPlayer())){
            match.notifyPlayer(player, "Non è il tuo turno");
            return;
        }  
        
        if(action instanceof DrawItemCardAction){
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
                     handIsFull=true;
                     match.notifyPlayer(player, "hai la mano piena, devi scartare una carta");
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
         if(action instanceof DiscardAction && handIsFull){
             ItemCard cardToDiscard = ((DiscardAction)action).getCardToDiscard();
             if(match.discardItemCardInItemDeck(player, cardToDiscard)){
                 match.notifyAllPlayer(player.getNickName()+" ha scartato una carta oggetto");
                 match.setState(new EndPhaseTurnState());
                 match.doAction(player, new EndPhaseAction());
             }
             else 
                 match.notifyPlayer(player, "non possiedi questa carta");
             return;
         }
         
         if(action instanceof PlayItemAction && handIsFull){
             ItemCard card=((PlayItemAction) action).getItemCard();
             if(card.isPlayableEndPhase()&&player.canPlayObject()){
                 card = card.play(match, player);
                 if(card!=null){
                     match.notifyAllPlayer("ha giocato la carta: "+card.toString());
                     match.setState(new EndPhaseTurnState());
                     match.doAction(player, new EndPhaseAction());
                 }
                 else
                     match.notifyPlayer(player, "Non possiedi questa carta");
                 return;
             }
             else
                 match.notifyPlayer(player, "Non puoi giocare questa carta");
             return; 
         }
         
         
         match.serviceMessage("Mossa non disponibile durante la drawPhase");
         return;
    }

}

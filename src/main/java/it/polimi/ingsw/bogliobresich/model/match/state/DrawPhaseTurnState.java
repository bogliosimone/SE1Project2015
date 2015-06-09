package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.AttackAction;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawItemCardAction;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawSectorAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

public class DrawPhaseTurnState implements State {


    @Override
    public void doAction(Match match, Player player, Action action) {
        if(!player.equals(match.getCurrentPlayer())){
            match.notifyPlayer(player, "Non è il tuo turno");
            return;
        }  
        
        if(action instanceof DrawItemCardAction){
            if(!player.canAttack() && !player.canDrawSectorCard()){
                match.setState(new EndPhaseTurnState()); 
                match.doAction(player, new EndPhaseAction());
                return;
             }
             if(!player.canAttack() && player.canDrawSectorCard()){
                 match.doAction(player, new DrawSectorAction());
                 return;
              }
             match.notifyPlayer(player, "Attacchi o peschi una carta settore?");
             return;
         }
         if(action instanceof AttackAction){
             match.setState(new AttackPhaseTurnState()); 
             match.doAction(player, action);
             return;
         }
         if(action instanceof DrawSectorAction){
             Deck deckItem= match.getItemDeck();
             Card tempCard;
             try {
                 tempCard = deckItem.drawCard();
                 if(!(tempCard instanceof ItemCard)){
                     match.serviceMessage("Fatal error draw sector card");
                     return;
                 }
                 //ItemCard newCard= (ItemCard) tempCard;
                 //finalmente ho pescato una carta
                 
             } catch (CardFinishedException e) {
                 match.serviceMessage("Fatal error draw sector card");
             } 
             return;
         }

    }

}

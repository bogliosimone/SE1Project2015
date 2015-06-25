/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.action;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;

/**
 * action for discard a card when hand is full and player want discard a card
 * @author simoneboglio
 */
public class DiscardAction implements Action {
    ItemCard cardToDiscard;
    
    public DiscardAction(ItemCard cardToDiscard){
        this.cardToDiscard=cardToDiscard;
    }
    
    public ItemCard getCardToDiscard(){
        return this.cardToDiscard;
    }
}

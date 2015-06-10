/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.action;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;

/**
 * @author simoneboglio
 *
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

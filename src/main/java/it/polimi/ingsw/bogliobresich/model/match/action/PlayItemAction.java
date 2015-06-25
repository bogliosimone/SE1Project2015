/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.action;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;

/**
 * action used by player when he want play a card
 * @author simoneboglio
 */
public class PlayItemAction implements Action {
    ItemCard card;
    public PlayItemAction(ItemCard itemCard){
        this.card=itemCard;
    }
    public ItemCard getItemCard(){
        return this.card;
    }
}

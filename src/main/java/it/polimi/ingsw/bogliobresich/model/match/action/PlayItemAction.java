/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.action;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;

/**
 * @author simoneboglio
 *
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

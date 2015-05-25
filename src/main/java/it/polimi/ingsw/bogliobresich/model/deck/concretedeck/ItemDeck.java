/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.ConstantsCard;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SectorCard;
import it.polimi.ingsw.bogliobresich.model.deck.ConstantsDeck;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;

/**
 * @author matteobresich
 *
 */
public class ItemDeck extends Deck {

    /**
     * Class constructor
     */
    public ItemDeck() {
        addItemCardAttack(ConstantsDeck.N_ITEMCARD_ATTACK);
        addItemCardDefence(ConstantsDeck.N_ITEMCARD_DEFENCE);
        addItemCardTeleport(ConstantsDeck.N_ITEMCARD_TELEPORT);
        addItemCardSpotlight(ConstantsDeck.N_ITEMCARD_SPOTLIGHT);
        addItemCardSedatives(ConstantsDeck.N_ITEMCARD_SEDATIVES);
        addItemCardAdrenaline(ConstantsDeck.N_ITEMCARD_ADRENALINE);
        
    }
    
    private void addItemCardAttack(int nItem) {
        Card card;
        int index;
        for(index = 0; index < nItem; index++) {
            card = new ItemCard(ConstantsCard.ATTACK);
            super.addCard(card);
        }
    }
    
    private void addItemCardDefence(int nItem) {
        Card card;
        int index;
        for(index = 0; index < nItem; index++) {
            card = new ItemCard(ConstantsCard.DEFENCE);
            super.addCard(card);
        }
    }
    
    private void addItemCardTeleport(int nItem) {
        Card card;
        int index;
        for(index = 0; index < nItem; index++) {
            card = new ItemCard(ConstantsCard.TELEPORT);
            super.addCard(card);
        }
    }
    
    private void addItemCardSpotlight(int nItem) {
        Card card;
        int index;
        for(index = 0; index < nItem; index++) {
            card = new ItemCard(ConstantsCard.SPOTLIGHT);
            super.addCard(card);
        }
    }
    
    private void addItemCardSedatives(int nItem) {
        Card card;
        int index;
        for(index = 0; index < nItem; index++) {
            card = new ItemCard(ConstantsCard.SEDATIVES);
            super.addCard(card);
        }
    }
    
    private void addItemCardAdrenaline(int nItem) {
        Card card;
        int index;
        for(index = 0; index < nItem; index++) {
            card = new ItemCard(ConstantsCard.ADRENALINE);
            super.addCard(card);
        }
    }
}

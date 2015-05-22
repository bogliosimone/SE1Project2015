/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.SectorCard;
import it.polimi.ingsw.bogliobresich.model.deck.ConstantsDeck;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;

/**
 * @author matteobresich
 *
 */
public class SectorDeck extends Deck {
    /**
     * Class constructor
     */
    public SectorDeck() {
        for (int i = 0; i < ConstantsDeck.NSECTORCARD; i++) {
            Card card = new SectorCard();
            super.addCard(card);
        }
        
    }

}

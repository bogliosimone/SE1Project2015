/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.ConstantsCard;
import it.polimi.ingsw.bogliobresich.model.cards.PortholeCard;
import it.polimi.ingsw.bogliobresich.model.deck.ConstantsDeck;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;

/**
 * @author matteobresich
 *
 */
public class PortholeDeck extends Deck {
    /**
     * Class constructor
     */
    public PortholeDeck() {
        Card card;
        for (int i = 0; i < ConstantsDeck.NPORTHOLECARD; i++) {
            
            //One works, one not
            if(i%2 == 0) {
                card = new PortholeCard(ConstantsCard.PORTHOLE_WORKS);
            }
            else {
                card = new PortholeCard(ConstantsCard.PORTHOLE_BROKEN);
            }
            super.addCard(card);
        }
    }

}

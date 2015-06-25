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
 * The <code>PortholeDeck</code> implements Deck. Can only contains PortholeCards. This Deck could not be reshuffled.
 * 
 * @author matteo bresich
 * @author simone boglio
 * 
 * @version 1.0
 * @see it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException
 * @see it.polimi.ingsw.bogliobresich.model.deck.exception.NoReShuffleableException
 */
public class PortholeDeck extends Deck {
    private int lastId = 0;
    /**
     * Class constructor
     */
    private static final int TWO = 2;
    public PortholeDeck() {
        super.setReShuffle(false);
        Card card;
        for (int i = 0; i < ConstantsDeck.NPORTHOLECARD; i++) {

            //One works, one not
            if(i%TWO == 0) {
                card = new PortholeCard(ConstantsCard.PORTHOLE_WORKS);
            } else {
                card = new PortholeCard(ConstantsCard.PORTHOLE_BROKEN);
            }
            super.addCard(card,generateId());
        }
        super.shuffle();
    }
    
    private int generateId() {
        return lastId++;
        }

}

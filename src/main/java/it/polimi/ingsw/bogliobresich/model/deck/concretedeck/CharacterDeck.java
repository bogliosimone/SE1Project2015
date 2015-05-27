/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import it.polimi.ingsw.bogliobresich.model.Characters;
import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;

/**
 * @author matteobresich
 *
 */
public class CharacterDeck extends Deck {

    /**
     * Class constructor
     */
    public CharacterDeck() {
        super.setReShuffle(false);
        for(Characters characterCards : Characters.values()) {
            Card card = new CharacterCard(characterCards);
            super.addCard(card);
        }
    }
}

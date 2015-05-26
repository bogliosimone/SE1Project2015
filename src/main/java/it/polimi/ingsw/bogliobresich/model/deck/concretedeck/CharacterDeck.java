/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import it.polimi.ingsw.bogliobresich.model.Character;
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
        for(Character characterCards : Character.values()) {
            Card card = new CharacterCard(characterCards);
            super.addCard(card);
        }
    }
}

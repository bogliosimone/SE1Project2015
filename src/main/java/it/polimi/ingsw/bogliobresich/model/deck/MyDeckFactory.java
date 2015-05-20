/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck;

import it.polimi.ingsw.bogliobresich.model.deck.concretedeck.CharacterDeck;
import it.polimi.ingsw.bogliobresich.model.deck.concretedeck.ItemDeck;

/**
 * @author matteobresich
 *
 */
public class MyDeckFactory extends DeckFactory {

	/* (non-Javadoc)
	 * @see test.DeckFactory#createItemDeck()
	 */
	@Override
	public Deck createItemDeck() {
		return new ItemDeck();
	}

	@Override
    public Deck createCharacterDeck() {
	    return new CharacterDeck();
    }

}

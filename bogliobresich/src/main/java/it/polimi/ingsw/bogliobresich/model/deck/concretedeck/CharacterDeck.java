/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import it.polimi.ingsw.bogliobresich.model.deck.Deck;

/**
 * @author matteobresich
 *
 */
public class CharacterDeck extends Deck {
	
	public CharacterDeck() {
		System.out.println("Costruttore");
	}

	/* (non-Javadoc)
	 * @see test.Deck#shuffle()
	 */
	@Override
	public void shuffle() {
		// TODO Auto-generated method stub
		System.out.println("Shuffle di Character");
	}

}

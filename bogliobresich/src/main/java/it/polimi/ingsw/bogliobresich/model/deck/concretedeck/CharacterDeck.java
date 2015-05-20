/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.Character;
import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;

/**
 * @author matteobresich
 *
 */
public class CharacterDeck extends Deck {
	
	public CharacterDeck() {
		System.out.println("Costruttore");
		for(Character characterCards : Character.values()) {
			Card card = new CharacterCard();
			super.addCard(card);
		}
			
	}

	/* (non-Javadoc)
	 * @see model.deck.Deck#shuffle()
	 */
	@Override
	public void shuffle() {
		super.shuffle();
	}
}

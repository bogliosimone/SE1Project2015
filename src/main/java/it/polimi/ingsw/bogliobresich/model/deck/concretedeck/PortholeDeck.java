/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.PortholeCard;
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
		for (int i = 0; i < 6; i++) {
			Card card = new PortholeCard();
			super.addCard(card);
		}
	}

}

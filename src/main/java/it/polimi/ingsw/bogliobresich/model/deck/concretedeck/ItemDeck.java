/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;

/**
 * @author matteobresich
 *
 */
public class ItemDeck extends Deck {

	/**
	 * Class contructor
	 */
	public ItemDeck() {
		for (int i = 0; i < 12; i++) {
			Card card = new ItemCard();
			super.addCard(card);
		}
	}
}

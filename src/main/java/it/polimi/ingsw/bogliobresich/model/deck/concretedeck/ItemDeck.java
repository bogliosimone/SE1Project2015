/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.deck.ConstantsDeck;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;

/**
 * @author matteobresich
 *
 */
public class ItemDeck extends Deck {

	/**
	 * Class constructor
	 */
	public ItemDeck() {
		for (int i = 0; i < ConstantsDeck.NITEMCARD; i++) {
			Card card = new ItemCard();
			super.addCard(card);
		}
	}
}

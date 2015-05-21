/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.SectorCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;

/**
 * @author matteobresich
 *
 */
public class SectorDeck extends Deck {
	/**
	 * Class constructor
	 */
	public SectorDeck() {
		for (int i = 0; i < 25; i++) {
			Card card = new SectorCard();
			super.addCard(card);
		}
		
	}

}

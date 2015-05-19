/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck;

/**
 * @author matteobresich
 * Creator
 */
public abstract class DeckGenerator {
	public Deck generateDeck(String tipo){
		Deck deck;
		deck = createDeck(tipo);
		deck.shuffle();
		
		return deck;
	}
	
	protected abstract Deck createDeck(String tipo);
}

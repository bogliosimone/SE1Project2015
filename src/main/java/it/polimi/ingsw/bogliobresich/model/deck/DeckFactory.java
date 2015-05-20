/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck;

/**
 * @author matteobresich
 *
 */
public abstract class DeckFactory {
	public abstract Deck createItemDeck();
	public abstract Deck createCharacterDeck();
}

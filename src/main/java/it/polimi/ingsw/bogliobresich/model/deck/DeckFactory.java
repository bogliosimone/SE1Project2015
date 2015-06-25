/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck;

/**
 * @author matteo bresich
 * @author simone boglio
 *
 */
public abstract class DeckFactory {
    public abstract Deck createItemDeck();
    public abstract Deck createCharacterDeck();
    public abstract Deck createCharacterDeck(int n);
    public abstract Deck createPortholeDeck();
    public abstract Deck createSectorDeck();
}

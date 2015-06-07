/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck;

import it.polimi.ingsw.bogliobresich.model.deck.concretedeck.CharacterDeck;
import it.polimi.ingsw.bogliobresich.model.deck.concretedeck.ItemDeck;
import it.polimi.ingsw.bogliobresich.model.deck.concretedeck.PortholeDeck;
import it.polimi.ingsw.bogliobresich.model.deck.concretedeck.SectorDeck;

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
    
    public Deck createCharacterDeck(int n) {
        return new CharacterDeck(n);
    }

    @Override
    public Deck createPortholeDeck() {
        return new PortholeDeck();
    }
    
    @Override
    public Deck createSectorDeck() {
        return new SectorDeck();
    }

}

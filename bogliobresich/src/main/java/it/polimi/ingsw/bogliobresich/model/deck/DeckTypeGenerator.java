/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck;

/**
 * @author matteobresich
 * Concrete creator
 */
public class DeckTypeGenerator extends DeckGenerator {
	@Override
    protected Deck createDeck(String tipo) {
		if (tipo.equals("item")) {
			return new ItemDeck();
		}
		return null;
    }

}

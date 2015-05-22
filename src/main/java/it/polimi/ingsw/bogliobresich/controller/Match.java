/**
 * 
 */
package it.polimi.ingsw.bogliobresich.controller;

import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.DeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.MyDeckFactory;

/**
 * @author matteobresich
 *
 */
public class Match {
    public static void main (String args[]) {
        DeckFactory factory = new MyDeckFactory();
        Deck characterDeck = factory.createCharacterDeck();
        Deck itemDeck = factory.createItemDeck();
        Deck sectorDeck = factory.createSectorDeck();
        Deck portholeDeck = factory.createPortholeDeck();

        characterDeck.showCards();
        itemDeck.showCards();
        sectorDeck.showCards();
        portholeDeck.showCards();

        characterDeck.shuffle();
        characterDeck.showCards();
        
        itemDeck.shuffle();
        itemDeck.showCards();
        
        
        for (int i = 0; i < 8; i++)
            System.out.println("Pesco " + characterDeck.drawCard());
        characterDeck.showCards();
    }

}

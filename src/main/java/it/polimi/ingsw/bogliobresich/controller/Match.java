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

        characterDeck.showCards();
        itemDeck.showCards();

        //itemDeck.shuffle();
        characterDeck.shuffle();
        characterDeck.showCards();
        for (int i = 0; i < 8; i++)
            System.out.println("Pesco " + characterDeck.drawCard());
        characterDeck.showCards();
    }

}

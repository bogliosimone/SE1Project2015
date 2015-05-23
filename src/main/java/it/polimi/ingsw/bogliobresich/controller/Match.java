/**
 * 
 */
package it.polimi.ingsw.bogliobresich.controller;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.DeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.MyDeckFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author matteobresich
 *
 */
public class Match {
    public static void main (String args[]) {
        DeckFactory factory = new MyDeckFactory();
        Deck characterDeck = factory.createCharacterDeck();

        characterDeck.showCards();

        characterDeck.shuffle();
        characterDeck.showCards();
        
        List <Card> cards = new ArrayList();
        for (int i = 0; i < 8; i++)
        {
            Card c = characterDeck.drawCard();
            cards.add(c);
            System.out.println("Pesco " + c);
            
        }  
        characterDeck.showCards();
        
        for (int i = 0; i < 8; i++)
        {
            characterDeck.discardCard(cards.get(i));
            
        }
        characterDeck.showCards();
        
    }

}

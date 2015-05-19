/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck;


import it.polimi.ingsw.bogliobresich.model.cards.Card;

import java.util.ArrayList;


/**
 * @author matteobresich
 *
 */
public abstract class Deck {
    
    private ArrayList<Card> stackOfCards = new ArrayList<Card>();
    
    public abstract void shuffle();
    
    /**
     * Add a card to the deck 
     * @param c Card: Card to add in the deck*/
    public void addCard(Card c) {
        stackOfCards.add(c);
    }
    
    /**
     * Draw a card from the deck
     * @return Card drawn*/ 
    public Card drawCard() {
        return stackOfCards.remove(stackOfCards.size()-1);
    }
}

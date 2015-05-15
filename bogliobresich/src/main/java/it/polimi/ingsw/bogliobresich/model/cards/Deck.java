/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import java.util.ArrayList;

/**
 * @author Matteo
 *
 */
public class Deck {
    
    private ArrayList<Card> stackOfCards = new ArrayList<Card>();
    
    public Deck() {
        
    }
    
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
    
    /**
     *
     **/ 
    public void shuffle() {
        //TODO
    }

}

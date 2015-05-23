/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck;


import it.polimi.ingsw.bogliobresich.model.cards.Card;

import java.util.ArrayList;
import java.util.List;


/**
 * @author matteobresich
 *
 */
public abstract class Deck {

    //Cards that are in the deck
    private List<Card> stackOfCards = new ArrayList<Card>();
    //Cards discarded belonging to the deck
    private List<Card> discardedCards = new ArrayList<Card>();
    //Cards drawn out the deck belonging to the deck
    private List<Card> drawnOutCards = new ArrayList<Card>();

    
    public void shuffle() {
        ArrayList<Card> temp = new ArrayList<Card>();
        while(!isStackOfCardEmpty()) {
            int loc=(int)(Math.random()*stackOfCards.size());
            temp.add(stackOfCards.get(loc));
            stackOfCards.remove(loc);
        }
        stackOfCards = temp;
    }

    /**
     * Add a card to the deck 
     * @param c Card: Card to add in the deck*/
    protected void addCard(Card c) {
        stackOfCards.add(c);
    }

    /**
     * Return if the stack of cards is empty
     * @return Return true if the stack is empty
     */
    public boolean isStackOfCardEmpty() {
        return stackOfCards.isEmpty();
    }

    /**
     * Draw a card from the deck
     * @return Card drawn*/ 
    public Card drawCard() {
        Card c = stackOfCards.remove(stackOfCards.size()-1);
        drawnOutCards.add(c);
        return c;
    }
    
    
    public void showCards() {
        System.out.println("Show Cards:");
        for(Card c:stackOfCards) {
            System.out.println("Card "+c.toString());
        }
    }

    @Override
    public String toString() {
        return "Deck [stackOfCards=" + stackOfCards + "]";
    }

}
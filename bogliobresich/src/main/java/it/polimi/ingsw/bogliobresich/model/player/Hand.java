/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.player;

import it.polimi.ingsw.bogliobresich.model.cards.Card;

import java.util.ArrayList;

/**
 * @author Matteo
 * @author simoneboglio
 *
 */
public class Hand {

    ArrayList<Card> cards;
    private int maximumNumberOfCards;

    /**
     * Class constructor.
     */
    public Hand() {
        maximumNumberOfCards = 0;
        cards = new ArrayList<Card>();
    }

    /**	
     * Return true if the hand is full
     */
    public boolean isFull() {
        if(cards.size() > this.maximumNumberOfCards)
            return true;
        return false;
    }
    
    /** 
     * Return true if the hand is empty
     */
    public boolean isEmpty() {
        if(cards.size() == 0)
            return true;
        return false;
    }

    /**
     * Add a card in the hand. Return false if the hand is full.
     */
    public boolean addCard(Card c) {
        return cards.add(c);
    }

    /**
     * Remove a card if the card is in the hand. Return false if the card is not present in the hand.
     */
    public boolean removeCard(Card c) {
        return cards.remove(c);//TODO controllare che la remove rimuova lo stesso tipo e non lo stesso oggetto
    }

    /**
     * Remove a card if the card is in the hand. Return false if the card is not present in the hand.
     */
    public boolean searchCard(Card c) {
        return cards.contains(c);//TODO controllare se cerca lo stesso tipo e non lo stesso oggetto
    }

    /**
     * 
     */
    //public Card[] list(Card c) {} TODO


}

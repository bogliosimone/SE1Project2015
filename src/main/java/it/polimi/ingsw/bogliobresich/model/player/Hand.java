/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.player;

import it.polimi.ingsw.bogliobresich.model.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matteo
 * @author simoneboglio
 *
 */
public class Hand {

    List<Card> cards;
    private int maximumNumberOfCards;

    /**
     * Class constructor.
     */
    public Hand() {
        maximumNumberOfCards = 0;
        cards = new ArrayList<Card>();
    }

    public Hand(int numberOfCards) {
        maximumNumberOfCards = numberOfCards;
        cards = new ArrayList<Card>();
    }

    /**	
     * Return true if the hand is full
     */
    public boolean isFull() {
        if(cards.size() >= this.maximumNumberOfCards) {
            return true;
        }
        return false;
    }

    /** 
     * Return true if the hand is empty
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Add a card in the hand if is not full. Return false if the hand is full.
     */
    public boolean addCard(Card c) {
        if (!this.isFull()) {
            return cards.add(c);
        }
        return false;
    }

    /**
     * Remove a card if the card is in the hand. Return false if the card is not present in the hand.
     */
    public boolean removeCard(Card c) {
        return cards.remove(c);
    }

    /**
     * Remove a card if the card is in the hand. Return false if the card is not present in the hand.
     */
    public boolean searchCard(Card c) {
        return cards.contains(c); //TODO
    }

    /**
     * Return all the cards in the hand
     */
    public Card[] list() {
        Card [] card = new Card[cards.size()];
        for (int i = 0; i < cards.size(); i++) {
            card[i] = this.cards.get(i);
        }
        return card;
    }

    @Override
    public String toString() {
        return "Hand [cards=" + cards + "]";
    }


}

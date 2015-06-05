/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.player;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;

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
        this(5);
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
        if(isItemCard(c)) {
            if (!this.isFull()) {
                return cards.add(c);
            }
            return false;
        } else {
            throw new IllegalArgumentException("Card is not valid!");
        }
    }

    /**
     * Remove a card if the card is in the hand. Return false if the card is not present in the hand.
     */
    public boolean removeCard(Card c) {
        if(isItemCard(c)) {
            return cards.remove(c);
        } else {
            throw new IllegalArgumentException("Card is not valid!");
        }
    }

    /**
     * Remove a card if the card is in the hand. Return false if the card is not present in the hand.
     */
    public boolean searchCard(Card c) {
        if(isItemCard(c)) {
            //contains use equals
            return cards.contains(c);
        } else {
            throw new IllegalArgumentException("Card is not valid!");
        }
    }
    
    private boolean isItemCard(Card c) {
        if(c instanceof ItemCard) {
            return true;
        }
        return false;
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

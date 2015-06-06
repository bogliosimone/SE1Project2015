/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.player;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matteo
 * @author simoneboglio
 *
 */
public class ItemHand {

    List<ItemCard> cards;
    private int maximumNumberOfCards;

    /**
     * Class constructor.
     * @param numberOfCards is the maximum number of cards that a hand can hold
     */
    public ItemHand(int numberOfCards) {
        maximumNumberOfCards = numberOfCards;
        cards = new ArrayList<ItemCard>();
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
    public boolean addCard(ItemCard c) {
        if (!this.isFull()) {
            return cards.add(c);
        }
        return false;
    }

    /**
     * Remove a card if the card is in the hand. Return false if the card is not present in the hand.
     */
    public boolean removeCard(ItemCard c) {
        return cards.remove(c);
    }

    /**
     * 
     */

    public boolean cardIsIn(ItemCard c) {
        return cards.contains(c);
    }

    /**
     * Return all the cards in the hand
     */
    public List<ItemCard> listOfCards() {
        return this.cards;
    }

    @Override
    public String toString() {
        return "ItemHand [cards=" + cards + "]";
    }
}

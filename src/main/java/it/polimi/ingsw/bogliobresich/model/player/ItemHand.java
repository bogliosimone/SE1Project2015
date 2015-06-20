/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.player;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.DefenceItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matteo
 * @author simoneboglio
 *
 */
public class ItemHand implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -891586495121456948L;
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
    
    public List<ItemCard> getAllCard(){
        return this.cards;
    }
    
    public void discardHand(){
        this.cards.clear();
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
        int idCard= c.getId();
        for(Card tmpCard: cards)
            if(idCard==tmpCard.getId()){
                cards.remove(tmpCard);
                return true;
            }
        return false;
    }
    
    public boolean removeCard(int idCard) {
        for(Card tmpCard: cards)
            if(idCard==tmpCard.getId()){
                cards.remove(tmpCard);
                return true;
            }
        return false;
    }
    
    
    /**
     * 
     */

    public boolean cardIsIn(ItemCard c) {
        int idCard= c.getId();
        for(Card tmpCard: cards)
            if(idCard==tmpCard.getId())
                return true;
        return false; 
    }
    
    public ItemCard getCard(int idCard) {
        for(ItemCard tmpCard: cards)
            if(idCard==tmpCard.getId())
                return  tmpCard;
        return null; 
    }
    
    public ItemCard getDefenceCard(){
        for(Card tmpCard: cards)
            if(tmpCard instanceof DefenceItemCard){
                return (ItemCard) tmpCard;
            }
        return null; 
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

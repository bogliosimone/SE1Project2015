/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.player;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.DefenceItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * item of hand, you can add, remove, search item card in the hand
 * this class is serializable
 * @author Matteo
 * @author simoneboglio
 *
 */
public class ItemHand implements Serializable {

    private static final long serialVersionUID = -891586495121456948L;
    List<ItemCard> cards;
    private int maximumNumberOfCards;

    /**
     * Class constructor.
     * @param numberOfCards is the maximum number of cards that a hand can hold
     */
    public ItemHand(int numberOfCards) {
        maximumNumberOfCards = numberOfCards;
        cards = new Vector<ItemCard>();
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
     * get all the cards in the hand
     * @return list with all card
     */
    public List<ItemCard> getAllCard(){
        return this.cards;
    }

    /**
     * clear the hand
     */
    public void discardHand(){
        if(!cards.isEmpty())
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

    /**
     * remove a card from hand by id of card
     * @param idCard id of the card
     * @return true if the card was removed, false if the hand don't contain this card
     */
    public boolean removeCard(int idCard) {
        for(Card tmpCard: cards)
            if(idCard==tmpCard.getId()){
                cards.remove(tmpCard);
                return true;
            }
        return false;
    }


    /**
     * return true if hand contain the item card
     * @param c card thet you want search in the hand
     * @return true if the card is in the hand
     */
    public boolean cardIsIn(ItemCard c) {
        int idCard= c.getId();
        for(Card tmpCard: cards)
            if(idCard==tmpCard.getId())
                return true;
        return false; 
    }

    /**
     * return the item card by id, null if the hand don't contain the card
     * @param idCard that you want pick
     * @return the item card, null if hand no cointa the card
     */
    public ItemCard getCard(int idCard) {
        for(ItemCard tmpCard: cards)
            if(idCard==tmpCard.getId())
                return  tmpCard;
        return null; 
    }

    /**
     * search if hand contain a defence card, if there is return the card instead return null
     * @return the item card defence, null if hand don't contain defence card
     */
    public ItemCard getDefenceCard(){
        for(Card tmpCard: cards)
            if(tmpCard instanceof DefenceItemCard){
                return (ItemCard) tmpCard;
            }
        return null; 
    }

    /**
     * return the list of all cards in the hand
     * @return list of all cards in the hand
     */
    public List<ItemCard> listOfCards() {
        return this.cards;
    }

    @Override
    public String toString() {
        return "ItemHand [cards=" + cards + "]";
    }
}

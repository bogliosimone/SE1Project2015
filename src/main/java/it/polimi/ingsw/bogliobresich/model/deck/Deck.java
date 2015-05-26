/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck;


import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;
import it.polimi.ingsw.bogliobresich.model.deck.exception.NoReShuffleableException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


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
    //A deck by default is re-shuffleable
    private boolean isReShuffleable = true;
    

    /**
     * Shuffle all the cards in the deck. To use when the deck is created. 
     * */
    public void shuffle() {
        if(!isEmpty()) {
            List<Card> temp = new ArrayList<Card>();
            while(!isEmpty()) {
                int loc=(int)(Math.random()*stackOfCards.size());
                temp.add(stackOfCards.get(loc));
                stackOfCards.remove(loc);
            }
            stackOfCards = temp;
        }
    }
    /**
     * If the Deck is reshuffleable, shuffle all the cards that are in the discarded stack. To use for reshuffle the deck.
     * @throws CardFinishedException 
     * 
     * */
    public void reShuffle() throws CardFinishedException {
        if(isReShuffleable) {
            if(!isDiscardedCardsEmpty()) {
                List<Card> temp = new ArrayList<Card>();
                while(!isDiscardedCardsEmpty()) {
                    int loc=(int)(Math.random()*discardedCards.size());
                    temp.add(discardedCards.get(loc));
                    discardedCards.remove(loc);
                }
                stackOfCards = temp;
            } else {
                throw new CardFinishedException();
            }
        } else {
            throw new NoReShuffleableException();
        }
    }

    /**
     * Add a card to the deck 
     * @param c Card: Card to add in the deck*/
    protected void addCard(Card c) {
        stackOfCards.add(c);
    }

    /**
     * Return if the stack of cards is empty.
     * @return Return true if the stack is empty
     */
    public boolean isEmpty() {
        return stackOfCards.isEmpty();
    }
    
    /**
     * Return if the stack of discarded cards is empty.
     * @return Return true if the stack is empty
     */
    public boolean isDiscardedCardsEmpty() {
        return discardedCards.isEmpty();
    }

    /**
     * Draw a card from the deck
     * @return Card drawn
     * */ 
    public Card drawCard() throws CardFinishedException {
        Card c;
        if(!isEmpty()) {
            c = stackOfCards.remove(stackOfCards.size()-1);
            drawnOutCards.add(c);
            return c;
        } else {
            reShuffle();
            c = stackOfCards.remove(stackOfCards.size()-1);
            drawnOutCards.add(c);
            return c;
        }
    }

    /**
     * Discard a card to the deck
     * @param card the card that i want to discard
     */
    public void discardCard(Card card) {
        int index = drawnOutCards.indexOf(card);
        if (index == -1) {
            throw new NoSuchElementException();
        } else {
            drawnOutCards.remove(card);
            discardedCards.add(card);
        }

    }
 
    /**
     * Set if deck can be remixed
     * @param reshuffleable true if the deck can be remixed
     */
    public void setReShuffle(boolean reshuffleable) {
        this.isReShuffleable = reshuffleable;
    }

    public void showCards() {
        System.out.println("Show stackOfCards:");
        for(Card c:stackOfCards) {
            System.out.println("Card " + c.toString());
        }
        System.out.println("Show drawnOutCards:");
        for(Card c1:drawnOutCards) {
            System.out.println("Card " + c1.toString());
        }
        System.out.println("Show discardedCards:");
        for(Card c2:discardedCards) {
            System.out.println("Card " + c2.toString());
        }
    }
    
    @Override
    public String toString() {
        return "Deck [stackOfCards=" + stackOfCards + "]";
    }
}

/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import it.polimi.ingsw.bogliobresich.model.Characters;
import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.cards.ConstantsCard;
import it.polimi.ingsw.bogliobresich.model.deck.ConstantsDeck;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>CharacterDeck</code> implements Deck. Can only contains CharacterCards. This Deck could not be reshuffled.
 * 
 * @author matteo bresich
 * @author simone boglio
 * 
 * @version 1.0
 * @see it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException
 * @see it.polimi.ingsw.bogliobresich.model.deck.exception.NoReShuffleableException
 */
public class CharacterDeck extends Deck {
    private static final int TWO = 2;
    private int lastId = 0;
    /**
     * Class constructor
     */
    public CharacterDeck(int nPlayer) {
        
        if(nPlayer > ConstantsDeck.NCHARACTERCARD) {
            throw new IllegalArgumentException();
        }
        super.setReShuffle(false);
        
        List<Card> alienCards = new ArrayList<Card>();
        List<Card> humanCards = new ArrayList<Card>();
        
        for(Characters characterCards : Characters.values()) {
            if(characterCards.getCharacterType() == ConstantsCard.ALIEN) {
                alienCards.add(new CharacterCard(characterCards));
            }
            else if(characterCards.getCharacterType() == ConstantsCard.HUMAN) {
                humanCards.add(new CharacterCard(characterCards));
            }
        }
        
        for (int cardIndex = 0; cardIndex < nPlayer; cardIndex++) {
            if(cardIndex%TWO == 0) {
              super.addCard(alienCards.remove(alienCards.size()-1),generateId());
          } else {
              super.addCard(humanCards.remove(humanCards.size()-1),generateId());
          }
        }
        super.shuffle();
    }
    
    @Override
    public Card drawCard() throws CardFinishedException {
        Card c;
        if(!isEmpty()) {
            c = stackOfCards.remove(stackOfCards.size()-1);
            discardedCards.add(c);
            return c;
        } else {
            reShuffle();
            c = stackOfCards.remove(stackOfCards.size()-1);
            discardedCards.add(c);
            return c;
        }
    }

    public CharacterDeck() {
        this(8);
        }
    
    private int generateId() {
        return lastId++;
        }
}

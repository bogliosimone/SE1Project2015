/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.ConstantsCard;
import it.polimi.ingsw.bogliobresich.model.cards.SectorCard;
import it.polimi.ingsw.bogliobresich.model.deck.ConstantsDeck;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;

/**
 * @author matteobresich
 *
 */
public class SectorDeck extends Deck {
    private int lastId = 0;
    /**
     * Class constructor
     */
    public SectorDeck() {

        addSectorCardWithNoiseMySector(ConstantsDeck.N_SECTORCARD_NOISE_MY_W_ITEM,ConstantsDeck.N_SECTORCARD_NOISE_MY_NO_ITEM);
        addSectorCardWithNoiseAnySector(ConstantsDeck.N_SECTORCARD_NOISE_ANY_W_ITEM,ConstantsDeck.N_SECTORCARD_NOISE_ANY_NO_ITEM);
        addSectorCardWithNoNoise(ConstantsDeck.N_SECTORCARD_SILENCE_W_ITEM,ConstantsDeck.N_SECTORCARD_SILENCE_NO_ITEM);
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



    private int generateId() { return lastId++; }
    
    //item e no item
    private void addSectorCardWithNoiseAnySector(int wItem, int wNoItem) {
        Card card;
        int index;
        for(index = 0; index < wItem; index++) {
            //create a SectorCard with noise in any sector and with an item to draw
            card = new SectorCard(ConstantsCard.NOISE_ANY_SECTOR,true);
            super.addCard(card,generateId());
        }
        for(index = 0; index < wNoItem; index++) {
            //create a SectorCard with noise in any sector and without an item to draw
            card = new SectorCard(ConstantsCard.NOISE_ANY_SECTOR,false);
            super.addCard(card,generateId());
        }
    }
    
    private void addSectorCardWithNoiseMySector(int wItem, int wNoItem) {
        Card card;
        int index;
        for(index = 0; index < wItem; index++) {
            //create a SectorCard with noise in my sector and with an item to draw
            card = new SectorCard(ConstantsCard.NOISE_MY_SECTOR,true);
            super.addCard(card,generateId());
        }
        for(index = 0; index < wNoItem; index++) {
            //create a SectorCard with noise in my sector and without an item to draw
            card = new SectorCard(ConstantsCard.NOISE_MY_SECTOR,false);
            super.addCard(card,generateId());
        }
    }
    
    private void addSectorCardWithNoNoise(int wItem, int wNoItem) {
        Card card;
        int index;
        for(index = 0; index < wItem; index++) {
            //create a SectorCard without noise and with an item to draw
            card = new SectorCard(ConstantsCard.SILENCE,true);
            super.addCard(card,generateId());
        }
        for(index = 0; index < wNoItem; index++) {
            //create a SectorCard without noise and without an item to draw
            card = new SectorCard(ConstantsCard.SILENCE,false);
            super.addCard(card,generateId());
        }
    }

}

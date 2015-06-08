/**
 * 
 */
package it.polimi.ingsw.bogliobresich.controller;

import it.polimi.ingsw.bogliobresich.model.Characters;
import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.DeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.MyDeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author matteobresich
 *
 */
public class Match {
    public static void main (String args[]) {
        DeckFactory factory = new MyDeckFactory();
        Deck characterDeck = factory.createCharacterDeck();

        characterDeck.showCards();

        System.out.println("MESCOLO:");
        characterDeck.shuffle();
        characterDeck.showCards();
        System.out.println("PESCO:");
        List <Card> cards = new ArrayList();
        for (int i = 0; i < 8; i++)
        {
            try{
                Card c = characterDeck.drawCard();
                cards.add(c);
                System.out.println("Pesco " + c);
            }
            catch (CardFinishedException e) { System.out.println("CARTA: " + i + " NON ESISTENTE!");}
        }  
        characterDeck.showCards();
        
        System.out.println("SCARTO:");
        for (int i = 0; i < 8; i++)
        {
            Card c = cards.get(i);
            characterDeck.discardCard(c);
            System.out.println("Scarto " + c);
            
        }
        characterDeck.showCards();
        
        factory = new MyDeckFactory();
        Deck itemDeck = factory.createItemDeck();
        try {
        Card c = itemDeck.drawCard();
        c.play();
        }catch(Exception e) {
            
        }
        
                
    }
    

}

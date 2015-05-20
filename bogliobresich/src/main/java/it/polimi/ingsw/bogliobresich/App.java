package it.polimi.ingsw.bogliobresich;

import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.DeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.MyDeckFactory;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	DeckFactory factory = new MyDeckFactory();
		Deck characterDeck = factory.createCharacterDeck();
		Deck itemDeck = factory.createItemDeck();
		
		characterDeck.showCards();
			
		//itemDeck.shuffle();
		characterDeck.shuffle();
		
		characterDeck.showCards();
    }
}

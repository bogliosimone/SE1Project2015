package it.polimi.ingsw.bogliobresich.model.player;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.bogliobresich.model.cards.AdrenalineItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.DefenceItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;

import org.junit.Test;

public class ItemHandTest {

    @Test
    public void test() {
        int numberCards=2;
        ItemCard c1=new AdrenalineItemCard(1);
        ItemCard c2=new AdrenalineItemCard(2);
        List<ItemCard> listC= new ArrayList<ItemCard>();
        listC.add(c1);
        listC.add(c2);
        ItemHand h=new ItemHand(numberCards);
        h.addCard(c1);
        h.addCard(c2);
        assertTrue(h.isFull());
        h.discardHand();
        assertTrue(h.isEmpty());
        h.addCard(c1);
        h.removeCard(c1);
        assertTrue(h.isEmpty());
        h.addCard(c1);
        assertTrue(h.cardIsIn(c1));
        assertEquals(h.getCard(c1.getId()),c1);
        h.removeCard(c1.getId());
        assertTrue(h.isEmpty());
        h.addCard(c1);
        h.addCard(c2);
        List<ItemCard> listC2=h.getAllCard();
        List<ItemCard> listC3=h.listOfCards();
        assertEquals(listC,listC3);
        assertEquals(listC,listC2);
        assertEquals(h.getDefenceCard(),null);
        assertEquals(h.toString(),"ItemHand [cards=" + listC2 + "]");
        
    }
    
    
    public void test2() {
        int numberCards=2;
        ItemHand h=new ItemHand(numberCards);
        ItemCard c1=new AdrenalineItemCard(1);
        ItemCard c2=new AdrenalineItemCard(2);
        ItemCard c3=new DefenceItemCard(3);
        h.discardHand();
        assertTrue(h.isEmpty());
        assertFalse(h.cardIsIn(c1));
        h.addCard(c1);
        assertTrue(h.addCard(c3));
        assertFalse(h.addCard(c2));
        assertFalse(h.removeCard(c2));
        assertFalse(h.removeCard(c2.getId()));
        assertFalse(h.cardIsIn(c2));
        assertEquals(h.getCard(c2.getId()),null);
        assertEquals(h.getDefenceCard(),c3);
        
        
    }
}

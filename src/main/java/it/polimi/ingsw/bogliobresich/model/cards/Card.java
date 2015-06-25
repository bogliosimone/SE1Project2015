/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

/**
 * This class provides a skeletal implementation of a card.
 * 
 * @author matteo bresich
 * @author simone boglio
 *  
 * @version 1.0
 * 
 */
public interface Card {
    
    /**
     * @param index the id card to set
     */
    public abstract void setId(int index);
    
    /**
     * @return the id of the card
     */
    public abstract int getId();
}
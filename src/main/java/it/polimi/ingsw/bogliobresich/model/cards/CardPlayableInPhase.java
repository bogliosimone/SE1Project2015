/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

/**
 * This class provides a skeletal implementation of when a card could be played.
 * 
 * @author matteo bresich
 * @author simone boglio
 *  
 * @version 1.0
 * 
 */
public interface CardPlayableInPhase {
    /**
     * @return true if the card could be played in the init phase
     */
    public boolean isPlayableInitPhase();
    /**
     * @return true if the card could be played in the movement phase
     */
    public boolean isPlayableMovePhase();
    /**
     * @return true if the card could be played in the end phase
     */
    public boolean isPlayableEndPhase();
}

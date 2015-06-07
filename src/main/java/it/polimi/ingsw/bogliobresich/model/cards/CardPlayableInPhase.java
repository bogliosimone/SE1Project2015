/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

/**
 * @author matteobresich
 *
 */
public interface CardPlayableInPhase {
    public boolean isPlayableInitPhase();
    public boolean isPlayableMovePhase();
    public boolean isPlayableEndPhase();
}

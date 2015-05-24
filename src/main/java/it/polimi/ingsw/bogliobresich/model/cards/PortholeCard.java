/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

/**
 * @author Matteo
 *
 */
public class PortholeCard extends Card {
    final private String state;

    /**
     * Class constructor
     */
    public PortholeCard(String type) {
        this.state = type;
    }
    
    /**
     * Return the state of the porthole
     * @return true if the porthole is working
     */
    public boolean isPortholeStateWorking() {
        return state.equals(ConstantsCard.PORTHOLE_WORKS);
    }

    @Override
    public String toString() {
        return "PortholeCard [" + state +"]";
    }
    

}

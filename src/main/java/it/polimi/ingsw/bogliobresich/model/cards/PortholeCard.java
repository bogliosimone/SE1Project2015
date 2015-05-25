/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

/**
 * @author Matteo
 *
 */
public class PortholeCard extends Card {
    private String state;

    /**
     * Class constructor
     */
    public PortholeCard(String state) {
        if(isDataOk(state)) {
            this.state = state;
        }
    }
    
    /**
     * Return the state of the porthole
     * @return true if the porthole is working
     */
    public boolean isPortholeStateWorking() {
        return state.equals(ConstantsCard.PORTHOLE_WORKS);
    }
    
    /**
     * Return if the data to be entered is correct
     * @return true if the data to be entered are ok
     */
    private boolean isDataOk(String state) {
        if(state.equals(ConstantsCard.PORTHOLE_BROKEN) || state.equals(ConstantsCard.PORTHOLE_WORKS)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "PortholeCard [" + state +"]";
    }
    

}

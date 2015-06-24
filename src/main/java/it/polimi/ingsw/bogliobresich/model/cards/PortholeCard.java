/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author Matteo
 *
 */
public class PortholeCard implements Card {
    private int id;
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

    
    /**
     * @return the id
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    
    @Override
    public String toString() {
        return "PortholeCard [" + state +"]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PortholeCard other = (PortholeCard) obj;
        if (id != other.id)
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        return true;
    }
    

}

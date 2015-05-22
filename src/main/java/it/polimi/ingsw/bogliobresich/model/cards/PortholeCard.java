/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

/**
 * @author Matteo
 *
 */
public class PortholeCard extends Card {
    final private String type;

    /**
     * Class constructor
     */
    public PortholeCard(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PortholeCard [" + type +"]";
    }
    

}

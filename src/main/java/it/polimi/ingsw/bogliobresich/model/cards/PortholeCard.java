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
    
    public boolean isPortholeWorking() {
        return type;//TODO
    }

    @Override
    public String toString() {
        return "PortholeCard [" + type +"]";
    }
    

}

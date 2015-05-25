/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

/**
 * @author Matteo
 *
 */
public class ItemCard extends Card {
    
    private String type;

    /**
     * Class constructor
     */
    public ItemCard(String type) {
        if(isDataOk(type)) {
            this.type = type;
        }
    }
    

    /**
     * Return if the data to be entered is correct
     * @return true if the data to be entered are ok
     */
    private boolean isDataOk(String type) {
        if(type.equals(ConstantsCard.ATTACK)
                || type.equals(ConstantsCard.DEFENCE)
                || type.equals(ConstantsCard.TELEPORT)
                || type.equals(ConstantsCard.SPOTLIGHT)
                || type.equals(ConstantsCard.SEDATIVES)
                || type.equals(ConstantsCard.ADRENALINE)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ItemCard [" + type +"]";
    }
    
}

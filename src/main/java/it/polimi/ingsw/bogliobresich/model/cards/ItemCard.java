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
        if(type.equals(ConstantsCard.ATTACK)) {
            return true;
        }
        if(type.equals(ConstantsCard.DEFENCE)) {
            return true;
        }
        if(type.equals(ConstantsCard.TELEPORT)) {
            return true;
        }
        if(type.equals(ConstantsCard.SPOTLIGHT)) {
            return true;
        }
        if(type.equals(ConstantsCard.SEDATIVES)) {
            return true;
        }
        if(type.equals(ConstantsCard.ADRENALINE)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ItemCard [" + type +"]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        ItemCard other = (ItemCard) obj;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }
    
}

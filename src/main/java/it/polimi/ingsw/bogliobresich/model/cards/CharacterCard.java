/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import it.polimi.ingsw.bogliobresich.model.Characters;

/**
 * @author Matteo
 *
 */
public class CharacterCard extends Card {
    private String name;
    

    /**
     * Class constructor
     * @param character
     */
    public CharacterCard(Characters character) {
        this.name = character.getCharacterName();
    }

    @Override
    public String toString() {
        return "CharacterCard [name=" + name + " + hashCode=" + hashCode() +"]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        CharacterCard other = (CharacterCard) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    
}

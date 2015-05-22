/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

/**
 * @author Matteo
 *
 */
public class CharacterCard extends Card {
    String name;

    /**
     * Class constructor
     * @param character
     */
    public CharacterCard(Character character) {
        this.name = character.getCharacterName();
    }

    @Override
    public String toString() {
        return "CharacterCard [name=" + name + "]";
    }
}

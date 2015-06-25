/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import java.io.Serializable;

import it.polimi.ingsw.bogliobresich.model.Characters;

/**
 * The <code>CharacterCard</code> class implements abstract class Card.<br>
 * 
 * @author matteo bresich
 * @author simone boglio
 *  
 * @version 1.0
 * @see it.polimi.ingsw.bogliobresich.model.cards.Card
 * 
 */
public class CharacterCard implements Card,Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -7421912153562352462L;
    private int id;
    private String name;
    private String type;

    /**
     * Constructs a CharacterCard with the id of the card
     * @param character the character of the card
     * 
     */
    public CharacterCard(Characters character) {
        this.name = character.getCharacterName();
        this.type = character.getCharacterType();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return id;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return the type of the character
     */
    public String getCharacterType(){
        return this.type;
    }
    
    /**
     * @return name the character name
     */
    public String getCharacterName(){
        return this.name;
    }
    
    
    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    
}

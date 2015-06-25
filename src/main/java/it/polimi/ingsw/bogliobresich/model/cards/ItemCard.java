/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import java.io.Serializable;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * This class provides a skeletal implementation of an item card.
 * 
 * @author matteo bresich
 * @author simone boglio
 *  
 * @version 1.0
 * 
 */
public abstract class ItemCard implements Card, CardPlayableInPhase, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 5336699153651039547L;
    private int id;
    protected boolean isPlayed;
    
    public abstract ItemCard play(Match m, Player p);
    
    /**
     * @return the id of the card
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * @param id the item card id to set
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return if the item card is played
     */
    public boolean isPlayed() {
        return this.isPlayed;
    }
    
    
    /**
     * @return the name of the item card
     */
    public String getName(){
        return "Generics Item Card";
    }
    
    
    /**
     * @return the info about the item card
     */
    public String getInfo(){
        return "";
    }
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        if (id != other.id)
            return false;
        return true;
    }
}

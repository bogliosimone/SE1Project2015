/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author Matteo
 *
 */
public class SectorCard implements Card {
    private int id;
    private boolean drawAnItem;
    private String noise;
    

    /**
     * Class constructor
     * @param d indicates if you have to draw an item card  
     */
    public SectorCard(String noise, boolean d) {
        if(isDataOk(noise)) {
            this.drawAnItem = d;
            this.noise = noise;
        }
    }
    
    @Override
    public Card play(Player p) {
        // TODO Auto-generated method stub
        return this;
    }
    
    /**
     * Return if there is noise
     * @return true if there is noise
     */
    public boolean isThereNoise() {
        //If there isn't any type of noise in the sector card, the sector card is silence  
        return !this.noise.equals(ConstantsCard.SILENCE);
    }
    
    /**
     * Return if the data to be entered is correct
     * @return true if the data to be entered are ok
     */
    private boolean isDataOk(String noise) {
        if(noise.equals(ConstantsCard.SILENCE)||noise.equals(ConstantsCard.NOISE_MY_SECTOR)||noise.equals(ConstantsCard.NOISE_ANY_SECTOR)) {
            return true;
        }
        return false;
    }
    
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    

    @Override
    public String toString() {
        return "SectorCard [drawAnItem =" + drawAnItem + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (drawAnItem ? 1231 : 1237);
        result = prime * result + id;
        result = prime * result + ((noise == null) ? 0 : noise.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SectorCard other = (SectorCard) obj;
        if (drawAnItem != other.drawAnItem)
            return false;
        if (id != other.id)
            return false;
        if (noise == null) {
            if (other.noise != null)
                return false;
        } else if (!noise.equals(other.noise))
            return false;
        return true;
    }

    
    
}

/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

/**
 * @author Matteo
 *
 */
public class SectorCard extends Card {
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

    @Override
    public String toString() {
        return "SectorCard [drawAnItem =" + drawAnItem + "]";
    }
    
}

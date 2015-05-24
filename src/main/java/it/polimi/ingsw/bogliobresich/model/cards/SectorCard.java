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
        if(true) { //TODO controllo immissione
            this.drawAnItem = d;
            this.noise = noise;
        }
    }

    @Override
    public String toString() {
        return "SectorCard [drawAnItem =" + drawAnItem + "]";
    }
    
    

}

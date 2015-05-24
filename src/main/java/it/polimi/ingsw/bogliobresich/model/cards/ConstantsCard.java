/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

/**
 * @author matteobresich
 *
 */
public final class ConstantsCard {
    public static final String ALIEN = "alien";
    public static final String HUMAN = "human"; 
    
    //PORTHOLE CARD
    public static final String PORTHOLE_WORKS = "works";
    public static final String PORTHOLE_BROKEN = "broken";
    
    //SECTOR CARD
    public static final boolean DRAW_AN_ITEM = true;
    public static final boolean DO_NOT_DRAW_AN_ITEM = false;
    public static final String NOISE_MY_SECTOR = "noise here";
    public static final String NOISE_ANY_SECTOR = "noise xy";
    public static final String SILENCE = "silence";
    
    
    private ConstantsCard() {
        //not called
    }
}

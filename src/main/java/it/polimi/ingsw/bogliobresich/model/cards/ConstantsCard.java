/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

/**
 * @author matteobresich
 *
 */
public final class ConstantsCard {
    public static final String ALIEN = "Alien";
    public static final String HUMAN = "Human"; 
    
    //PORTHOLE CARD
    public static final String PORTHOLE_WORKS = "works";
    public static final String PORTHOLE_BROKEN = "broken";
    
    //ITEM CARD
    public static final String ATTACK = "Attack";
    public static final String DEFENCE = "Defence";
    public static final String TELEPORT= "Teleport";
    public static final String SPOTLIGHT = "Spotlight";
    public static final String SEDATIVES = "Sedatives";
    public static final String ADRENALINE = "Adrenaline";
    public static final int ADRENALINESTEP = 2;
    
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

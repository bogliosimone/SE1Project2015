/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck;

import it.polimi.ingsw.bogliobresich.model.Characters;

/**
 * This class contains all constants decks used in the application.
 * 
 * @author matteo bresich
 * @author simone boglio
 *
 */
public final class ConstantsDeck {
    //Character contains all characters of the game
    public static final int NCHARACTERCARD = Characters.values().length;
    public static final int NPORTHOLECARD = 6;
    
    //ITEM CARD
    public static final int N_ITEMCARD_ATTACK = 2;
    public static final int N_ITEMCARD_DEFENCE = 1;
    public static final int N_ITEMCARD_TELEPORT = 2;
    public static final int N_ITEMCARD_SPOTLIGHT = 2;
    public static final int N_ITEMCARD_SEDATIVES = 3;
    public static final int N_ITEMCARD_ADRENALINE = 2;
    public static final int NITEMCARD = 
            N_ITEMCARD_ATTACK + 
            N_ITEMCARD_DEFENCE +
            N_ITEMCARD_TELEPORT +
            N_ITEMCARD_SPOTLIGHT +
            N_ITEMCARD_SEDATIVES +
            N_ITEMCARD_ADRENALINE;
    
    //SECTOR CARD
    public static final int N_SECTORCARD_NOISE_MY_W_ITEM = 4;
    public static final int N_SECTORCARD_NOISE_MY_NO_ITEM = 6;
    public static final int N_SECTORCARD_NOISE_ANY_W_ITEM = 4;
    public static final int N_SECTORCARD_NOISE_ANY_NO_ITEM = 6;
    public static final int N_SECTORCARD_SILENCE_W_ITEM = 0;
    public static final int N_SECTORCARD_SILENCE_NO_ITEM = 5;
    
    public static final int N_SECTORCARD =
            N_SECTORCARD_NOISE_MY_W_ITEM + 
            N_SECTORCARD_NOISE_MY_NO_ITEM + 
            N_SECTORCARD_NOISE_ANY_W_ITEM + 
            N_SECTORCARD_NOISE_ANY_NO_ITEM +
            N_SECTORCARD_SILENCE_W_ITEM +
            N_SECTORCARD_SILENCE_NO_ITEM
            ;
    
    
    private ConstantsDeck() {
        //not called
    }
    
}

/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck;

import it.polimi.ingsw.bogliobresich.model.Character;

/**
 * @author matteobresich
 *
 */
public final class ConstantsDeck {
    //Character contains all characters of the game
    public static final int NCHARACTERCARD = Character.values().length;
    public static final int NPORTHOLECARD = 6;
    public static final int NITEMCARD = 12;
    
    
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

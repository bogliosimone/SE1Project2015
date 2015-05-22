/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck;

import it.polimi.ingsw.bogliobresich.model.cards.enums.Character;

/**
 * @author matteobresich
 *
 */
public final class ConstantsDeck {
    //Character contains all characters of the game
    public static final int NCHARACTERCARD = Character.values().length;
    public static final int NPORTHOLECARD = 6;
    public static final int NSECTORCARD = 25;
    public static final int NITEMCARD = 12;
    
    private ConstantsDeck() {
        //not called
    }
    
}

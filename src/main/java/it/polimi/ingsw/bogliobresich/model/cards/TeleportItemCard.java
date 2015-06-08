/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author matteobresich
 *
 */
public class TeleportItemCard extends ItemCard {

    private boolean isPlayableInit = true;
    private boolean isPlayableMove = true;
    private boolean isPlayableEnd = true;
    
    public TeleportItemCard(int id) {
        super.setId(id);
    }

    @Override
    public TeleportItemCard play(Player p) {
        // TODO Auto-generated method stub
        return this;
    }

    @Override
    public boolean isPlayableInitPhase() {
        return isPlayableInit;
    }

    @Override
    public boolean isPlayableMovePhase() {
        return isPlayableMove;
    }

    @Override
    public boolean isPlayableEndPhase() {
        return isPlayableEnd;
    }
    
}

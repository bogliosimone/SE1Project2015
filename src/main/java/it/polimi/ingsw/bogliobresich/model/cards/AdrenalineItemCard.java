/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author matteobresich
 *
 */
public class AdrenalineItemCard extends ItemCard {
    
    private boolean isPlayableInit = true;
    private boolean isPlayableMove = false;
    private boolean isPlayableEnd = false;

    public AdrenalineItemCard(int id) {
        super.setId(id);
    }
    
    @Override
    public AdrenalineItemCard play(Player p) {
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

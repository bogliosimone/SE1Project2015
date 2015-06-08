/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author matteobresich
 *
 */
public class DefenceItemCard extends ItemCard {
    
    private boolean isPlayableInit = false;
    private boolean isPlayableMove = false;
    private boolean isPlayableEnd = false;
    
    public DefenceItemCard(int id) {
        super.setId(id);
    }

    @Override
    public DefenceItemCard play(Player p) {
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

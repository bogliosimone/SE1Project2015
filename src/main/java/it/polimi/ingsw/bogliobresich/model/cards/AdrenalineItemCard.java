/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.player.HumanPlayer;
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
    public AdrenalineItemCard play(Match m, Player p) {
        if(p instanceof HumanPlayer){
            ((HumanPlayer) p).setMovementStep(ConstantsCard.ADRENALINESTEP);
            this.isPlayed=true;
        }
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
    
    @Override
    public  String toString(){
        return new String("Adrenalin Card id: "+this.getId());
    }

}

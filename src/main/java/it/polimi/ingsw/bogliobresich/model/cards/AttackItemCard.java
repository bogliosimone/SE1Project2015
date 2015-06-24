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
public class AttackItemCard extends ItemCard {

    private static final long serialVersionUID = -4525591736748400938L;
    private boolean isPlayableInit = true;
    private boolean isPlayableMove = true;
    private boolean isPlayableEnd = false;
    
    public AttackItemCard(int id) {
        super.setId(id);
    }
    
    @Override
    public AttackItemCard play(Match m, Player p) {
        if(p instanceof HumanPlayer){
            ((HumanPlayer) p).setCanAttack(true);
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
    public String getName(){
        return "Attacco";
    }
    
    @Override
    public  String toString(){
        return new String("Attack Card id: "+this.getId());
    }
    
}

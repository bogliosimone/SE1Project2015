/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import it.polimi.ingsw.bogliobresich.model.match.Match;
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
    public DefenceItemCard play(Match m, Player p) {
        p.SetIsAlive(true);
        this.isPlayed=true;
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
        return "Defence";
    }

    @Override
    public  String toString(){
        return new String("Defence Card id: "+this.getId());
    }
}

/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.player.HumanPlayer;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * The <code>AdrenalineItemCard</code> class implements abstract class ItemCard.<br>
 * 
 * @author matteo bresich
 * @author simone boglio
 *  
 * @version 1.0
 * 
 */
public class AdrenalineItemCard extends ItemCard {
    
    private static final long serialVersionUID = -4541074207490980654L;
    private boolean isPlayableInit = true;
    private boolean isPlayableMove = false;
    private boolean isPlayableEnd = false;

    public AdrenalineItemCard(int id) {
        super.setId(id);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public AdrenalineItemCard play(Match m, Player p) {
        if(p instanceof HumanPlayer){
            ((HumanPlayer) p).setMovementStep(ConstantsCard.ADRENALINESTEP);
            this.isPlayed=true;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayableInitPhase() {
        return isPlayableInit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayableMovePhase() {
        return isPlayableMove;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayableEndPhase() {
        return isPlayableEnd;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName(){
        return "Adrenaline";
    }
    
    @Override
    public  String toString(){
        return new String("Adrenalin Card id: "+this.getId());
    }

}

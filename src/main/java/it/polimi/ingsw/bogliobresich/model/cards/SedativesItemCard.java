/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.player.HumanPlayer;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * The <code>SedativesItemCard</code> class implements abstract class ItemCard.<br>
 * 
 * @author matteo bresich
 * @author simone boglio
 *  
 * @version 1.0
 * @see it.polimi.ingsw.bogliobresich.model.cards.ItemCard
 * 
 */
public class SedativesItemCard extends ItemCard {

    private static final long serialVersionUID = 1294759215955567323L;
    private boolean isPlayableInit = true;
    private boolean isPlayableMove = true;
    private boolean isPlayableEnd = false;
    
    /**
     * Constructs an SedativesItemCard with the id of the card
     * @param id the id of the sedatives card
     * 
     */
    public SedativesItemCard(int id) {
        super.setId(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SedativesItemCard play(Match m, Player p) {
        if(p instanceof HumanPlayer){
            ((HumanPlayer) p).setCanDrawSectorCard(false);
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
        return "Sedatives";
    }
    
    @Override
    public  String toString(){
        return new String("Sedatives Card id: "+this.getId());
    }

}

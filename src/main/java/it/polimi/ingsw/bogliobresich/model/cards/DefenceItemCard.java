/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * The <code>DefenceItemCard</code> class implements abstract class ItemCard.<br>
 * 
 * @author matteo bresich
 * @author simone boglio
 *  
 * @version 1.0
 * @see it.polimi.ingsw.bogliobresich.model.cards.ItemCard
 * 
 */
public class DefenceItemCard extends ItemCard {
    
    private static final long serialVersionUID = 1898169686290037142L;
    private boolean isPlayableInit = false;
    private boolean isPlayableMove = false;
    private boolean isPlayableEnd = false;
    
    /**
     * Constructs an DefenceItemCard with the id of the card
     * @param id the id of the defence card
     * 
     */
    public DefenceItemCard(int id) {
        super.setId(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefenceItemCard play(Match m, Player p) {
        p.SetIsAlive(true);
        this.isPlayed=true;
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
        return "Defence";
    }

    @Override
    public  String toString(){
        return new String("Defence Card id: "+this.getId());
    }
}

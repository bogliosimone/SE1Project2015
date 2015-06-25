/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import it.polimi.ingsw.bogliobresich.model.map.HexMap;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * The <code>TeleportItemCard</code> class implements abstract class ItemCard.<br>
 * 
 * @author matteo bresich
 * @author simone boglio
 *  
 * @version 1.0
 * @see it.polimi.ingsw.bogliobresich.model.cards.ItemCard
 * 
 */
public class TeleportItemCard extends ItemCard {

    private static final long serialVersionUID = 3392064214760107064L;
    private boolean isPlayableInit = true;
    private boolean isPlayableMove = false;
    private boolean isPlayableEnd = true;
    
    /**
     * Constructs an TeleportItemCard with the id of the card
     * @param id the id of the teleport card
     * 
     */
    public TeleportItemCard(int id) {
        super.setId(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TeleportItemCard play(Match m, Player p) {
        HexMap gameMap = m.getGameMap();
        p.setCoordinate(gameMap.getCoordinateHumanBase());
        m.notifyPlayer(Commands.SET_YOUR_COORDINATE, gameMap.getCoordinateHumanBase(), p);
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
        return "Teleport";
    }
    
    @Override
    public  String toString(){
        return new String("Teleport Card id: "+this.getId());
    }
}

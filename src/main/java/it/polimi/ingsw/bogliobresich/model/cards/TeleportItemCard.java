/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import it.polimi.ingsw.bogliobresich.model.map.HexMap;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author matteobresich
 *
 */
public class TeleportItemCard extends ItemCard {

    private boolean isPlayableInit = true;
    private boolean isPlayableMove = false;
    private boolean isPlayableEnd = true;
    
    public TeleportItemCard(int id) {
        super.setId(id);
    }

    @Override
    public TeleportItemCard play(Match m, Player p) {
        HexMap gameMap = m.getGameMap();
        p.setCoordinate(gameMap.getCoordinateHumanBase());
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
    public  String toString(){
        return new String("Teleport Card id: "+this.getId());
    }
}

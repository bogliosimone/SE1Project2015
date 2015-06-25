/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;

import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

import java.io.Serializable;
import java.util.Set;

/**
 * list of all move available  in a specific phase of turn, this class is used for send all commands available to user
 * @author simoneboglio
 *
 */
public class MovesAvaiable implements Serializable{

    /**
     * serial id
     */
    private static final long serialVersionUID = 2100671246376168578L;
    /**
     * true if player can play item
     */
    private boolean canPlayItem =false;
    /**
     * true if player can move
     */
    private boolean canMove=false;
    /**
     * true if player can attack
     */
    private boolean canAttack=false;
    /**
     * true if player can draw sector card
     */
    private boolean canDrawSectorCard=false;
    /**
     * true if player can go in end phase
     */
    private boolean canGoInEndPhase=false;
    /**
     * true if player can end turn
     */
    private boolean canEndTurn=false;
    /**
     * true if player can discard item
     */
    private boolean canDiscardItemCard=false;
    /**
     * true if player can call rumor
     */
    private boolean canCallRumor=false;
    
    /**
     * list of all reachable coordinate if player can moove
     */
    private Set<Coordinate> reachableCoordinate=null;
    
    /**
     * default all moves are set to false
     */
    public MovesAvaiable(){
        
    }

    /**
     * if player can play item
     * @return true if the player can play item
     */
    public boolean canPlayItem() {
        return canPlayItem;
    }

    /**
     * set if can play item card
     * @param canPlayItem true if can play item card
     */
    public void setCanPlayItem(boolean canPlayItem) {
        this.canPlayItem = canPlayItem;
    }

    /**
     * if player can move
     * @return true if the player can move
     */
    public boolean canMove() {
        return canMove;
    }

    /**
     * set if can move
     * @param canMove true if can move
     */
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    /**
     * if player can attack
     * @return true if the player can attack
     */
    public boolean canAttack() {
        return canAttack;
    }

    /**
     * set if can attack
     * @param canAttack true if can attack
     */
    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    /**
     * if player can draw sector card
     * @return true if the player can move
     */
    public boolean canDrawSectorCard() {
        return canDrawSectorCard;
    }

    /**
     * set if can draw sector card
     * @param canDrawSectorCard true if can draw sector card
     */
    public void setCanDrawSectorCard(boolean canDrawSectorCard) {
        this.canDrawSectorCard = canDrawSectorCard;
    }

    /**
     * if player can go in end phase
     * @return true if the player can go in end phase
     */
    public boolean canGoInEndPhase() {
        return canGoInEndPhase;
    }

    /**
     * set if can go in end phase
     * @param canGoInEndPhase true if can go in end phase
     */
    public void setCanGoInEndPhase(boolean canGoInEndPhase) {
        this.canGoInEndPhase = canGoInEndPhase;
    }

    /**
     * if player can end turn
     * @return true if the player can end turn
     */
    public boolean canEndTurn() {
        return canEndTurn;
    }

    /**
     * if player can end turn
     * @param canEndTurn if can end turn
     */
    public void setCanEndTurn(boolean canEndTurn) {
        this.canEndTurn = canEndTurn;
    }

    /**
     * if player can discard item card
     * @return true if the player can discard item card
     */
    public boolean canDiscardItemCard() {
        return canDiscardItemCard;
    }

    /**
     * if player can discard card
     * @param canDiscardItemCard true if can discard item card
     */
    public void setCanDiscardItemCard(boolean canDiscardItemCard) {
        this.canDiscardItemCard = canDiscardItemCard;
    }

    /**
     * if player can call rumor
     * @return true if the player can call rumor
     */
    public boolean canCallRumor() {
        return canCallRumor;
    }

    /**
     * set true if can call rumor
     * @param canCallRumor true if can call rumor
     */
    public void setCanCallRumor(boolean canCallRumor) {
        this.canCallRumor = canCallRumor;
    }

    /**
     * list of all reachable coordinate
     * @return list coordinate reachable
     */
    public Set<Coordinate> getReachableCoordinate() {
        return reachableCoordinate;
    }

    /**
     * set list reachable coordinate
     * @param reachableCoordinate list of coordinate
     */
    public void setReachableCoordinate(Set<Coordinate> reachableCoordinate) {
        this.reachableCoordinate = reachableCoordinate;
    }

}

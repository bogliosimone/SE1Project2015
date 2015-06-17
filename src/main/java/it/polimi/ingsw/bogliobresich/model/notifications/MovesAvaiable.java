/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;

import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

import java.io.Serializable;
import java.util.Set;

/**
 * @author simoneboglio
 *
 */
public class MovesAvaiable implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 2100671246376168578L;
    private boolean canPlayItem =false;
    private boolean canMove=false;
    private boolean canAttack=false;
    private boolean canDrawSectorCard=false;
    private boolean canGoInEndPhase=false;
    private boolean canEndTurn=false;
    private boolean canDiscardItemCard=false;
    private boolean canCallRumor=false;
    private Set<Coordinate> reachableCoordinate=null;
    
    public MovesAvaiable(){
        
    }
    
    public MovesAvaiable(boolean canPlayItem, boolean canMove,
            boolean canAttack, boolean canDrawSectorCard,
            boolean canGoInEndPhase, boolean canEndTurn) {
        super();
        this.canPlayItem = canPlayItem;
        this.canMove = canMove;
        this.canAttack = canAttack;
        this.canDrawSectorCard = canDrawSectorCard;
        this.canGoInEndPhase = canGoInEndPhase;
        this.canEndTurn = canEndTurn;
    }

    public boolean canPlayItem() {
        return canPlayItem;
    }

    public void setCanPlayItem(boolean canPlayItem) {
        this.canPlayItem = canPlayItem;
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean canAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public boolean canDrawSectorCard() {
        return canDrawSectorCard;
    }

    public void setCanDrawSectorCard(boolean canDrawSectorCard) {
        this.canDrawSectorCard = canDrawSectorCard;
    }

    public boolean canGoInEndPhase() {
        return canGoInEndPhase;
    }

    public void setCanGoInEndPhase(boolean canGoInEndPhase) {
        this.canGoInEndPhase = canGoInEndPhase;
    }

    public boolean canEndTurn() {
        return canEndTurn;
    }

    public void setCanEndTurn(boolean canEndTurn) {
        this.canEndTurn = canEndTurn;
    }

    public boolean canDiscardItemCard() {
        return canDiscardItemCard;
    }

    public void setCanDiscardItemCard(boolean canDiscardItemCard) {
        this.canDiscardItemCard = canDiscardItemCard;
    }

    public boolean canCallRumor() {
        return canCallRumor;
    }

    public void setCanCallRumor(boolean canCallRumor) {
        this.canCallRumor = canCallRumor;
    }

    public Set<Coordinate> getReachableCoordinate() {
        return reachableCoordinate;
    }

    public void setReachableCoordinate(Set<Coordinate> reachableCoordinate) {
        this.reachableCoordinate = reachableCoordinate;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}

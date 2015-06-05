/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */

public class Match {
    private State myState;
    private int idMatch;
    private boolean isActive=true;
    private int currentTurn=0;
    private List<Player> players = new ArrayList<Player>();

    public Match(List<User> users){
        setState(new InitState());
    }

    public int getIdMatch(){
        return this.idMatch;
    }
    
    public int getCurrentTurn(){
        return this.currentTurn;
    }
    
    public void setCurrentTurn(int turnNumber){
        this.currentTurn=turnNumber;
    }
    
    public boolean isActive(){
        return this.isActive;
    }

    void setState(State newState){
        this.myState = newState;
    }   
    public void doAction(Player player, Action action){
        myState.doAction(this,player,action);
    }
    
    public void notifyAllPlayer(String notification){
        System.out.println("Broadcast message: "+notification);
    }
    
    public void notifyPlayer(int idPlayer, String notification){
        System.out.println("Player "+idPlayer+": "+notification);
    }
    
    public void serviceMessage(String message ){
        System.out.println("Service Message: "+message);
    }
}

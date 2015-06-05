/**
 * 
 */
package it.polimi.ingsw.bogliobresich.match;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.bogliobresich.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */

public class Match {
    private State myState;
    private int id_match;
    private boolean isActive;
    private List<Player> players = new ArrayList<Player>();
    

    public Match(){
        setState(new InitState());
    }

    void setState(State newState){
        this.myState = newState;
    }   
    public void doAction(Player player, Action action){
        myState.doAction(this,player,action);
    }
}

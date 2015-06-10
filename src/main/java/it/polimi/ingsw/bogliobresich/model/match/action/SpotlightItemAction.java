/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.action;

import it.polimi.ingsw.bogliobresich.model.match.state.State;

/**
 * @author simoneboglio
 *
 */
public class SpotlightItemAction implements Action {
    State oldState;
    
    public SpotlightItemAction(State oldState){
        this.oldState=oldState;
    }
    
    public State getOldState(){
        return this.oldState;
    }
}

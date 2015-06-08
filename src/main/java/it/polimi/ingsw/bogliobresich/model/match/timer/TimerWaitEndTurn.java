/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.timer;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.TimerEndTurnAction;
import it.polimi.ingsw.bogliobresich.model.match.state.EndTurnState;

import java.util.TimerTask;

/**
 * @author simoneboglio
 *
 */
public class TimerWaitEndTurn extends TimerTask {
    private Match match;
    
    public TimerWaitEndTurn(Match match){
        this.match=match;
    }
    
    public void run() {
        this.match.setState(new EndTurnState());
        this.match.doAction(null, new TimerEndTurnAction());
    }
}

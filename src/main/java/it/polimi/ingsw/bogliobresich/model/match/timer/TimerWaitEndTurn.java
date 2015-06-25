/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.timer;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.TimerEndTurnAction;
import it.polimi.ingsw.bogliobresich.model.match.state.EndTurnState;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.util.TimerTask;

/**
 * @author simoneboglio
 *
 */
public class TimerWaitEndTurn extends TimerTask {
    private Match match;
    private Player dcPlayer;

    public TimerWaitEndTurn(Match match,Player currentPlayer){
        this.match=match;
        dcPlayer = currentPlayer;
    }

    @Override
    public void run() {
        if((match != null)&&(dcPlayer != null)) {
            this.match.setState(new EndTurnState());
            this.match.doAction(dcPlayer, new TimerEndTurnAction());
        }
    }
}

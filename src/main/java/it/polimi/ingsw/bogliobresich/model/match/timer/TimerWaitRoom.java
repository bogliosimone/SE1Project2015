/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.timer;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.TimerRoomEndAction;

import java.util.TimerTask;

/**
 * @author simoneboglio
 *
 */
public class TimerWaitRoom extends TimerTask {
    private Match match;
    
    public TimerWaitRoom(Match match){
        this.match=match;
    }
    
    public void run() {
        this.match.doAction(null, new TimerRoomEndAction());
    }
}

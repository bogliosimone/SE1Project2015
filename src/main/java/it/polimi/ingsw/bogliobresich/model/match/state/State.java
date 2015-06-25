/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * interface for all state of the match, match evolve with action, only some action can do in a specific moment
 * depend on the current state of match
 * used state patter 
 * @author simoneboglio
 *
 */
public interface State {

    void doAction(Match match,Player player, Action action);
    
}

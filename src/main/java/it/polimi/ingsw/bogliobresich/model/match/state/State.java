/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public interface State {

    void doAction(Match match,Player player, Action action);
    
}

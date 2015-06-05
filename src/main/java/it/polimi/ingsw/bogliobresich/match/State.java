/**
 * 
 */
package it.polimi.ingsw.bogliobresich.match;

import it.polimi.ingsw.bogliobresich.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public interface State {

    void doAction(Match match,Player player, Action action);
    
}

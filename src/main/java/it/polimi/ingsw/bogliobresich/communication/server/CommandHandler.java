/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.action.AttackAction;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author matteobresich
 *
 */
public class CommandHandler {
    
    MatchHandler matchHandler = null;
    public CommandHandler(MatchHandler matchHandler) {
        this.matchHandler = matchHandler;
    }
    
    public void executeCommand(Player player, GameProtocol command, Coordinate coordinate) {
        switch(command) {
        case DO_MOVE_REQUEST: 
            matchHandler.executeAction(player, new MovementAction(coordinate));
        break;
        case DO_ATTACK_REQUEST:
            matchHandler.executeAction(player, new AttackAction());
            break;
        case DO_DISCARD_ITEM_REQUEST:
            break;
        case DO_DRAW_SECTOR_REQUEST:
            break;
        case DO_END_MOVEMENT_PHASE_REQUEST:
            break;
        case DO_END_TURN_REQUEST:
            break;
        case DO_PLAY_ITEM_REQUEST:
            break;
        case DO_RUMOR_IN_COORDINATE_REQUEST:
            break;
        default:
            break;
        }
        
    }

}

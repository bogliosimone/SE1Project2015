/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.CommandType;
import it.polimi.ingsw.bogliobresich.model.match.action.AttackAction;
import it.polimi.ingsw.bogliobresich.model.match.action.DiscardAction;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawSectorAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndTurnAction;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.match.action.RumorCoordinate;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author matteobresich
 *
 */
public class CommandHandler {
    
    public static void executeClientCommand(MatchHandler matchHandler, Player player, ClientCommand command) {
        CommandType cmdType = command.getCommandType();
        switch(cmdType) {
        case DO_MOVE_REQUEST: 
            if(command.getCoordinate() != null) {
                matchHandler.executeAction(player, new MovementAction(command.getCoordinate()));
            }
        break;
        case DO_ATTACK_REQUEST:
            matchHandler.executeAction(player, new AttackAction());
            break;
        case DO_DISCARD_ITEM_REQUEST:
            if(command.getCard() != null) { 
                matchHandler.executeAction(player, new DiscardAction(command.getCard()));
            }
            break;
        case DO_DRAW_SECTOR_REQUEST:
            matchHandler.executeAction(player, new DrawSectorAction());
            break;
        case DO_END_MOVEMENT_PHASE_REQUEST:
            matchHandler.executeAction(player, new EndPhaseAction());
            break;
        case DO_END_TURN_REQUEST:
            matchHandler.executeAction(player, new EndTurnAction());
            break;
        case DO_PLAY_ITEM_REQUEST:
            if(command.getCard() != null) { 
                matchHandler.executeAction(player, new PlayItemAction(command.getCard()));
            }
            break;
        case DO_RUMOR_IN_COORDINATE_REQUEST:
            if(command.getCoordinate() != null) {
                matchHandler.executeAction(player, new RumorCoordinate(command.getCoordinate()));
            }
            break;
        default:
            break;
        }
        
    }
}

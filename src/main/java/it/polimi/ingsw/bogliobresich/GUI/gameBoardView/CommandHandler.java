/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;

import it.polimi.ingsw.bogliobresich.GUI.ImagesHolder;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;

import javax.swing.JDialog;

/**
 * @author matteobresich
 *
 */
public class CommandHandler {
    
    private static ImagesHolder imagesHolder = ImagesHolder.getInstance();

    private CommandHandler() {
        //Not Called
    }
    
    public static void dispatchUpdate(GameBoardView board, NotificationMessage notification) {
        switch (notification.getCommand()) {
        case ALL_PLAYERS_MESSAGE:
            board.printMessage(notification.getString());
            break;
        case ATTACK:
            board.printMessage(notification.getString());
            break;
        case CALL_RUMOR:
            //POPUP
            break;
        case CANT_DISCARD_CARD:
            break;
        case CANT_PLAY_CARD:
            break;
        case CARDS_END:
            break;
        case COORDINATE_ERROR:
            break;
        case DISCARD_CARD:
            break;
        case DISCARD_HAND:
            break;
        case DRAW_CARD:
            break;
        case DRAW_SECTOR_CARD:
            break;
        case END_TURN:
            break;
        case FATAL_ERROR:            
            break;
        case GAME_END:
            break;
        case GAME_INFO_MESSAGE:
            board.printMessage(notification.getString());
            break;
        case GAME_START:
            break;
        case GENERIC_ERROR:
            break;
        case GENERIC_MESSAGE:
            board.printMessage(notification.getString());
            break;
        case HAND_FULL:
            break;
        case IS_NOT_YOUR_TURN:
            board.printMessage(notification.getString());
            break;
        case ITEM_PLAYED:
            break;
        case LIST_USERS:
            break;
        case MOVES_AVAIABLE:
            break;
        case MOVE_NO_AVAIABLE:
            break;
        case PLAYER_COMMAND:
            break;
        case PLAYER_JOIN_WAIT_ROOM:
            break;
        case PLAYER_MESSAGE:
            board.printMessage(notification.getString());
            break;
        case PORTHOLE_BROKEN:
            break;
        case SECTOR_TYPE_MESSAGE:
            break;
        case SERVER_NOT_RESPONDING:
            break;
        case SET_YOUR_COORDINATE:
            board.getCommandPanel().printMyCoordinate(notification.getCoordinate());
            break;
        case START_END_PHASE:
            break;
        case START_MOVEMENT_PHASE:
            break;
        case START_TIMER:
            break;
        case START_TURN:
            board.getCommandPanel().printCurrentTurnNumber(notification.getInteger());
            break;
        case USER_END_IS_GAME:
            break;
        case USER_END_TURN:
            break;
        case USER_START_TURN:
            break;
        case WHO_ARE_YOU:
            break;
        case YOU_ARE_FEED:
            break;
        case YOU_DIE:
            break;
        case YOU_DISCONNECTED:
            break;
        case YOU_LOST:
            break;
        case YOU_WIN:
            break;
        default:
            break;
        }
    }
}
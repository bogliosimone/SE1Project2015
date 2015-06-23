/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;

import it.polimi.ingsw.bogliobresich.GUI.GUIController;
import it.polimi.ingsw.bogliobresich.GUI.ImagesHolder;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;

/**
 * @author matteobresich
 *
 */
public class CommandHandler {

    private static ImagesHolder imagesHolder = ImagesHolder.getInstance();

    private CommandHandler() {
        //Not Called
    }

    public static void dispatchUpdate(GameBoardView board, HexagonMapPanel map, NotificationMessage notification) {
        switch (notification.getCommand()) {
        case ALL_PLAYERS_MESSAGE:
            board.printMessage(notification.getString());
            break;
        case ATTACK:
            board.printMessage(notification.getString());
            break;
        case CALL_RUMOR:
            board.printMessage("Clicca sulla mappa per inviare le coordinate dove vuoi fare rumore");
            map.setStateMoveRumorSpotlight(map.STATE_RUMOR);
            map.setAvaiableAllMoves();
            break;
        case CANT_DISCARD_CARD:
            board.printMessage("Errore non puoi scartare questa carta");
            break;
        case CANT_PLAY_CARD:
            board.printMessage("Errore non puoi giocare questa carta");
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
            board.getCommandPanel().printHand(GUIController.getInstance().getHandOfCards());
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
            board.printMessage("Non puoi compiere questa azione: non è il tuo turno!");
            break;
        case ITEM_PLAYED:
            break;
        case LIST_USERS:
            break;
        case MOVES_AVAIABLE:
            if(notification.getMovesAvaiable().canMove()) {
                map.setStateMoveRumorSpotlight(map.STATE_MOVE);
                map.setAvaiableMoves(notification.getMovesAvaiable().getReachableCoordinate());
            }
            break;
        case MOVE_NO_AVAIABLE:
            board.printMessage("Non puoi compiere questa azione: azione non disponibile.");
            break;
        case PLAYER_COMMAND:
            break;
        case PLAYER_JOIN_WAIT_ROOM:
            break;
        case PLAYER_MESSAGE:
            board.printMessage(notification.getString());
            break;
        case PORTHOLE_BROKEN:
            map.setBreakPorthole(notification.getCoordinate());
            break;
        case SECTOR_TYPE_MESSAGE:
            break;
        case SERVER_NOT_RESPONDING:
            break;
        case SET_YOUR_COORDINATE:
            board.getCommandPanel().printMyCoordinate(notification.getCoordinate());
            map.setActualCoordinate(notification.getCoordinate());
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
        case USER_DISCONNECTED:
            //USER
            break;
        case HUMAN_ESCAPE:
            //PLAYER
            break;
        case PLAYER_DIE:
            //PLAYER
            break;
        case GAME_MAP_FILE_NAME:
            break;
        default:
            board.printMessage("Comando non supportato");
            break;
        }
    }
}

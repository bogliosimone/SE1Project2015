/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;

import it.polimi.ingsw.bogliobresich.GUI.GUIController;
import it.polimi.ingsw.bogliobresich.GUI.ImagesHolder;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;

import java.awt.Color;

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
        String s;
        switch (notification.getCommand()) {
        case LIST_PLAYERS_END_GAME:
            break;
        case ATTACK:
            board.printMessage(notification.getString());
            break;
        case CALL_RUMOR:
            board.getCommandPanel().printOtherMessage("Clicca sulla mappa per fare rumore");
            map.setStateMoveRumorSpotlight(map.STATE_RUMOR);
            map.setAvaiableAllMoves();
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
            board.getCommandPanel().printHand(GUIController.getInstance().getHandOfCards());
            break;
        case DISCARD_HAND:
            board.getCommandPanel().printHand(GUIController.getInstance().getHandOfCards());
            break;
        case DRAW_CARD:
            board.getCommandPanel().printHand(GUIController.getInstance().getHandOfCards());
            break;
        case DRAW_SECTOR_CARD:
            break;
        case END_TURN:
            board.getCommandPanel().printPhaseTurnMessage("Non è il tuo turno", Color.RED);
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
            break;
        case ITEM_PLAYED:
            board.printMessage(notification.getString());
            break;
        case LIST_USERS:
            break;
        case MOVES_AVAIABLE:
            s = "Mosse disponibili";
            if(notification.getMovesAvaiable().canMove()) {
                map.setStateMoveRumorSpotlight(map.STATE_MOVE);
                map.setAvaiableMoves(notification.getMovesAvaiable().getReachableCoordinate());
                s=s+" - MUOVI";
            }
            if(notification.getMovesAvaiable().canPlayItem()){
                if(!GUIController.getInstance().getHandOfCards().isEmpty()) {
                    board.getCommandPanel().setBtnPlayTheCardEnabled(true);
                    board.getCommandPanel().setCardsEnabled(true);
                    s=s+" - GIOCA OGGETTO";
                }
            }
            if(notification.getMovesAvaiable().canAttack()){
                board.getCommandPanel().setBtnAttackEnabled(true);
                s=s+" - ATTACCA";
            }
            if(notification.getMovesAvaiable().canDiscardItemCard()){
                board.getCommandPanel().setBtnDiscardTheCardEnabled(true);
                board.getCommandPanel().setCardsEnabled(true);
                s=s+" - SCARTA";
            }
            if(notification.getMovesAvaiable().canEndTurn()){
                board.getCommandPanel().setBtnEndTurnEnabled(true);
                s=s+" - FINE TURNO";
            }
            if(notification.getMovesAvaiable().canGoInEndPhase()){
                board.getCommandPanel().setBtnEndMovementEnabled(true);
                s=s+" - FINE MOVIMENTO";
            }
            if(notification.getMovesAvaiable().canDrawSectorCard()){
                board.getCommandPanel().setBtnDrawSectorCardEnabled(true);
                s=s+" - PESCA CARTA SETTORE";
            }
            board.getCommandPanel().printOtherMessage(s);
            break;
        case MOVE_NO_AVAIABLE:
            break;
        case PLAYER_JOIN_WAIT_ROOM:
            break;
        case PLAYER_MESSAGE:
            board.printMessage("da spostare.. "+notification.getString());
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
            board.getCommandPanel().printPhaseTurnMessage("Sei nella End Phase", Color.GREEN);
            break;
        case START_MOVEMENT_PHASE:
            board.getCommandPanel().printPhaseTurnMessage("Sei nella Movement Phaase", Color.GREEN);
            break;
        case START_TIMER:
            break;
        case START_TURN:
            board.getCommandPanel().printPhaseTurnMessage("Sei nella Start Phase", Color.GREEN);
            board.getCommandPanel().printCurrentTurnNumber(notification.getInteger());
            break;
        case USER_END_TURN:
            board.getCommandPanel().printUserList(GUIController.getInstance().getUserList(), GUIController.getInstance().getCommandPanelUserList());
            break;
        case USER_START_TURN:
            board.getCommandPanel().printUserList(GUIController.getInstance().getUserList(), GUIController.getInstance().getCommandPanelUserList());
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
            board.getCommandPanel().printUserList(GUIController.getInstance().getUserList(), GUIController.getInstance().getCommandPanelUserList());
            board.printMessage(notification.getUser().getNickname()+" si è disconnesso");
            break;
        case HUMAN_ESCAPE:
            board.getCommandPanel().printUserList(GUIController.getInstance().getUserList(), GUIController.getInstance().getCommandPanelUserList());
            board.printMessage(notification.getPlayer().getNickName()+" ha lasciato l'astronave \n"+notification.getPlayer().getNickName()+" personaggio: "+notification.getPlayer().getCharacterCard().getCharacterName()+" natura: "+notification.getPlayer().getCharacterCard().getCharacterType());
            break;
        case PLAYER_DIE:
            board.getCommandPanel().printUserList(GUIController.getInstance().getUserList(), GUIController.getInstance().getCommandPanelUserList());
            board.printMessage(notification.getPlayer().getNickName()+" è morto \n"+notification.getPlayer().getNickName()+" personaggio: "+notification.getPlayer().getCharacterCard().getCharacterName()+" natura: "+notification.getPlayer().getCharacterCard().getCharacterType());
            break;
        case GAME_MAP_FILE_NAME:
            break;
        default:
            board.printMessage("Comando non supportato");
            break;
        }
    }
}

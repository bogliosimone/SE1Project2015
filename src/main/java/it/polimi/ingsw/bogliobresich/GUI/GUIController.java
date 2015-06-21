/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI;

import it.polimi.ingsw.bogliobresich.communication.client.ClientController;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

/**
 * @author matteobresich
 *
 */
public class GUIController implements Observer, Runnable {
    
    private static GUIController instance;
    private static ClientController controller;
    private View currentView;
    private View previousView;
    private ViewFactory viewFactory = new ViewFactory();
    
    private GUIController() {
        //Not called
    }
    
    public static synchronized GUIController getInstance() {
        if (instance == null) {
            instance = new GUIController();
        }
        return instance;
    }

    @Override
    public void update(Observable o, Object obsNotification) {
        
        if(obsNotification instanceof NotificationMessage)
        {
            NotificationMessage notification = (NotificationMessage)obsNotification;
            Commands command = notification.getCommand();
            switch(command) {
            case ALL_PLAYERS_MESSAGE:
                currentView.doUpdate(notification);
                break;
            case ATTACK:
                currentView.doUpdate(notification);
                break;
            case CALL_RUMOR:
                currentView.doUpdate(notification);
                break;
            case CANT_DISCARD_CARD:
                currentView.doUpdate(notification);
                break;
            case CANT_PLAY_CARD:
                currentView.doUpdate(notification);
                break;
            case CARDS_END:
                currentView.doUpdate(notification);
                break;
            case COORDINATE_ERROR:
                currentView.doUpdate(notification);
                break;
            case DISCARD_CARD:
                currentView.doUpdate(notification);
                break;
            case DISCARD_HAND:
                currentView.doUpdate(notification);
                break;
            case DRAW_CARD:
                currentView.doUpdate(notification);
                break;
            case DRAW_SECTOR_CARD:
                currentView.doUpdate(notification);
                break;
            case END_TURN:
                currentView.doUpdate(notification);
                break;
            case FATAL_ERROR:
                currentView.doUpdate(notification);
                break;
            case GAME_END:
                currentView.doUpdate(notification);
                break;
            case GAME_INFO_MESSAGE:
                currentView.doUpdate(notification);
                break;
            case GAME_START:
                currentView = viewFactory.getView(GUIViews.GAME_BOARD_VIEW);
                currentView.initView();
                break;
            case GENERIC_ERROR:
                currentView.doUpdate(notification);
                break;
            case GENERIC_MESSAGE:
                currentView.doUpdate(notification);
                break;
            case HAND_FULL:
                currentView.doUpdate(notification);
                break;
            case IS_NOT_YOUR_TURN:
                currentView.doUpdate(notification);
                break;
            case ITEM_PLAYED:
                currentView.doUpdate(notification);
                break;
            case LIST_USERS:
                currentView.doUpdate(notification);
                break;
            case MOVES_AVAIABLE:
                currentView.doUpdate(notification);
                break;
            case MOVE_NO_AVAIABLE:
                currentView.doUpdate(notification);
                break;
            case PLAYER_COMMAND:
                currentView.doUpdate(notification);
                break;
            case PLAYER_JOIN_WAIT_ROOM:
                currentView = viewFactory.getView(GUIViews.WAITING_ROOM_VIEW);
                currentView.initView();
                break;
            case PLAYER_MESSAGE:
                currentView.doUpdate(notification);
                break;
            case PORTHOLE_BROKEN:
                currentView.doUpdate(notification);
                break;
            case SECTOR_TYPE_MESSAGE:
                currentView.doUpdate(notification);
                break;
            case SERVER_NOT_RESPONDING:
                currentView.doUpdate(notification);
                break;
            case SET_YOUR_COORDINATE:
                currentView.doUpdate(notification);
                break;
            case START_END_PHASE:
                currentView.doUpdate(notification);
                break;
            case START_MOVEMENT_PHASE:
                currentView.doUpdate(notification);
                break;
            case START_TIMER:
                currentView.doUpdate(notification);
                break;
            case START_TURN:
                currentView.doUpdate(notification);
                break;
            case USER_END_IS_GAME:
                currentView.doUpdate(notification);
                break;
            case USER_END_TURN:
                currentView.doUpdate(notification);
                break;
            case USER_START_TURN:
                currentView.doUpdate(notification);
                break;
            case WHO_ARE_YOU:
                currentView.doUpdate(notification);
                break;
            case YOU_ARE_FEED:
                currentView.doUpdate(notification);
                break;
            case YOU_DIE:
                currentView.doUpdate(notification);
                break;
            case YOU_DISCONNECTED:
                currentView.doUpdate(notification);
                break;
            case YOU_LOST:
                currentView.doUpdate(notification);
                break;
            case YOU_WIN:
                currentView.doUpdate(notification);
                break;
            default:
                break;
            }
        }
        
    }

    @Override
    public void run() {
        initUI();
        controller.addObserver(this);
    }
    
    public void initUI() {
        controller = ClientController.getInstance();
//        currentView = viewFactory.getView(GUIViews.LOGO_VIEW);
//        currentView.initView();
        currentView = viewFactory.getView(GUIViews.LOGIN_VIEW);
        currentView.initView();
    }
    
    public void shutdown() {
        controller.deleteObserver(this);
    }
    
    public static void doLogin(String nickname, String password) {
        controller.doLogin(nickname, password);
    }
    
}

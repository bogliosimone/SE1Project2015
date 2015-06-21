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
            System.out.println(command + "" + notification.getArgument());
            switch(command) {
            case ALL_PLAYERS_MESSAGE:
                break;
            case ATTACK:
                break;
            case CALL_RUMOR:
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
                break;
            case GAME_START:
                currentView = viewFactory.getView(GUIViews.GAME_BOARD_VIEW);
                currentView.initView();
                break;
            case GENERIC_ERROR:
                break;
            case GENERIC_MESSAGE:
                currentView.doUpdate(notification);
                break;
            case HAND_FULL:
                break;
            case IS_NOT_YOUR_TURN:
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
                currentView = viewFactory.getView(GUIViews.WAITING_ROOM_VIEW);
                currentView.initView();
                break;
            case PLAYER_MESSAGE:
                break;
            case PORTHOLE_BROKEN:
                break;
            case SECTOR_TYPE_MESSAGE:
                break;
            case SERVER_NOT_RESPONDING:
                break;
            case SET_YOUR_COORDINATE:
                break;
            case START_END_PHASE:
                break;
            case START_MOVEMENT_PHASE:
                break;
            case START_TIMER:
                break;
            case START_TURN:
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

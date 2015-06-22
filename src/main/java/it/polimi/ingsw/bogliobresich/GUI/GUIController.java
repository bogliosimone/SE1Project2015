/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI;

import it.polimi.ingsw.bogliobresich.GUI.messageView.MessageView;
import it.polimi.ingsw.bogliobresich.communication.client.ClientController;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.awt.EventQueue;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

/**
 * @author matteobresich
 *
 */
public class GUIController implements Observer, Runnable {

    private static GUIController instance;
    private static ClientController controller;
    private ImagesHolder imagesHolder = ImagesHolder.getInstance();
    private View currentView;
    private View previousView;
    private View nextView;

    private ViewFactory viewFactory = new ViewFactory();
    private Player myPlayer;

    private List <User> users;
    
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
                getCurrentView().doUpdate(notification);
                break;
            case ATTACK:
                getCurrentView().doUpdate(notification);
                break;
            case CALL_RUMOR:
                getCurrentView().doUpdate(notification);
                break;
            case CANT_DISCARD_CARD:
                getCurrentView().doUpdate(notification);
                break;
            case CANT_PLAY_CARD:
                getCurrentView().doUpdate(notification);
                break;
            case CARDS_END:
                getCurrentView().doUpdate(notification);
                break;
            case COORDINATE_ERROR:
                getCurrentView().doUpdate(notification);
                break;
            case DISCARD_CARD:
                getCurrentView().doUpdate(notification);
                break;
            case DISCARD_HAND:
                getCurrentView().doUpdate(notification);
                break;
            case DRAW_CARD:
                getCurrentView().doUpdate(notification);
                break;
            case DRAW_SECTOR_CARD:
                getCurrentView().doUpdate(notification);
                break;
            case END_TURN:
                getCurrentView().doUpdate(notification);
                break;
            case FATAL_ERROR:
                getCurrentView().doUpdate(notification);
                break;
            case GAME_END:
                getCurrentView().doUpdate(notification);
                break;
            case GAME_INFO_MESSAGE:
                getCurrentView().doUpdate(notification);
                break;
            case GAME_START:
                setNextView(viewFactory.getView(GUIViews.GAME_BOARD_VIEW));
                changeToNextView();
                createMessageView("ciao è iniziato il tuo turno!",imagesHolder.getItemIcon());
                break;
            case GENERIC_ERROR:
                getCurrentView().doUpdate(notification);
                break;
            case GENERIC_MESSAGE:
                getCurrentView().doUpdate(notification);
                break;
            case HAND_FULL:
                getCurrentView().doUpdate(notification);
                break;
            case IS_NOT_YOUR_TURN:
                getCurrentView().doUpdate(notification);
                break;
            case ITEM_PLAYED:
                getCurrentView().doUpdate(notification);
                break;
            case LIST_USERS:
                setUsers(notification.getListOfUsers());
                break;
            case MOVES_AVAIABLE:
                getCurrentView().doUpdate(notification);
                break;
            case MOVE_NO_AVAIABLE:
                getCurrentView().doUpdate(notification);
                break;
            case PLAYER_COMMAND:
                getCurrentView().doUpdate(notification);
                break;
            case PLAYER_JOIN_WAIT_ROOM:
                setNextView(viewFactory.getView(GUIViews.WAITING_ROOM_VIEW));
                changeToNextView();
                break;
            case PLAYER_MESSAGE:
                getCurrentView().doUpdate(notification);
                break;
            case PORTHOLE_BROKEN:
                getCurrentView().doUpdate(notification);
                break;
            case SECTOR_TYPE_MESSAGE:
                getCurrentView().doUpdate(notification);
                break;
            case SERVER_NOT_RESPONDING:
                getCurrentView().doUpdate(notification);
                break;
            case SET_YOUR_COORDINATE:
                getCurrentView().doUpdate(notification);
                break;
            case START_END_PHASE:
                getCurrentView().doUpdate(notification);
                break;
            case START_MOVEMENT_PHASE:
                getCurrentView().doUpdate(notification);
                break;
            case START_TIMER:
                getCurrentView().doUpdate(notification);
                break;
            case START_TURN:
                getCurrentView().doUpdate(notification);
                break;
            case USER_END_IS_GAME:
                getCurrentView().doUpdate(notification);
                break;
            case USER_END_TURN:
                getCurrentView().doUpdate(notification);
                break;
            case USER_START_TURN:
                getCurrentView().doUpdate(notification);
                break;
            case WHO_ARE_YOU:
                setMyPlayer(notification.getPlayer());
                break;
            case YOU_ARE_FEED:
                getCurrentView().doUpdate(notification);
                break;
            case YOU_DIE:
                getCurrentView().doUpdate(notification);
                break;
            case YOU_DISCONNECTED:
                getCurrentView().doUpdate(notification);
                break;
            case YOU_LOST:
                getCurrentView().doUpdate(notification);
                break;
            case YOU_WIN:
                getCurrentView().doUpdate(notification);
                break;
            default:
                break;
            }
        }
    }


    @Override
    public void run() {
        controller = ClientController.getInstance();
        controller.addObserver(this);
        initUI();
    }

    private void initUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setCurrentView(viewFactory.getView(GUIViews.LOGO_VIEW));
                getCurrentView().initView();
                setNextView(viewFactory.getView(GUIViews.LOGIN_VIEW));
                changeToNextView();
            }
        });
    }

    private void changeToNextView() {
        setPreviousView(getCurrentView());
        setCurrentView(getNextView());
        getCurrentView().initView();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                getPreviousView().dispose();
            }
        });
    }
    
    public MessageView createMessageView(final String message, final ImageIcon image) {
            final MessageView messageView = new MessageView(message, image);
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    messageView.initView();
                }
            });
            return messageView;
    }

    private void shutdown() {
        controller.deleteObserver(this);
    }

    public static void doLogin(String nickname, String password) {
        controller.doLogin(nickname, password);
    }

    public void setCurrentView(View view) {
        this.currentView = view;
    }

    public View getCurrentView() {
        return currentView;
    }

    public View getPreviousView() {
        return previousView;
    }

    public void setPreviousView(View previousView) {
        this.previousView = previousView;
    }

    public View getNextView() {
        return nextView;
    }

    public void setNextView(View nextView) {
        this.nextView = nextView;
    }

    public List <User> getUserList() {
        return users;
    }

    public void setUsers(List <User> users) {
        this.users = users;
    }

    public Player getMyPlayer() {
        return myPlayer;
    }

    public void setMyPlayer(Player myPlayer) {
        this.myPlayer = myPlayer;
    }
}

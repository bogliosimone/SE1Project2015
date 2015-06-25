/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI;

import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.UserListProprieties;
import it.polimi.ingsw.bogliobresich.GUI.messageView.MessageView;
import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.client.ClientController;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SectorCard;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;
import it.polimi.ingsw.bogliobresich.model.player.ItemHand;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
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
    private List <Player> players;
    private ItemHand handOfCards;
    private int idCardSelected = -1;
    
    private UserListProprieties commandPanelUserList;

    private String mapFileName;
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
        NotificationMessage notification = controller.getQueueNotification().poll();

//        if(obsNotification instanceof NotificationMessage)
//        {
//            NotificationMessage notification = (NotificationMessage)obsNotification;
            Commands command = notification.getCommand();
            switch(command) {
            case LIST_PLAYERS_END_GAME:
                setPlayers(notification.getListOfPlayers()); // li salvo nella lista 
                //fare nuova finestra con lista vincitori
                break;
            case ATTACK:
                //up
                break;
            case CALL_RUMOR:
                //up
                break;
            case CANT_DISCARD_CARD:
                createMessageView("Non puoi scartare questa carta",null);
                break;
            case CANT_PLAY_CARD:
                createMessageView("Non puoi giocare questa carta\nin questa fase di gioco",null);
                break;
            case CARDS_END:
                createMessageView("Le carte oggetto sono finite",null);
                break;
            case COORDINATE_ERROR:
                createMessageView("Coordinata non valida",null);
                break;
            case DISCARD_CARD:
                this.handOfCards.removeCard(notification.getItemCard());
                break;
            case DISCARD_HAND:
                this.handOfCards.discardHand();
                break;
            case DRAW_CARD:
                ItemCard card = notification.getItemCard();
                handOfCards.addCard(card);
                //createMessageView("La carta settore contiene un oggetto",imagesHolder.getItemIcon());
                break;
            case DRAW_SECTOR_CARD:
                String s=new String("");
                SectorCard sc = notification.getSectorCard();
                if(sc.isThereAnItemToDraw()){
                    s="\nLa carta contiene un oggetto";
                }
                if(sc.isThereNoiseInAnySector()) {
                    createMessageView("Hai pescato la carta:\nRUMORE IN QUALUNQUE SETTORE"+s,imagesHolder.getRumorXY());
                }
                if(sc.isThereNoiseInMySector()) {
                    createMessageView("Hai pescato la carta:\nRUMORE NEL TUO SETTORE"+s,imagesHolder.getRumorMySector());
                }
                if(sc.isThereSilence()) {
                    createMessageView("Hai pescato la carta:\nSILENZIO"+s,imagesHolder.getSilence());
                }
                break;
            case END_TURN:
                //createMessageView(myPlayer.getNickName()+" il tuo turno è finito",null);
                break;
            case FATAL_ERROR:
                createMessageView(notification.getString(),null);
                break;
            case GAME_END:
                setNextView(viewFactory.getView(GUIViews.WINNERS_VIEW));
                changeToNextView();
                break;
            case GAME_INFO_MESSAGE:
                //up
                break;
            case GAME_START:
                setNextView(viewFactory.getView(GUIViews.GAME_BOARD_VIEW));
                changeToNextView();
                break;
            case GENERIC_ERROR:
                createMessageView(notification.getString(),null);
                break;
            case GENERIC_MESSAGE:
                //up
                break;
            case HAND_FULL:
                createMessageView("La tua mano è piena",null);
                break;
            case IS_NOT_YOUR_TURN:
                createMessageView(myPlayer.getNickName() + " non è il tuo turno",null);
                break;
            case ITEM_PLAYED:
                //up
                break;
            case LIST_USERS:
                setUsers(notification.getListOfUsers());
                setCommandPanelUserList(new UserListProprieties(notification.getListOfUsers() , Color.WHITE ,""));
                break;
            case MOVES_AVAIABLE:
                //up
                break;
            case MOVE_NO_AVAIABLE:
                createMessageView("Mossa non disponibile",null);
                break;
            case PLAYER_JOIN_WAIT_ROOM:
                setNextView(viewFactory.getView(GUIViews.WAITING_ROOM_VIEW));
                changeToNextView();
                break;
            case PLAYER_MESSAGE:
                //up
                break;
            case PORTHOLE_BROKEN:
                //up
                break;
            case SECTOR_TYPE_MESSAGE:
                //not used - used in CLI, string with safe unsafe porthole sector
                break;
            case SERVER_NOT_RESPONDING:
                createMessageView("Il server non risponde - ERRORE",null);
                break;
            case SET_YOUR_COORDINATE:
                //up
                break;
            case START_END_PHASE:
                //up
                break;
            case START_MOVEMENT_PHASE:
                //up
                break;
            case START_TIMER:
                //start timer, not implemented
                break;
            case START_TURN:
                createMessageView(myPlayer.getNickName()+" è il tuo turno",null);
                break;
            case USER_END_TURN:
                commandPanelUserList.setUserColor(notification.getUser(), Color.WHITE);
                break;
            case USER_START_TURN:
                commandPanelUserList.setUserColor(notification.getUser(), Color.GREEN);
                break;
            case WHO_ARE_YOU:
                setMyPlayer(notification.getPlayer());
                handOfCards = myPlayer.getHand();
                break;
            case YOU_ARE_FEED:
                createMessageView(myPlayer.getNickName()+" ti sei nutrito di un umano\n Ora puoi muoverti di tre caselle",null);
                break;
            case YOU_DIE:
                createMessageView(myPlayer.getNickName()+" sei morto!",null);
                break;
            case YOU_DISCONNECTED:
                createMessageView(myPlayer.getNickName()+" sei stato disconnesso per inattività!",null);
                break;
            case YOU_LOST:
                //createMessageView(myPlayer.getNickName()+"Hai perso! :(",null);
                break;
            case YOU_WIN:
                //createMessageView(myPlayer.getNickName()+"Hai vinto! :)",null);
                break;
            case USER_DISCONNECTED:
                commandPanelUserList.setUserColor(notification.getUser(), new Color(239,239,41));
                commandPanelUserList.setUserState(notification.getUser(), "DISCONNECTED");
                break;
            case HUMAN_ESCAPE:
                commandPanelUserList.setUserColor(notification.getPlayer().getUser(), new Color(41,147,239));
                commandPanelUserList.setUserState(notification.getPlayer().getUser(), "ESCAPED");
                break;
            case PLAYER_DIE:
                commandPanelUserList.setUserColor(notification.getPlayer().getUser(), Color.RED);
                commandPanelUserList.setUserState(notification.getPlayer().getUser(), "DEAD");
                break;
            case GAME_MAP_FILE_NAME:
                setMapFileName(notification.getString());
                break;
            default:
                break;
            }

            getCurrentView().doUpdate(notification);
        }
//    }


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

    //TO NETWORK CONTROLLER
    
    public static void doLogin(String nickname, String password) {
        controller.doLogin(nickname, password);
        
    }
    
    public static void sendCommand(ClientCommand command) {
        controller.sendCommand(command);
    }
    
    //SETTER AND GETTER

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

    /**
     * @return the handOfCards
     */
    public ItemHand getHandOfCards() {
        return handOfCards;
    }

    /**
     * @param handOfCards the handOfCards to set
     */
    public void setHandOfCards(ItemHand handOfCards) {
        this.handOfCards = handOfCards;
    }

    public int getIdCardSelected() {
        return idCardSelected;
    }

    public void setIdCardSelected(int idCardSelected) {
        this.idCardSelected = idCardSelected;
    }

    public String getMapFileName() {
        return mapFileName;
    }

    public void setMapFileName(String mapFileName) {
        this.mapFileName = mapFileName;
    }

    /**
     * @return the commandPanelUserList
     */
    public UserListProprieties getCommandPanelUserList() {
        return commandPanelUserList;
    }

    /**
     * @param commandPanelUserList the commandPanelUserList to set
     */
    public void setCommandPanelUserList(UserListProprieties commandPanelUserList) {
        this.commandPanelUserList = commandPanelUserList;
    }

    public List <Player> getPlayers() {
        return players;
    }

    public void setPlayers(List <Player> players) {
        this.players = players;
    }
}

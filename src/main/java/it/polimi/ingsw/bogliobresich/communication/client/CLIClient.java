/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.client;

import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.notifications.Notification;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationQueue;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author matteobresich
 *
 */
public class CLIClient implements Observer{
    
    public CLIClient () {
        Client c = new Client((Observer)this);
    }
    
    

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof NotificationQueue) {
            final NotificationQueue queue = (NotificationQueue)o;
            new Thread(new Runnable()
            {
                @Override
                public void run() {
                    while (!queue.isEmpty()) {
                        Commands command = queue.getNotificationCommand();
                        switch(command) {
                        
                        case ALL_PLAYERS_MESSAGE:
                            System.out.println(queue.getNotificationCommand() + " - " + queue.getGenericMessage());
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
                            System.out.println(queue.getNotificationCommand() + " - " + queue.getGenericMessage());
                            break;
                        case GAME_START:
                            System.out.println(queue.getNotificationCommand());
                            break;
                        case GENERIC_ERROR:
                            break;
                        case GENERIC_MESSAGE:
                            System.out.println(queue.getNotificationCommand() + " - " + queue.getGenericMessage());
                            break;
                        case HAND_FULL:
                            break;
                        case IS_NOT_YOUR_TURN:
                            break;
                        case ITEM_PLAYED:
                            break;
                        case LIST_USERS:
                            List<User> users = queue.getListOfUsers();
                            System.out.println(queue.getNotificationCommand() + " " + users);
                            break;
                        case MOVES_AVAIABLE:
                            break;
                        case MOVE_NO_AVAIABLE:
                            break;
                        case PLAYER_COMMAND:
                            break;
                        case PLAYER_JOIN_WAIT_ROOM:
                            System.out.println(queue.getNotificationCommand());
                            break;
                        case PLAYER_MESSAGE:
                            break;
                        case PORTHOLE_BROKEN:
                            break;
                        case SECTOR_TYPE_MESSAGE:
                            System.out.println(queue.getNotificationCommand() + " - " + queue.getGenericMessage()); 
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
                            System.out.println(queue.getNotificationCommand()); 
                            break;
                        case USER_END_IS_GAME:
                            break;
                        case USER_END_TURN:
                            break;
                        case USER_START_TURN: 
                            break;
                        case WHO_ARE_YOU:
                            Player p = queue.getPlayerArgument();
                            System.out.println(queue.getNotificationCommand() + " - " + p);
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
                        Notification n = queue.pollNotification();
                        //System.out.println(n.getCommand()  + " " + n.getArgument());
                    }
                }
            }).start();
        }
    }

}

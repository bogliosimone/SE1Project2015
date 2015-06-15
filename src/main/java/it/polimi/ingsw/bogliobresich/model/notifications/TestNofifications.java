/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;

import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author matteobresich
 *
 */
public class TestNofifications {

    /**
     * @param args
     * 
     * TEST NOTIFICATION
     */
    public static void main(String[] args) {
        NotificationQueue queue = new NotificationQueueHandler();
        NotificationMessage notification = new NotificationMessage(Commands.GENERIC_MESSAGE, "Messaggio per il server");
        
        queue.addNotification(notification);
        
        System.out.println(queue.getNotificationCommand());
        String msg = queue.getGenericMessage();
        System.out.println(msg);
        
        
        boolean isBroadcast = false;
        //address da inizializzare con chi deve ricevere il messaggio. ATTENZIONE se è null o il messaggio è broadcast o arriva al server
        User address = new User(0, null, null);
        notification = new NotificationMessage(Commands.PLAYER_MESSAGE,"Messaggio per il player",isBroadcast,address);
        queue.addNotification(notification);
        
        System.out.println(queue.getNotificationCommand());
        msg = queue.getGenericMessage();
        System.out.println(msg);
        
        
        isBroadcast = true;
        address = null;
        notification = new NotificationMessage(Commands.ALL_PLAYERS_MESSAGE,"Messaggio per i players",isBroadcast,address);
        queue.addNotification(notification);
        
        System.out.println(queue.getNotificationCommand());
        msg = queue.getGenericMessage();
        System.out.println(msg);
        
        
        isBroadcast = false;
        address = new User(0, null, null);
        Coordinate c = new Coordinate('C',9);
        notification = new NotificationMessage(Commands.PLAYER_COMMAND,c,isBroadcast,address);
        queue.addNotification(notification);
        
        System.out.println(queue.getNotificationCommand());
        Coordinate recivedCoordinate = queue.getCoordinateArgument();
        System.out.println(recivedCoordinate);
        
        
        
        
    }
}

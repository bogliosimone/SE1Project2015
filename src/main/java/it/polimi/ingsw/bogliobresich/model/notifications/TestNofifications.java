/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;

import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
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
        NotificationStack stack = new NotificationStackHandler();
        NotificationMessage notification = new NotificationMessage(Commands.GENERIC_MESSAGE, "Messaggio per il server");
        
        stack.pushNotification(notification);
        
        System.out.println(stack.getNotificationCommand());
        String msg = stack.getGenericMessage();
        System.out.println(msg);
        
        
        boolean isBroadcast = false;
        //address da inizializzare con chi deve ricevere il messaggio. ATTENZIONE se è null o il messaggio è broadcast o arriva al server
        Player address = new Player(null, null, null);
        notification = new NotificationMessage(Commands.PLAYER_MESSAGE,"Messaggio per il player",isBroadcast,address);
        stack.pushNotification(notification);
        
        System.out.println(stack.getNotificationCommand());
        msg = stack.getGenericMessage();
        System.out.println(msg);
        
        
        isBroadcast = true;
        address = null;
        notification = new NotificationMessage(Commands.ALL_PLAYERS_MESSAGE,"Messaggio per i players",isBroadcast,address);
        stack.pushNotification(notification);
        
        System.out.println(stack.getNotificationCommand());
        msg = stack.getGenericMessage();
        System.out.println(msg);
        
        
        isBroadcast = false;
        address = new Player(null, null, null);
        Coordinate c = new Coordinate('C',9);
        notification = new NotificationMessage(Commands.PLAYER_COMMAND,c,isBroadcast,address);
        stack.pushNotification(notification);
        
        System.out.println(stack.getNotificationCommand());
        Coordinate recivedCoordinate = stack.getCoordinateArgument();
        System.out.println(recivedCoordinate);
        
        
        
        
        
        
    }
}

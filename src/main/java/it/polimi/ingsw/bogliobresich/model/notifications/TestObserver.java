package it.polimi.ingsw.bogliobresich.model.notifications;

import java.util.Observable;
import java.util.Observer;


public class TestObserver {
    public NotificationQueue queue = new NotificationQueueHandler();
    public TestServer t = new TestServer();
    
    public static void main (String args[]) {
        new TestObserver();
    }
    
    public TestObserver () {
        queue.addObserver(new TestServer());
        NotificationMessage notification = new NotificationMessage(Commands.GENERIC_MESSAGE, "Messaggio per il server");
        NotificationMessage nall = new NotificationMessage(Commands.ALL_PLAYERS_MESSAGE, "Messaggio per tutti!",true,null);
        queue.addNotification(notification);
        queue.addNotification(nall);
        queue.addNotification(nall);
        queue.addNotification(nall);
        queue.addNotification(nall);
        queue.addNotification(notification);
        queue.addNotification(notification);
    }
    
    
    private class TestServer implements Observer {

        public TestServer() {
            
        }
        
        @Override
        public void update(Observable o, Object arg) {
            NotificationQueue queue = (NotificationQueue)o;
            if(queue.getNotificationCommand() == Commands.ALL_PLAYERS_MESSAGE) {
                System.out.println("Messagge for players: " + queue.getString());
                queue.pollNotification();
            }
            if(queue.getNotificationCommand() == Commands.GENERIC_MESSAGE) {
                System.out.println("Server: " + queue.getString());
                queue.pollNotification();
            }
        }
        
    }

}

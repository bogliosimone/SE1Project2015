package it.polimi.ingsw.bogliobresich.model.notifications;

import java.util.Observable;
import java.util.Observer;


public class TestObserver {
    public NotificationStack stack = new NotificationStackHandler();
    public TestServer t = new TestServer();
    
    public static void main (String args[]) {
        TestObserver t = new TestObserver();
    }
    
    public TestObserver () {
        stack.addObserver(new TestServer());
        NotificationMessage notification = new NotificationMessage(Commands.GENERIC_MESSAGE, "Messaggio per il server");
        NotificationMessage nall = new NotificationMessage(Commands.ALL_PLAYERS_MESSAGE, "Messaggio per tutti!",true,null);
        stack.pushNotification(notification);
        stack.pushNotification(nall);
    }
    
    
    private class TestServer implements Observer {

        public TestServer() {
            
        }
        
        @Override
        public void update(Observable o, Object arg) {
            NotificationStack stack = (NotificationStack)o;
            if(stack.getNotificationCommand() == Commands.ALL_PLAYERS_MESSAGE) {
                System.out.println("Messagge for players: " + stack.getGenericMessage());
            }
            if(stack.getNotificationCommand() == Commands.GENERIC_MESSAGE) {
                System.out.println("Server: " + stack.getGenericMessage());
            }
        }
        
    }

}

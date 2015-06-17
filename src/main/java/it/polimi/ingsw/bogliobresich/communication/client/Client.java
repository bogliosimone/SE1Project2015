/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.client;

import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.bogliobresich.communication.client.exception.AddToMatchException;
import it.polimi.ingsw.bogliobresich.communication.client.exception.LoginException;
import it.polimi.ingsw.bogliobresich.communication.server.ServerUtils;
import it.polimi.ingsw.bogliobresich.model.notifications.Notification;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationQueue;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationQueueHandler;

/**
 * @author matteobresich
 *
 */
public class Client implements Observer{
    
    private NotificationQueue notificationQueue = null;
    private ClientCommunicationStrategy communication = null;
    
    public Client() {
        notificationQueue = new NotificationQueueHandler(false);
        notificationQueue.addObserver(this);
        
        
            try {
                communication = new RMIClient(notificationQueue);
            } catch (RemoteException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        try {
            String url = "//localhost:"+ ServerUtils.RMI_REQUEST_SERVER_TCP_PORT +"/"+ ServerUtils.REMOTE_CONNECTION_NAME;
            communication.doLogin(url, "utente", "password");
            communication.addMeMatch();
            
            } catch (LoginException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (AddToMatchException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
                        //DO SOMETHING THEN POLL
                        Notification notification = queue.pollNotification();
                        System.out.println(((Notification)notification).getCommand() + " got message:" + ((Notification)notification).getArgument());
                    }
                }
            }).start();
        }
        
    }

}

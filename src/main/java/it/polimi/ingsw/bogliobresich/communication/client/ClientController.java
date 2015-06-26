/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.client;

import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.client.exception.AddToMatchException;
import it.polimi.ingsw.bogliobresich.communication.client.exception.LoginException;
import it.polimi.ingsw.bogliobresich.communication.client.exception.SendCommandException;
import it.polimi.ingsw.bogliobresich.communication.notification.Notification;
import it.polimi.ingsw.bogliobresich.communication.notification.NotificationMessage;
import it.polimi.ingsw.bogliobresich.communication.notification.NotificationQueue;
import it.polimi.ingsw.bogliobresich.communication.notification.NotificationQueueHandler;
import it.polimi.ingsw.bogliobresich.communication.server.ServerUtils;

import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author matteobresich
 *
 */
public class ClientController extends Observable implements Observer, Client {

    private ClientCommunicationStrategy communication = null;
    private static ClientController instance;
    private Queue <NotificationMessage> queueNotification = new ConcurrentLinkedQueue <NotificationMessage>();
    
    public Queue<NotificationMessage> getQueueNotification() {
        return queueNotification;
    }

    public void setQueueNotification(Queue<NotificationMessage> queueNotification) {
        this.queueNotification = queueNotification;
    }

    public static synchronized ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }
    
    public ClientController() {
        try {
            communication = new RMIClient();
            communication.addNotificationObserver(this);
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
    }
    

    @Override
    public void doLogin(String nickname, String password) {
        final String name = nickname;
        final String passwd = password;
        new Thread(new Runnable()
        {
            @Override
            public void run() {
                try {
                    String url = "//localhost:"+ ServerUtils.RMI_REQUEST_SERVER_TCP_PORT +"/"+ ServerUtils.REMOTE_CONNECTION_NAME;
                    communication.doLogin(url, name, passwd);
                    communication.addMeMatch();

                } catch (LoginException e) {
                    e.getStackTrace();

                } catch (AddToMatchException e) {
                    e.getStackTrace();
                }
            }
        }).start();
    }


    @Override
    public void sendCommand(ClientCommand command) {
        final ClientCommand cmd = command;
        new Thread(new Runnable()
        {
            @Override
            public void run() {
                try {
                    communication.sendCommand(cmd);
                } catch (SendCommandException e) {
                    e.getStackTrace();
                }
            }
        }).start();
        
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof NotificationQueueHandler) {
            NotificationQueue queue = ((NotificationQueueHandler)o);
            Notification notification = queue.pollNotification();
            queueNotification.add((NotificationMessage) notification);
            setChanged();
            notifyObservers();
        }
    }
}

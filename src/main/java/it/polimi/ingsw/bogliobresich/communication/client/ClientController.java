/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.client;

import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.client.exception.AddToMatchException;
import it.polimi.ingsw.bogliobresich.communication.client.exception.LoginException;
import it.polimi.ingsw.bogliobresich.communication.client.exception.SendCommandException;
import it.polimi.ingsw.bogliobresich.communication.server.ServerUtils;
import it.polimi.ingsw.bogliobresich.model.notifications.Notification;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationQueue;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationQueueHandler;

import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

/**
 * @author matteobresich
 *
 */
public class ClientController extends Observable implements Observer, Client {

    private ClientCommunicationStrategy communication = null;
    private static ClientController instance;

    
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
        try {
            String url = "//localhost:"+ ServerUtils.RMI_REQUEST_SERVER_TCP_PORT +"/"+ ServerUtils.REMOTE_CONNECTION_NAME;
            communication.doLogin(url, nickname, password);
            communication.addMeMatch();

        } catch (LoginException e) {
            e.getStackTrace();

        } catch (AddToMatchException e) {
            e.getStackTrace();
        }
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
            setChanged();
            notifyObservers(notification);
        }
    }
}

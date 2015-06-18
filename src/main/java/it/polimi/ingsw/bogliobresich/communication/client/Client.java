/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.client;

import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.CommandType;
import it.polimi.ingsw.bogliobresich.communication.client.exception.AddToMatchException;
import it.polimi.ingsw.bogliobresich.communication.client.exception.LoginException;
import it.polimi.ingsw.bogliobresich.communication.client.exception.SendCommandException;
import it.polimi.ingsw.bogliobresich.communication.server.ServerUtils;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationQueue;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationQueueHandler;
import it.polimi.ingsw.bogliobresich.model.player.ItemHand;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * @author matteobresich
 *
 */
public class Client implements ClientController {
    
    private NotificationQueue inputNotificationQueue = null;
    private ClientCommunicationStrategy communication = null;
    
    private ItemHand myHand = null;
    
    public Client(Observer o) {
        inputNotificationQueue = new NotificationQueueHandler(false);
        inputNotificationQueue.addObserver(o);
        
        try {
            communication = new RMIClient(inputNotificationQueue);
        } catch (RemoteException e1) {
            // TODO Auto-generated catch block
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
                System.out.println("Errore! impossibile effettuare il login!");
                //e.printStackTrace();
            } catch (AddToMatchException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }


    @Override
    public void sendCommand(ClientCommand command) {
        try {
            communication.sendCommand(command);
        } catch (SendCommandException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}

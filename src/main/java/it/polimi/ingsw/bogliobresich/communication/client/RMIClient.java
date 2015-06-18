package it.polimi.ingsw.bogliobresich.communication.client;

import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.client.exception.AddToMatchException;
import it.polimi.ingsw.bogliobresich.communication.client.exception.LoginException;
import it.polimi.ingsw.bogliobresich.communication.client.exception.SendCommandException;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIConnectionService;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIMatchService;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.notifications.Notification;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationQueue;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class RMIClient extends UnicastRemoteObject implements RemoteObserver, ClientCommunicationStrategy {
    
    /**
     * 
     */
    private static final long serialVersionUID = 4074353858175931035L;
    
    private NotificationQueue notificationQueue = null;
    protected RMIClient(NotificationQueue notificationQueue) throws RemoteException {
        super();
        this.notificationQueue = notificationQueue;
    }

    private RMIConnectionService remoteConnectionService = null;
    private RMIMatchService matchService = null; 
    private User myUser = null;
    
    public User getMyUser() {
        return myUser;
    }
    
    //--- TO SERVER ---
    
    public void doLogin(String url, String nickname, String password) throws LoginException {
        try {
            remoteConnectionService = (RMIConnectionService) Naming.lookup(url);
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            throw new LoginException(e.getMessage());
        }
        try {
            myUser = remoteConnectionService.login(nickname, password);
        } catch (RemoteException e) {
            throw new LoginException(e.getMessage());
        }
    }
    
    public void addMeMatch() throws AddToMatchException {
        addToMatch(myUser);
    }
    
    public void addToMatch(User user) throws AddToMatchException {
        try {
            matchService = remoteConnectionService.getMatch(user);
            if(matchService != null) {
                matchService.addObserver(user, this);
            }
        }
        catch (RemoteException e) {
            throw new AddToMatchException();
        }
    }
    

    @Override
    public void sendCommand(ClientCommand command) throws SendCommandException {
        try {
            matchService.doAction(myUser, command);
        } catch (RemoteException e) {
            throw new SendCommandException();
        }
    }
    
    // --- TO CLIENT ---
    
    @Override
    public void update(Serializable observable, Object msg) throws RemoteException {
        notificationQueue.addNotification((NotificationMessage)msg);
    }

    @Override
    public void ErrorToClient(NotificationMessage notification) {
        notificationQueue.addNotification(notification);
    }
}
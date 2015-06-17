package it.polimi.ingsw.bogliobresich.communication.client;

import it.polimi.ingsw.bogliobresich.communication.ClientCommands;
import it.polimi.ingsw.bogliobresich.communication.client.exception.AddToMatchException;
import it.polimi.ingsw.bogliobresich.communication.client.exception.LoginException;
import it.polimi.ingsw.bogliobresich.communication.server.ServerUtils;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIConnectionService;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIMatchService;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.notifications.Notification;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationQueue;

import java.io.Console;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


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
    
    public void doLogin(String url, String nickname, String password) throws LoginException{
        try {
            remoteConnectionService = (RMIConnectionService) Naming.lookup(url);
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            throw new LoginException();
        }
        try {
            myUser = remoteConnectionService.login(nickname, password);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
    
    public void sendCommand() {
        //TODO
    }
    
    public User getMyUser() {
        return myUser;
    }
    

    public static void main(String[] args) {
        try {
            String url = "//localhost:"+ ServerUtils.RMI_REQUEST_SERVER_TCP_PORT +"/"+ ServerUtils.REMOTE_CONNECTION_NAME;
            Scanner scan = new Scanner(System.in);
            RMIClient client = new RMIClient(null);
            
//            try {
//                  client.doLogin(url, "nome", "password");
//                  client.addMeMatch();
//  
//                  String coordinate = scan.next();
//                  char ch = coordinate.charAt(0);
//                  int num = Integer.parseInt(coordinate.substring(1));
//                  m.doAction(user, ClientCommands.DO_MOVE_REQUEST, new Coordinate(ch, num));
//                  scan.close();
//                
//            }
//            catch(RemoteException e) {
//                System.err.println("Impossibile collegarsi al server: " + e);
//            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void printClientCommands() {
        for(ClientCommands c : ClientCommands.values()) {
            System.out.println("-" + c.getCommandName() + "\t\t" + c.getCommandDescription());
        }
    }

    @Override
    public void update(Serializable observable, Object msg)
            throws RemoteException {
        System.out.println(((Notification)msg).getCommand() + " got message:" + ((Notification)msg).getArgument());
    }

}
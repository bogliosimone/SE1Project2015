package it.polimi.ingsw.bogliobresich.communication.client;

import it.polimi.ingsw.bogliobresich.communication.ClientCommands;
import it.polimi.ingsw.bogliobresich.communication.server.ServerUtils;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIConnectionService;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIMatchService;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.notifications.Notification;

import java.io.Console;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


public class RmiClient extends UnicastRemoteObject implements RemoteObserver {
    protected RmiClient() throws RemoteException {
        super();
    }

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        try {
            String url = "//localhost:"+ ServerUtils.RMI_REQUEST_SERVER_TCP_PORT +"/"+ ServerUtils.REMOTE_CONNECTION_NAME;
            String nickname;
            String password;
            Scanner scan = new Scanner(System.in);
            
            RMIConnectionService remoteService = (RMIConnectionService) Naming.lookup(url);
            
            System.out.println("--- LOGIN ---");
            System.out.print("Utente: ");
            nickname = scan.next();
            System.out.print("Password: ");
            password = scan.next();
            
            RmiClient client = new RmiClient();
            try {
                  
                  User user = remoteService.login(nickname, password);
                  RMIMatchService m;
                  m =  remoteService.getMatch(user);
                  System.out.println("--- AGGIUNTO IN SALA DI ATTESA ---");
                  m.addObserver(user, client);
  
                  String coordinate = scan.next();
                  char ch = coordinate.charAt(0);
                  int num = Integer.parseInt(coordinate.substring(1));
                  
                  m.doAction(user, ClientCommands.DO_MOVE_REQUEST, new Coordinate(ch, num));
  
                  scan.close();
                
            }
            catch(RemoteException e) {
                System.err.println("Impossibile collegarsi al server: " + e);
            }
            
            scan.close();
            
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
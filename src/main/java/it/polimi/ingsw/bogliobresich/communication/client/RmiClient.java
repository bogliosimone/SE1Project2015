package it.polimi.ingsw.bogliobresich.communication.client;

import it.polimi.ingsw.bogliobresich.communication.ClientCommands;
import it.polimi.ingsw.bogliobresich.communication.server.ServerUtils;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIConnectionService;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIMatchService;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;

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
            System.out.println(url);
            RMIConnectionService remoteService = (RMIConnectionService) Naming.lookup(url);
            
            System.out.print("Presentati: ");
            String nickname;
            Scanner bis = new Scanner(System.in);
            nickname = bis.next();
            
            
            RmiClient client = new RmiClient();
            try {
                  User user = remoteService.login(nickname, nickname);
                  RMIMatchService m;
                  m =  remoteService.connectToMatch(user);
                  System.out.println(m);
                  System.out.println("//localhost:"+ ServerUtils.RMI_REQUEST_SERVER_TCP_PORT +"/" + m.getMatchHandlerID());
                  m.addObserver(client);
                  
                  String coordinate = bis.next();
                  char ch = coordinate.charAt(0);
                  int num = Integer.parseInt(coordinate.substring(1));
                  
                  m.doAction(user, ClientCommands.DO_MOVE_REQUEST, new Coordinate(ch, num));
                
            }
            catch(RemoteException e) {
                System.err.println("Impossibile collegarsi al server: " + e);
            }
            
            bis.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Serializable observable, Object updateMsg)
            throws RemoteException {
        System.out.println("got message:" + updateMsg);
    }

}
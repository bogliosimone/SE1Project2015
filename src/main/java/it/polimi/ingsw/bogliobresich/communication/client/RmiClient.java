package it.polimi.ingsw.bogliobresich.communication.client;

import it.polimi.ingsw.bogliobresich.communication.server.CommunicationUtil;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIConnectionService;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIMatchHandlerService;

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
            
            String url = "//localhost:"+ CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT +"/"+ CommunicationUtil.REMOTE_CONNECTION_NAME;
            System.out.println(url);
            RMIConnectionService remoteService = (RMIConnectionService) Naming.lookup(url);
            
            System.out.print("Presentati: ");
            String nickname;
            Scanner bis = new Scanner(System.in);
            nickname = bis.next();
            bis.close();
            
            RmiClient client = new RmiClient();
            try {
                RMIMatchHandlerService m;
                  m =  remoteService.connectToMatch(nickname);
                  System.out.println(m);
                  System.out.println("//localhost:"+ CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT +"/" + m.getMatchHandlerID());
                  m.addObserver(client);
                
            }
            catch(RemoteException e) {
                System.err.println("||Impossibile collegarsi al server: " + e);
            }
            
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
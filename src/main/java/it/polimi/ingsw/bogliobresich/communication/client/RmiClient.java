package it.polimi.ingsw.bogliobresich.communication.client;

import it.polimi.ingsw.bogliobresich.communication.server.CommunicationUtil;
import it.polimi.ingsw.bogliobresich.communication.server.MatchHandler;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RMIConnectionService;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


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
            
            
            RmiClient client = new RmiClient();
            try {
                MatchHandler m;
//                m = remoteService.connectToMatch("SIMONE");
                  remoteService.connectToMatch("MATTEO");
//                System.out.println("//localhost:"+ CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT +"/" + m.getID());
//                RMIMatchHandlerService matchHandler = (RMIMatchHandlerService)  Naming.lookup("//localhost:"+ CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT +"/" + m.getID());
//                matchHandler.addObserver(client);
                
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
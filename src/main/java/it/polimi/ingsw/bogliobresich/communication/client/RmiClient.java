package it.polimi.ingsw.bogliobresich.communication.client;

import it.polimi.ingsw.bogliobresich.communication.server.CommunicationUtil;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RmiService;

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
            
            String url = "//localhost:"+ CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT +"/"+ CommunicationUtil.REMOTE_MATCH_OBJECT_NAME;
            RmiService remoteService = (RmiService) Naming.lookup(url);

            RmiClient client = new RmiClient();
            remoteService.addObserver(client);
            remoteService.connect("PIPPO é IL MIO NOME! :D");
            
            //remoteService.doAction(123);
            
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

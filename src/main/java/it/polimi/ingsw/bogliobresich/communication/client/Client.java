/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.client;

import it.polimi.ingsw.bogliobresich.communication.server.CommunicationUtil;
import it.polimi.ingsw.bogliobresich.communication.server.rmi.RmiService;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 * @author matteobresich
 *
 */
public class Client extends UnicastRemoteObject implements RemoteObserver {
    
    private static final long serialVersionUID = 1L;

    protected Client() throws RemoteException {
        super();
    }

    @Override
    public void update(Object observable, Object updateMsg)
            throws RemoteException {
        // TODO Auto-generated method stub
        
    }
    
    
    public static void main(String[] args) {
      try {
          String url = "//localhost:"+ CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT + "/RmiService";
          System.out.println(url);
          RmiService remoteService = (RmiService) Naming.lookup(url);
          Client client = new Client();
          remoteService.addObserver(client);
      } catch (Exception ex) {
          ex.printStackTrace();
      }
  }
}

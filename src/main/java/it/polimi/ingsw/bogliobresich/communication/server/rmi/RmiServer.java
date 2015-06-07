/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.client.RemoteObserver;
import it.polimi.ingsw.bogliobresich.communication.server.Server;
import it.polimi.ingsw.bogliobresich.communication.server.CommunicationUtil;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;


/**
 * @author matteobresich
 *
 */
public class RmiServer implements RmiService {

int ultimaPartitaLibera = 0;
    
    public void addObserver(RemoteObserver o) throws RemoteException {
        if(partite.elementAt(ultimaPartitaLibera).countObservers() < 2) {
        partite.elementAt(ultimaPartitaLibera).addObserver(o);
        }
        else
        {
            ultimaPartitaLibera++;
            partite.add(new TestPartita(ultimaPartitaLibera));
            partite.elementAt(ultimaPartitaLibera).addObserver(o);
        }
    }

    private static final long serialVersionUID = 1L;

    private static Vector <TestPartita> partite = new Vector <TestPartita>();
    
    public static void main(String[] args) {
        try {
            Registry rmiRegistry = LocateRegistry.createRegistry(CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT);
            RmiService rmiService = (RmiService) UnicastRemoteObject.exportObject(new RmiServer(), CommunicationUtil.RMI_REQUEST_SERVER_TCP_PORT);
            rmiRegistry.bind("RmiService", rmiService);
            System.out.println("Server ok");
            
            partite.add(new TestPartita(0));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

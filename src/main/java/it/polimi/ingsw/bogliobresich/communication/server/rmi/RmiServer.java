package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.client.RemoteObserver;
import it.polimi.ingsw.bogliobresich.communication.server.MatchHandler;
import it.polimi.ingsw.bogliobresich.communication.server.Matches;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class RmiServer extends Observable implements RmiService, Serializable {    

    /**
     * 
     */
    private static final long serialVersionUID = -5383030575590094604L;

    private class WrappedObserver implements Observer, Serializable {

        /**
         * 
         */
        private static final long serialVersionUID = 4079400357473321305L;
        
        private RemoteObserver ro = null;

        public WrappedObserver(RemoteObserver ro) {
            this.ro = ro;
        }

        @Override
        public void update(Observable o, Object arg) {
            try {
                ro.update((Serializable) o, arg);
            } catch (RemoteException e) {
                System.out.println("Remote exception removing observer:" + this);
                o.deleteObserver(this);
            }
        }

    }

    @Override
    public void addObserver(RemoteObserver o) throws RemoteException {
        WrappedObserver mo = new WrappedObserver(o);
        addObserver(mo);
        System.out.println("Added observer:" + mo);
    }

    Thread thread = new Thread() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    // ignore
                }
                setChanged();
                notifyObservers(new Date());
            }
        };
    };

    public RmiServer() {
        thread.start();
    }

    @Override
    public boolean connect(String nickname) throws RemoteException {
        return Matches.getInstance().connectUser(nickname);
    }

    @Override
    public void doAction(Player p, Action action) throws RemoteException {
        // TODO Auto-generated method stub
        
    }

    
}

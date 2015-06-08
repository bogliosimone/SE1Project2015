/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.bogliobresich.communication.client.RemoteObserver;

/**
 * @author matteobresich
 *
 */
public class RmiObservableObject extends Observable implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -6511284420101252055L;

    private class WrappedObserver implements Observer, Serializable {

        private static final long serialVersionUID = 1L;

        private RemoteObserver ro = null;
        
        public WrappedObserver(RemoteObserver ro) {
            this.ro = ro;
        }

        @Override
        public void update(Observable o, Object arg) {
            try {
                ro.update(o.toString(), arg);
            } catch (RemoteException e) {
                System.out.println("Remote exception removing observer:" + this);
                o.deleteObserver(this);
            }
        }
    }
        
    
        
    public void addObserver(RemoteObserver o) throws RemoteException {
        WrappedObserver mo = new WrappedObserver(o);
        addObserver(mo);
        System.out.println("Added observer:" + mo);
    }
        
        
    public RmiObservableObject () {
        thread.start();
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
        }
    };
}

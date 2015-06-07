/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.client.RemoteObserver;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;



/**
 * @author matteobresich
 *
 */
public class TestPartita extends Observable implements Serializable{
    
    private int IdPartita = 0;
    
    public TestPartita (int n) {
        IdPartita = n;
        thread.start();
    }

    
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
    
    
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public void addObserver(RemoteObserver o) throws RemoteException {
        if(this.countObservers() < 3) {
            WrappedObserver mo = new WrappedObserver(o);
            addObserver(mo);
            System.out.println("Partita n: " + IdPartita + " Added observer:" + mo);
        } else {
          System.out.println("Troppi osservatori!");
        }
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

}

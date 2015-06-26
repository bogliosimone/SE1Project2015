/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.notification;

import it.polimi.ingsw.bogliobresich.model.commands.Commands;

import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The <code>NotificationQueueHandler</code> implements a queue of notification.<br>
 * 
 * @author matteo bresich
 * @author simone boglio
 *  
 * @version 1.0
 * @see it.polimi.ingsw.bogliobresich.communication.notification.NotificationQueue
 * 
 */
public class NotificationQueueHandler extends Observable implements NotificationQueue, Cloneable {
    Queue <NotificationMessage> notificationQueue = new ConcurrentLinkedQueue <NotificationMessage>();
    
    public NotificationQueueHandler () {
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void addObserver(Object o) {
        addObserver((Observer)o);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public NotificationQueueHandler clone() {
        synchronized(this) {
            try {
                return (NotificationQueueHandler) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void addNotification(NotificationMessage n) {
        //System.out.println("NOTIFICA " + n.getCommand() + " AGGIUNTA IN CODA - CODA ISEMPTY: " + notificationQueue.isEmpty());
        notificationQueue.add(n);
        setChanged();
        notifyObservers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized boolean isEmpty() {
        return notificationQueue.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized Commands getNotificationCommand() {
        if(!notificationQueue.isEmpty()) {
            return notificationQueue.peek().getCommand();
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized NotificationMessage pollNotification() {
        //System.out.println("NOTIFICA " + notificationQueue.peek().getCommand() + " DA SCODARE - CODA ISEMPTY BEFORE SCODARE: " + notificationQueue.isEmpty());
        return notificationQueue.poll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void clear() {
        notificationQueue.clear();
    }

}

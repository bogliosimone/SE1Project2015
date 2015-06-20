/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;

import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author matteobresich
 *
 */
public class NotificationQueueHandler extends Observable implements NotificationQueue, Cloneable {
    Queue <NotificationMessage> notificationQueue = new ConcurrentLinkedQueue <NotificationMessage>();
    
    public NotificationQueueHandler () {
    }
    
    @Override
    public synchronized void addObserver(Object o) {
        addObserver((Observer)o);
    }
    
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
    
    
    @Override
    public synchronized void addNotification(NotificationMessage n) {
        //System.out.println("NOTIFICA " + n.getCommand() + " AGGIUNTA IN CODA - CODA ISEMPTY: " + notificationQueue.isEmpty());
        notificationQueue.add(n);
        setChanged();
        notifyObservers();
    }

    @Override
    public synchronized boolean isEmpty() {
        return notificationQueue.isEmpty();
    }

    @Override
    public synchronized Commands getNotificationCommand() {
        if(!notificationQueue.isEmpty()) {
            return notificationQueue.peek().getCommand();
        }
        return null;
    }
    
    @Override
    public synchronized NotificationMessage pollNotification() {
        //System.out.println("NOTIFICA " + notificationQueue.peek().getCommand() + " DA SCODARE - CODA ISEMPTY BEFORE SCODARE: " + notificationQueue.isEmpty());
        return notificationQueue.poll();
    }

    @Override
    public synchronized void clear() {
        notificationQueue.clear();
    }

}

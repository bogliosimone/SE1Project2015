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
    private boolean notifyAll = false;
    
    public NotificationQueueHandler () {
        this(true);
    }
    
    public NotificationQueueHandler (boolean notifyAll) {
        this.notifyAll = notifyAll;
    }
    
    @Override
    public synchronized void addObserver(Object o) {
        addObserver((Observer)o);
    }
    
    @Override
    public NotificationQueueHandler clone() {
        try {
            return (NotificationQueueHandler) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    
    
    @Override
    public synchronized void addNotification(NotificationMessage n) {
        //System.out.println("NOTIFICA " + n.getCommand() + " AGGIUNTA IN CODA - CODA ISEMPTY: " + notificationQueue.isEmpty());
        if(notifyAll) {
            notificationQueue.add(n);
            setChanged();
            notifyObservers();
        } else {
            if(isEmpty()) {
                notificationQueue.add(n);
                setChanged();
                notifyObservers();
            } else {
                notificationQueue.add(n);
            }
        }
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

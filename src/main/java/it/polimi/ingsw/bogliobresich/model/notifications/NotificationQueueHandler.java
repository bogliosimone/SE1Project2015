/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author matteobresich
 *
 */
public class NotificationQueueHandler extends Observable implements NotificationQueue {
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
    public synchronized void addNotification(NotificationMessage n) {
        if(notifyAll) {
            notificationQueue.add(n);
            setChanged();
            notifyObservers();
        } else {
            if(isEmpty()) {
                //Add the first notification in the queue
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
    public synchronized Player getPlayerArgument() {
        if(!notificationQueue.isEmpty()) {
            Object arg = notificationQueue.peek().getArgument();
            if(arg instanceof Player) {
                return (Player) arg;
            }
        }
        return null;
    }

    @Override
    public synchronized Coordinate getCoordinateArgument() {
        if(!notificationQueue.isEmpty()) {
            Object arg = notificationQueue.peek().getArgument();
            if(arg instanceof Coordinate) {
                return (Coordinate) arg;
            }
        }
        return null;
    }

    @Override
    public synchronized ItemCard getItemCardArgument() {
        if(!notificationQueue.isEmpty()) {
            Object arg = notificationQueue.peek().getArgument();
            if(arg instanceof ItemCard) {
                return (ItemCard) arg;
            }
        }
        return null;
    }

    @Override
    public synchronized String getGenericMessage() {
        if(!notificationQueue.isEmpty()) {
            Object arg = notificationQueue.peek().getArgument();
            if(arg instanceof String) {
                return (String) arg;
            }
        }
        return null;
    }

    @Override
    public synchronized NotificationMessage pollNotification() {
        return notificationQueue.poll();
    }
}

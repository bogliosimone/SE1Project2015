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
    
    @Override
    public void addObserver(Object o) {
        addObserver((Observer)o);
    }
    
    @Override
    public void addNotification(NotificationMessage n) {
        notificationQueue.add(n);
        setChanged();
        notifyObservers();
    }

    @Override
    public boolean isEmpty() {
        return notificationQueue.isEmpty();
    }

    @Override
    public Commands getNotificationCommand() {
        if(!notificationQueue.isEmpty()) {
            return notificationQueue.peek().getCommand();
        }
        return null;
    }

    @Override
    public Player getPlayerArgument() {
        if(!notificationQueue.isEmpty()) {
            Object arg = notificationQueue.peek().getArgument();
            if(arg instanceof Player) {
                return (Player) arg;
            }
        }
        return null;
    }

    @Override
    public Coordinate getCoordinateArgument() {
        if(!notificationQueue.isEmpty()) {
            Object arg = notificationQueue.peek().getArgument();
            if(arg instanceof Coordinate) {
                return (Coordinate) arg;
            }
        }
        return null;
    }

    @Override
    public ItemCard getItemCardArgument() {
        if(!notificationQueue.isEmpty()) {
            Object arg = notificationQueue.peek().getArgument();
            if(arg instanceof ItemCard) {
                return (ItemCard) arg;
            }
        }
        return null;
    }

    @Override
    public String getGenericMessage() {
        if(!notificationQueue.isEmpty()) {
            Object arg = notificationQueue.peek().getArgument();
            if(arg instanceof String) {
                return (String) arg;
            }
        }
        return null;
    }

    @Override
    public NotificationMessage pollNotification() {
        return notificationQueue.poll();
    }
}

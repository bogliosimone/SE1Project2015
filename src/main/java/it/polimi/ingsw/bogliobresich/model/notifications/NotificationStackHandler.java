/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.util.Stack;

/**
 * @author matteobresich
 *
 */
public class NotificationStackHandler implements NotificationStack {
    Stack <NotificationMessage> notificationStack = new Stack <NotificationMessage>();

    @Override
    public void pushNotification(NotificationMessage n) {
        notificationStack.push(n);
    }

    @Override
    public boolean isEmpty() {
        return notificationStack.empty();
    }

    @Override
    public Commands getNotificationCommand() {
        if(!notificationStack.empty()) {
            return notificationStack.peek().getCommand();
        }
        return null;
    }

    @Override
    public Player getPlayerArgument() {
        if(!notificationStack.empty()) {
            Object arg = notificationStack.peek().getArgument();
            if(arg instanceof Player) {
                return (Player) arg;
            }
        }
        return null;
    }

    @Override
    public Coordinate getCoordinateArgument() {
        if(!notificationStack.empty()) {
            Object arg = notificationStack.peek().getArgument();
            if(arg instanceof Coordinate) {
                return (Coordinate) arg;
            }
        }
        return null;
    }

    @Override
    public ItemCard getItemCardArgument() {
        if(!notificationStack.empty()) {
            Object arg = notificationStack.peek().getArgument();
            if(arg instanceof ItemCard) {
                return (ItemCard) arg;
            }
        }
        return null;
    }

    @Override
    public String getGenericMessage() {
        if(!notificationStack.empty()) {
            Object arg = notificationStack.peek().getArgument();
            if(arg instanceof String) {
                return (String) arg;
            }
        }
        return null;
    }

    @Override
    public NotificationMessage popNotification() {
        return notificationStack.pop();
    }
}

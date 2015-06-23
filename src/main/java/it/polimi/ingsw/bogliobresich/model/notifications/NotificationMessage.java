/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SectorCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.io.Serializable;
import java.util.List;

/**
 * @author matteobresich
 *
 */
public class NotificationMessage implements Notification<Object>, Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1316223433698919241L;
    private Commands command = null;
    private Object argument = null;
    private boolean isBroadcast = false;
    private User addressee = null;
    
    /**
     * Class constructor. The constructor create a notification message.
     * @param type indicates the type of notification
     * @param argument is the argument to send.
     * @param isBroadcast indicates if the notification is broadcast.
     * @param addressee is who will receive the notification.
     */
    public NotificationMessage(Commands type, Object argument, boolean isBroadcast, User addressee) {
        this.command = type;
        this.argument = argument;
        this.isBroadcast = isBroadcast;
        this.addressee = addressee;
    }
    
    /**
     * Class constructor. The constructor create a notification message for the server.
     * @param type indicates the type of notification
     * @param argument is the argument to send to the server
     */
    public NotificationMessage(Commands type, Object argument) {
        this(type, argument, false, null);
    }
    
    @Override
    public Commands getCommand() {
        return command;
    }

    @Override
    public Object getArgument() {
        return argument;
    }

    @Override
    public boolean isBroadcast() {
        return isBroadcast;
    }

    @Override
    public User getNotificationReciver() {
        return addressee;
    }

    @Override
    public Player getPlayer() {
        if(argument instanceof Player) {
            return (Player) argument;
        }
        return null;
    }

    @Override
    public List<User> getListOfUsers() {
        if(argument instanceof List) {
            return (List<User>) argument;
        }
        return null;
    }
    
    @Override
    public List<Player> getListOfPlayers() {
        if(argument instanceof List) {
            return (List<Player>) argument;
        }
        return null;
    }

    @Override
    public String getString() {
        if(argument instanceof String) {
            return (String) argument;
        }
        return null;
    }

    @Override
    public Coordinate getCoordinate() {
        if(argument instanceof Coordinate) {
            return (Coordinate) argument;
        }
        return null;
    }

    @Override
    public ItemCard getItemCard() {
        if(argument instanceof ItemCard) {
            return (ItemCard) argument;
        }
        return null;
    }

    @Override
    public MovesAvaiable getMovesAvaiable() {
        if(argument instanceof MovesAvaiable) {
            return (MovesAvaiable) argument;
        }
        return null;
    }

    @Override
    public User getUser() {
        if(argument instanceof User) {
            return (User) argument;
        }
        return null;
    }

    @Override
    public SectorCard getSectorCard() {
        if(argument instanceof SectorCard) {
            return (SectorCard) argument;
        }
        return null;
    }

    @Override
    public Integer getInteger() {
        if(argument instanceof Integer) {
            return (Integer) argument;
        }
        return null;
    }

}

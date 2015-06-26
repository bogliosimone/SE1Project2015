/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.notification;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SectorCard;
import it.polimi.ingsw.bogliobresich.model.commands.Commands;
import it.polimi.ingsw.bogliobresich.model.commands.MovesAvaiable;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.io.Serializable;
import java.util.List;

/**
 * This class provides a skeletal implementation of a notification queue.
 * 
 * @author matteo bresich
 * @author simone boglio
 *  
 * @version 1.0
 * 
 * @see it.polimi.ingsw.bogliobresich.communication.notification.Notification
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Commands getCommand() {
        return command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getArgument() {
        return argument;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBroadcast() {
        return isBroadcast;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getNotificationReciver() {
        return addressee;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        if(argument instanceof Player) {
            return (Player) argument;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getListOfUsers() {
        if(argument instanceof List) {
            return (List<User>) argument;
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getListOfPlayers() {
        if(argument instanceof List) {
            return (List<Player>) argument;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getString() {
        if(argument instanceof String) {
            return (String) argument;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Coordinate getCoordinate() {
        if(argument instanceof Coordinate) {
            return (Coordinate) argument;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemCard getItemCard() {
        if(argument instanceof ItemCard) {
            return (ItemCard) argument;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovesAvaiable getMovesAvaiable() {
        if(argument instanceof MovesAvaiable) {
            return (MovesAvaiable) argument;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser() {
        if(argument instanceof User) {
            return (User) argument;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SectorCard getSectorCard() {
        if(argument instanceof SectorCard) {
            return (SectorCard) argument;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getInteger() {
        if(argument instanceof Integer) {
            return (Integer) argument;
        }
        return null;
    }
}

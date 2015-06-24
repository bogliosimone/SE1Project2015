/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.CommandType;
import it.polimi.ingsw.bogliobresich.communication.client.RemoteObserver;
import it.polimi.ingsw.bogliobresich.communication.server.CommandHandler;
import it.polimi.ingsw.bogliobresich.communication.server.MatchHandler;
import it.polimi.ingsw.bogliobresich.communication.server.Server;
import it.polimi.ingsw.bogliobresich.communication.server.ServerCommunicationStrategy;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.notifications.Notification;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

/**
 * @author matteobresich
 *
 */
public class RMIMatchServiceHandler extends Observable implements RMIMatchService, Serializable, ServerCommunicationStrategy {
    
    
    /**
     * 
     */
    private static final long serialVersionUID = 5899514569944036667L;
    private transient MatchHandler matchHandler;
    
    public RMIMatchServiceHandler (MatchHandler matchHandler) {
        this.matchHandler = matchHandler;
    }
    
    private class WrappedObserver implements Observer, Serializable {

        /**
         * 
         */
        private static final long serialVersionUID = 2747416104063483478L;
        private RemoteObserver ro = null;
        private User user = null;

        public WrappedObserver(User user, RemoteObserver ro) {
            this.user = user;
            this.ro = ro;
        }

        @Override
        public void update(Observable o, Object arg) {
            try {
                if(arg instanceof Notification){
                    //TODO remove
                    
                    if(((Notification) arg).isBroadcast()) {
                        ro.update((Serializable) o, arg);
                        Server.debugMessage("BROADCAST "+((Notification) arg).getCommand() + "" + ((Notification) arg).getArgument());
                    } else if (((Notification) arg).getNotificationReciver().equals(user)) {
                        ro.update((Serializable) o, arg);
                        Server.debugMessage("USER: " + user +((Notification) arg).getCommand() + "" + ((Notification) arg).getArgument());
                    }
                    
                }
            } catch (RemoteException e) {
                Server.errorMessage("REMOTE EXCEPTION REMOVING OBSERVER:" + user);
                o.deleteObserver(this);
                //e.printStackTrace();
            }
        }
    }

    
    //--- RMI REQUESTS ---
    
    @Override
    public void addObserver(User user, RemoteObserver o) throws RemoteException {
        WrappedObserver mo = new WrappedObserver(user,o);
        addObserver(mo);
        matchHandler.addUser(user);
        Server.serviceMessage("ADDED RMI CLIENT:" + user);
    }

    @Override
    public void doAction(User user, ClientCommand command) throws RemoteException {
        if(Server.SERVER_DEBUG) {
            Server.debugMessage("Comando in arrivo: " + command.getCommandType() + " carta: " + command.getCard() + " coordinata: " + command.getCoordinate());
        }
        
        Player player = matchHandler.getPlayerByUser(user);
        if (player != null) {
            CommandHandler.executeClientCommand(matchHandler,player,command);
        }
        
    }
    
    @Override
    public String getMatchHandlerID() throws RemoteException {
        return matchHandler.getID();
    }
    
    // --- SEND TO OBSERVERS ---

    @Override
    public void sendNotification(Notification n) {
        setChanged();
        notifyObservers(n);
    }

    
}

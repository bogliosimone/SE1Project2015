/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.client.RemoteObserver;
import it.polimi.ingsw.bogliobresich.communication.notification.Notification;
import it.polimi.ingsw.bogliobresich.communication.server.CommandHandler;
import it.polimi.ingsw.bogliobresich.communication.server.MatchHandler;
import it.polimi.ingsw.bogliobresich.communication.server.Server;
import it.polimi.ingsw.bogliobresich.communication.server.ServerCommunicationStrategy;
import it.polimi.ingsw.bogliobresich.model.commands.Commands;
import it.polimi.ingsw.bogliobresich.model.match.User;
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
                    Notification notification = (Notification)arg;
                    Server.debugMessage("SENDING " + notification.getCommand());
                    if(Server.isServerNotification(notification)) {
                        if (notification.getUser().equals(user)) {
                            if(notification.getCommand().equals(Commands.USER_DISCONNECTED)) {
                                Server.serviceMessage("USER " + notification.getUser() + " DISCONNECTED: TIME-OUT");
                                o.deleteObserver(this);
                            }
                        }
                    } else {
                        if(notification.isBroadcast()) {
                            ro.update((Serializable) o, arg);
                            Server.debugMessage("BROADCAST "+ notification.getCommand() + " " + notification.getArgument());
                        } else if (notification.getNotificationReciver().equals(user)) {
                            ro.update((Serializable) o, arg);
                            Server.debugMessage("USER: " + user + notification.getCommand() + " " + notification.getArgument());
                        }
                    }
                }
            } catch (RemoteException e) {
                Server.communicationMessage("REMOTE EXCEPTION USER " + user + " UNREACHABLE");
                Server.communicationMessage("USER " + user + " DISCONNECTED");
                o.deleteObserver(this);
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

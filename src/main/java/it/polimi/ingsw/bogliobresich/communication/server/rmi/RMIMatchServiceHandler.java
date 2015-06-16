/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.ClientCommands;
import it.polimi.ingsw.bogliobresich.communication.client.RemoteObserver;
import it.polimi.ingsw.bogliobresich.communication.server.CommandHandler;
import it.polimi.ingsw.bogliobresich.communication.server.MatchHandler;
import it.polimi.ingsw.bogliobresich.communication.server.Server;
import it.polimi.ingsw.bogliobresich.communication.server.ServerCommunication;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
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
public class RMIMatchServiceHandler extends Observable implements RMIMatchService, Serializable, ServerCommunication {
    
    
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

        public WrappedObserver(RemoteObserver ro) {
            this.ro = ro;
        }

        @Override
        public void update(Observable o, Object arg) {
            try {
                ro.update((Serializable) o, arg);
            } catch (RemoteException e) {
                Server.errorMessage("REMOTE EXCEPTION REMOVING OBSERVER:" + this);
                o.deleteObserver(this);
            }
        }

        @Override
        public String toString() {
            return "WrappedObserver";
        }
    }

    
    //--- RMI REQUESTS ---
   
    //TODO PASSARE USER
    @Override
    public void addObserver(RemoteObserver o) throws RemoteException {
        WrappedObserver mo = new WrappedObserver(o);
        addObserver(mo);
        Server.serviceMessage("ADDED RMI CLIENT:" + mo);
    }

    @Override
    public void doAction(User user, ClientCommands command, Coordinate c) throws RemoteException {
        Player player = matchHandler.getPlayerByUser(user);
        if (player != null) {
            CommandHandler.executeClientCommand(matchHandler,player,command,c);
        }
    }
    
    @Override
    public String getMatchHandlerID() throws RemoteException {
        return matchHandler.getID();
    }

}

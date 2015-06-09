package it.polimi.ingsw.bogliobresich.communication.server.rmi;

import it.polimi.ingsw.bogliobresich.communication.server.MatchHandler;
import it.polimi.ingsw.bogliobresich.communication.server.MatchesHandler;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Observable;

public class RMIConnectionServer extends Observable implements RMIConnectionService, Serializable {    
    /**
     * 
     */
    private static final long serialVersionUID = -1443539127775650583L;
    private MatchesHandler matchesHandler;
    
    public RMIConnectionServer() {
        matchesHandler = MatchesHandler.getInstance();
    }

    @Override
    public MatchHandler connectToMatch(String nickname) throws RemoteException {
        if(matchesHandler == null) {
            throw new RemoteException("Match Handler not initialized!");
        }
        return matchesHandler.connectUser(nickname);
    }
}

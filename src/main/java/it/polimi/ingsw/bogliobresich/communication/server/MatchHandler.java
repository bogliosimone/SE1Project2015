/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.model.match.Match;

import java.util.ArrayList;

/**
 * @author matteobresich
 *
 */
public class MatchHandler implements Runnable {
    
    private ArrayList users;
    private Match match;
    
    public MatchHandler(ArrayList users) {
        this.users = users;
    }

    @Override
    public void run() {
        match = new Match(users);
    }

}

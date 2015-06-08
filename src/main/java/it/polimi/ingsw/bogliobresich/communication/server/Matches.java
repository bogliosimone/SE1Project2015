/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author matteobresich
 *
 */
public class Matches {
    
    private static Matches instance = null;
    ExecutorService executor = Executors.newCachedThreadPool();
    
    public static synchronized Matches getInstance() {
        if(instance == null) {
            instance = new Matches();
            return instance;
        }
        return instance; 
    }
    
    public synchronized void shutdownNow() {
        executor.shutdownNow();
    }
    
    public synchronized void addMatch() {
        User u1= new User("pippo");
        User u2= new User("pluto");
        User u3= new User("gatto");
        User u4= new User("baubau");
        List<User> users=new ArrayList<User>();
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        Match match = new Match(users);
        executor.submit(new MatchHandler(match));
        System.out.println("PARTITA " + match.toString() + " AVVIATA!");
    }
    
}

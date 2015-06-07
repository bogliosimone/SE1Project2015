/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

/**
 * @author matteobresich
 *
 */
public class MatchesHandler {
    
    private static MatchesHandler instance = null;
    
    public static synchronized MatchesHandler getInstance() {
        if(instance == null) {
            instance = new MatchesHandler();
            return instance;
        }
        return instance; 
    }
    
    //public synchronized
    //addMatch
    //ConcludiMatch che chiama remove per pulire
    //private remove
    

}

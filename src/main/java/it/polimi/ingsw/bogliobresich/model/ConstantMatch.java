/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model;

/**
 * The <code>ConstantMatch</code> class contains all constants of the match.<br>
 * 
 * @author matteo bresich
 * @author simone boglio
 *  
 * @version 1.0
 * 
 */
public class ConstantMatch {
    /**
     * the number of the turn
     */
    public static final int LASTNUMBERTURN=39;
    
    /**
     * default max player(for map galilei)
     */
    public static final int MAXPLAYERS=8;
    
    /**
     * the minimum number of players in a match
     */
    public static final int MINPLAYERS=2;
    
    /**
     * time to wait in the waiting room in seconds
     */
    public static final long TIMEWAITROOM=120000; 
    
    /**
     * time limit for the player turn in seconds
     */
    public static final long TIMETURN=120000; 
    
    /**
     * the max number of cards 
     */
    public static final int MAXCARDINHAND=4;
    
    private ConstantMatch(){
        
    }
}

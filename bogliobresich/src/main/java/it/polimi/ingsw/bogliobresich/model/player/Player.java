/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.player;

/**
 * @author Matteo
 * @author simoneboglio
 * 
 */
public class Player {

    private boolean connected;
    private boolean life;
    private boolean turnEnabled;

    /**
     * Class constructor.
     */
    public Player() {
        connected = false;
        life = true;
        turnEnabled = false;
    }

    /**
     * Return the abilitation for a turn
     *   
     */
    public boolean isYourTurn() {
        return turnEnabled;
    }

    /**
     * Return the connection state
     * 
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Return true if the player is alive
     * 
     */
    public boolean isAlive() {
        return life;
    }

}

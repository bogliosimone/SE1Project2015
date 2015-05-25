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
    private String nickName;
    
    /**
     * Class constructor.
     */
    public Player() {
        connected = false;
        life = true;
        turnEnabled = false;
    }

    /**
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return the abilitation for a turn
     *   
     */
    public boolean isYourTurn() {
        return turnEnabled;
    }

    /**
     * @return the connection state
     * 
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * @return true if the player is alive
     * 
     */
    public boolean isAlive() {
        return life;
    }

}

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

    protected boolean isConnected=true;
    protected boolean isAlive=true;
    protected boolean isYourTurn=false;
    protected boolean canAttack=false;
    protected boolean canPlayObject=true;
    protected boolean canDrawSectorCard=true;
    protected int movementStep=1;
    private String nickName;
    private int idPlayer;
    
    /**
     * Class constructor.
     * @param nickName TODO
     */
    public Player(int idPlayer,String nickName) {
        this.nickName=nickName;
        this.idPlayer=idPlayer;
        this.isConnected=true;
        this.isAlive=true;
        this.isYourTurn=false;
        this.canAttack=false;
        this.canPlayObject=true;
        this.canDrawSectorCard=true;
        this.movementStep=1;
    }

    /**
     * @return the nickName
     */
    public String getNickName() {
        return this.nickName;
    }
    /**
     * @return the id of the player
     */
    public int getIdPlayer() {
        return this.idPlayer;
    }
    
    /**
     * @return true if is the current turn of the player
     *   
     */
    public boolean isYourTurn() {
        return isYourTurn;
    }

    /**
     * @return true if the player is connect
     * 
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * @return true if the player is alive
     * 
     */
    public boolean isAlive() {
        return isAlive;
    }
    
    /**
     * @return integer number that is the current movement step of the player
     */
    public int getMovementStep() {
        return movementStep;
    }
    
    public boolean canPlayObject(){
        return this.canPlayObject;
    }
    
    public boolean canAttack(){
        return this.canPlayObject;
    }
    
    public boolean canDrawSectorCard(){
        return this.canPlayObject;
    }

}

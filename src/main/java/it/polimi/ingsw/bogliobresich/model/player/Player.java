/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.player;

import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

/**
 * @author simoneboglio
 * 
 */
public class Player {

    protected boolean isWinner=false;
    protected boolean isConnected=true;
    protected boolean isAlive=true;
    protected boolean isYourTurn=false;
    protected boolean canAttack=false;
    protected boolean canPlayObject=true;
    protected boolean canDrawSectorCard=true;
    protected int movementStep=1;
    protected String nickName;
    protected int idPlayer;
    protected ItemHand hand;
    protected static final int MAXCARDSINHAND=5;
    protected Coordinate coordinate;
    protected CharacterCard characterCard;
    
    /**
     * Class constructor.
     */
    public Player(int idPlayer,String nickName,Coordinate coordinate,CharacterCard characterCard) {
        this.nickName=nickName;
        this.idPlayer=idPlayer;
        this.coordinate=coordinate;
        this.isConnected=true;
        this.isAlive=true;
        this.isYourTurn=false;
        this.canAttack=false;
        this.canPlayObject=true;
        this.canDrawSectorCard=true;
        this.movementStep=1;
        this.hand = new ItemHand(MAXCARDSINHAND);
        this.characterCard=characterCard;
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
    
    public boolean canPlayTurn(){
        if(this.isAlive()&&this.isConnected()&&!this.isWinner())
            return true;
        return false;
    }
    
    public boolean isWinner() {
        return isWinner;
    }

    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    /**
     * @return true if is the current turn of the player
     *   
     */
    public boolean isYourTurn() {
        return isYourTurn;
    }

    public void setIsYourTurn(boolean isYourTurn) {
        this.isYourTurn=isYourTurn;
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
    
    public void SetIsAlive(boolean isAlive) {
        this.isAlive=isAlive;
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
        return this.canAttack;
    }
    
    public boolean canDrawSectorCard(){
        return this.canDrawSectorCard;
    }
    
    public ItemHand getHand(){
        return this.hand;
    }   
    
    public void setCoordinate(Coordinate coord){
        this.coordinate=coord;
    }
    public Coordinate getCoordinate(){
        return this.coordinate;
    }
}

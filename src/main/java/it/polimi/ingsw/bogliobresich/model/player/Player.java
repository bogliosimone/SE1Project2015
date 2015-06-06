/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.player;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;

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
    protected String nickName;
    protected int idPlayer;
    protected ItemHand hand;
    protected static final int MAXCARDSINHAND=5;
    
    /**
     * Class constructor.
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
        this.hand = new ItemHand(MAXCARDSINHAND);
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
    
    public boolean itemCardIsInHand(ItemCard card){
        return this.hand.cardIsIn(card);
    }
    
    public boolean handIsFull(){
        return this.hand.isFull();
    }
    
    public boolean removeCardInHand(ItemCard card){
        return this.hand.removeCard(card);
    }
    
    public boolean addCardInHand(ItemCard card){
        return this.hand.addCard(card);
    }   
}

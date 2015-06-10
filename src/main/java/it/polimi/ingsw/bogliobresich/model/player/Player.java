/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.player;

import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.ConstantMatch;
import it.polimi.ingsw.bogliobresich.model.match.User;

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
    protected ItemHand hand;
    protected Coordinate coordinate;
    protected CharacterCard characterCard;
    protected User user;
    
    /**
     * Class constructor.
     */
    public Player(User user,Coordinate coordinate,CharacterCard characterCard) {
        this.user = user;
        this.coordinate=coordinate;
        this.isConnected=true;
        this.isAlive=true;
        this.isYourTurn=false;
        this.canAttack=false;
        this.canPlayObject=true;
        this.canDrawSectorCard=true;
        this.movementStep=1;
        this.hand = new ItemHand(ConstantMatch.MAXCARDINHAND);
        this.characterCard=characterCard;
    }

    /**
     * @return the nickName
     */
    public String getNickName() {
        return this.user.getNickname();
    }
    /**
     * @return the id of the player
     */
    public int getIdPlayer() {
        return this.user.getId();
    }
    
    public User getUser(){
        return this.user;
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
    
    public void setIsConnected(boolean connection) {
        this.isConnected=connection;    
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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Player other = (Player) obj;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

}

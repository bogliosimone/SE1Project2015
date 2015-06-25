/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.player;

import java.io.Serializable;

import it.polimi.ingsw.bogliobresich.model.ConstantMatch;
import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;

/**
 * class that represent player of the game, by default can't attack, can't play object,  can draw sector card and move by 1 step
 * this class is serializable
 * @author simoneboglio
 * 
 */
public class Player implements Serializable {

    /**
     * serial version id
     */
    private static final long serialVersionUID = -8383698783989595474L;
    /**
     * true if player win the game
     */
    protected boolean isWinner=false;
    /**
     * true if player is connected
     */
    protected boolean isConnected=true;
    /**
     * true if player is alive
     */
    protected boolean isAlive=true;
    /**
     * true if is his turn
     */
    protected boolean isYourTurn=false;
    /**
     * true if he can attack
     */
    protected boolean canAttack=false;
    /**
     * true if he can play object
     */
    protected boolean canPlayObject=true;
    /**
     * true if he can draw sector card
     */
    protected boolean canDrawSectorCard=true;
    /**
     * number of movement step
     */
    protected int movementStep=1;
    /**
     * item card hand 
     */
    protected ItemHand hand;
    /**
     * coordinate of actual position of the player in the map
     */
    protected Coordinate coordinate;
    /**
     * character of the player
     */
    protected CharacterCard characterCard;
    /**
     * info of the player
     */
    protected User user;

    /**
     * create a player
     * @param user info of the player
     * @param coordinate where the player is locate in the map
     * @param characterCard character that he play in a game
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
     * get nickname of the player
     * @return string with nickname
     */
    public String getNickName() {
        return this.user.getNickname();
    }

    /**
     * get id of the player
     * @return number of the player
     */
    public int getIdPlayer() {
        return this.user.getId();
    }

    /**
     * get user
     * @return user of the player
     */
    public User getUser(){
        return this.user;
    }

    /**
     * true if he can play a turn, he is alive connected and no winner
     * @return true if player can play a turn
     */
    public boolean canPlayTurn(){
        if(this.isAlive()&&this.isConnected()&&!this.isWinner())
            return true;
        return false;
    }

    /**
     * true if player win
     * @return true if player win
     */
    public boolean isWinner() {
        return isWinner;
    }

    /**
     * set true if player win
     * @param isWinner true if player win
     */
    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    /**
     * true if is the current turn of the player
     * @return true if is the current turn of the player  
     */
    public boolean isYourTurn() {
        return isYourTurn;
    }

    /**
     * set true if is the current player turn
     * @param isYourTurn true if is his current turn
     */
    public void setIsYourTurn(boolean isYourTurn) {
        this.isYourTurn=isYourTurn;
    }

    /**
     * true if player is connected
     * @return true if the player is connect
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * true if the player is connected
     * @param connection true if player is connected
     */
    public void setIsConnected(boolean connection) {
        this.isConnected=connection;    
    }

    /**
     * true if player is alive false instead
     * @return true if player is alive
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * true if player is alive
     * @param isAlive true if player is alive
     */
    public void SetIsAlive(boolean isAlive) {
        this.isAlive=isAlive;
    }

    /**
     * get the number of movement step of the player
     * @return number of movement step 
     */
    public int getMovementStep() {
        return movementStep;
    }

    /**
     * true if player can play item card
     * @return true if player can play card
     */
    public boolean canPlayObject(){
        return this.canPlayObject;
    }

    /**
     * true if player can attack
     * @return true if player can  attack
     */
    public boolean canAttack(){
        return this.canAttack;
    }

    /**
     * true if player can draw sector card
     * @return true if player can draw sector card
     */
    public boolean canDrawSectorCard(){
        return this.canDrawSectorCard;
    }

    /**
     * get the hand of player
     * @return hand of the player
     */
    public ItemHand getHand(){
        return this.hand;
    }   

    /**
     * set the actual coordinate of the player
     * @param coord where you want set the player
     */
    public void setCoordinate(Coordinate coord){
        this.coordinate=coord;
    }
    /**
     * return the actual coordinate of the player
     * @return coordinate 
     */
    public Coordinate getCoordinate(){
        return this.coordinate;
    }

    /**
     * get character card of the player
     * @return character card
     */
    public CharacterCard getCharacterCard() {
        return characterCard;
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

    @Override
    public String toString(){
        return new String("Natura: "+this.characterCard.getCharacterType()+" ; Personaggio: "+ this.characterCard.getCharacterName()  +" ; Coordinate: "+this.coordinate);
    }

}

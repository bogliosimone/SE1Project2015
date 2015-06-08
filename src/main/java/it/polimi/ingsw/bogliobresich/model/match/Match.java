/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.map.HexMap;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.ActionListUser;
import it.polimi.ingsw.bogliobresich.model.match.state.InitState;
import it.polimi.ingsw.bogliobresich.model.match.state.State;
import it.polimi.ingsw.bogliobresich.model.player.AlienPlayer;
import it.polimi.ingsw.bogliobresich.model.player.HumanPlayer;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */

public class Match {
    private State myState;
    private int idMatch;
    private boolean isActive=true;
    private boolean isEnd=false;
    private boolean IsLastPlayerKill=false; //true  when player kill, false when player escape
    private int currentTurn=0;
    private Player currentPlayer;
    private Deque<Player> players = new LinkedList<Player>();
    private List<Player> arrayPlayers = new ArrayList<Player>();
    private HexMap gameMap=new HexMap();
    private int numberOfPlayers;
    private Deck itemDeck;
    private Deck characterDeck;
    private Deck portholeDeck;
    private Deck sectorDeck;

    public Match(List<User> users){
        setState(new InitState());
        Action action = new ActionListUser(users);
        myState.doAction(this, null , action);
    }

    public int getIdMatch(){
        return this.idMatch;
    }
    
    public int getCurrentTurn(){
        return this.currentTurn;
    }
    
    public int getNumberOfPlayers(){
        return this.numberOfPlayers;
    }
    
    public void setCurrentTurn(int turnNumber){
        this.currentTurn=turnNumber;
    }
    
    public void setNumberOfPlayers(int numberOfPlayers){
        this.numberOfPlayers=numberOfPlayers;
    }
    
    public boolean isActive(){
        return this.isActive;
    }
    
    public boolean isEnd() {
        return isEnd;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public boolean isLastPlayerKill() {
        return IsLastPlayerKill;
    }

    public void setIsLastPlayerKill(boolean IsLastPlayerKill) {
        this.IsLastPlayerKill = IsLastPlayerKill;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public HexMap getGameMap(){
        return this.gameMap;
    }
    public Deck getSectorDeck() {
        return sectorDeck;
    }

    public void setSectorDeck(Deck sectorDeck) {
        this.sectorDeck = sectorDeck;
    }

    public Deck getPortholeDeck() {
        return portholeDeck;
    }

    public void setPortholeDeck(Deck portholeDeck) {
        this.portholeDeck = portholeDeck;
    }

    public Deck getCharacterDeck() {
        return characterDeck;
    }

    public void setCharacterDeck(Deck characterDeck) {
        this.characterDeck = characterDeck;
    }

    public Deck getItemDeck() {
        return itemDeck;
    }

    public void setItemDeck(Deck itemDeck) {
        this.itemDeck = itemDeck;
    }
    
    public void addPlayer(Player player){
        this.players.add(player);
        this.arrayPlayers.add(player);
    }
    
    public List<Player> getAllPlayer(){
        return this.arrayPlayers;
    }
    
    public boolean isLastHumanKill(){
        if(this.IsLastPlayerKill&& !this.atLeastOneHumaAlive())
            return true;
        return false;
    }
    
    public boolean atLeastOneHumaAliveNoWinner(){
        for(Player tmpPlayer:arrayPlayers){
            if((tmpPlayer instanceof HumanPlayer)&& tmpPlayer.isAlive() && !tmpPlayer.isWinner())
                return true;
        }
        return false;
    }
    
    public boolean atLeastOneHumaAlive(){
        for(Player tmpPlayer:arrayPlayers){
            if((tmpPlayer instanceof HumanPlayer)&& tmpPlayer.isAlive())
                return true;
        }
        return false;
    }
    
    public boolean atLeastOneHumanCanPlay(){
        for(Player tmpPlayer:arrayPlayers){
            if((tmpPlayer instanceof HumanPlayer)&& tmpPlayer.canPlayTurn())
                return true;
        }
        return false;
    }
    
    public boolean AtLeastOnePlayerCanPlay(){
        for(Player tmpPlayer:arrayPlayers){
            if(tmpPlayer.canPlayTurn())
                return true;
        }
        return false;
    }
    
    public boolean isLastTurn(){
        if(this.getCurrentTurn()==ConstantMatch.LASTNUMBERTURN){
            int start= arrayPlayers.indexOf(this.currentPlayer);
            for(int i=start+1;i<this.numberOfPlayers;i++){
                Player tmpPlayer=arrayPlayers.get(i);
                if(tmpPlayer.canPlayTurn())
                    return true;
            }
        }
        return false;
    }
    
    public boolean thereIsAnotherTurn(){
        if(this.atLeastOneHumanCanPlay() && !this.isLastTurn() && this.AtLeastOnePlayerCanPlay()){
            return true;
        }
        return false;
    }
    
    public Player getNextPlayer(Player currentPlayer){
        if(currentPlayer==null || currentPlayer.equals(players.peekLast()))
            return players.peekFirst();
        else
            return players.peek();
        
    }
    
    public boolean isLastPlayer(Player currentPlayer){
        if(currentPlayer==players.peekLast())
            return true;
        else
            return false;
        }
    
    public boolean playerIsAlien(Player player){
        return player instanceof AlienPlayer;
    }
    public boolean playerIsHuman(Player player){
        return player instanceof HumanPlayer;
    }
    
    public void setState(State newState){
        this.myState = newState;
    }
    
    public void doAction(Player player, Action action){
        myState.doAction(this,player,action);
    }
    
    public void notifyAllPlayer(String notification){
        System.out.println("Broadcast message: "+notification);
    }
    
    public void notifyPlayer(Player player, String notification){
        System.out.println("Player "+player.getNickName()+": "+notification);
    }
    
    public void serviceMessage(String message ){
        System.out.println("Service Message: "+message);
    }

    
}

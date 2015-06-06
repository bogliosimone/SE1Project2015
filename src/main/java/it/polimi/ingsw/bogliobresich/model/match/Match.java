/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.map.HexMap;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.ActionListUser;
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
    private int currentTurn=0;
    private List<Player> players = new ArrayList<Player>();
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
    
    public void setCurrentTurn(int turnNumber){
        this.currentTurn=turnNumber;
    }
    
    public void setNumberOfPlayers(int numberOfPlayers){
        this.numberOfPlayers=numberOfPlayers;
    }
    
    public boolean isActive(){
        return this.isActive;
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
        players.add(player);
    }
    
    public boolean isValidMoove(Player player, Coordinate start, Coordinate end){ //da spostare
        boolean validMove;
        validMove=gameMap.isValidMove(start, end, player.getMovementStep());
        if(validMove && playerIsAlien(player) && gameMap.coordinateIsPortholeSector(end))
            validMove = false; //alien can't go in porthole sector
        return validMove;
    }
    
    public boolean playerIsAlien(Player player){
        return player instanceof AlienPlayer;
    }
    public boolean playerIsHuman(Player player){
        return player instanceof HumanPlayer;
    }
    
    void setState(State newState){
        this.myState = newState;
    }
    
    public void doAction(Player player, Action action){
        myState.doAction(this,player,action);
    }
    
    public void notifyAllPlayer(String notification){
        System.out.println("Broadcast message: "+notification);
    }
    
    public void notifyPlayer(int idPlayer, String notification){
        System.out.println("Player "+idPlayer+": "+notification);
    }
    
    public void serviceMessage(String message ){
        System.out.println("Service Message: "+message);
    }

    
}

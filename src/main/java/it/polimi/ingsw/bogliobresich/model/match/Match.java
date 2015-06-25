/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

import it.polimi.ingsw.bogliobresich.model.ConstantMatch;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.map.HexMap;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.state.State;
import it.polimi.ingsw.bogliobresich.model.match.state.WaitRoomState;
import it.polimi.ingsw.bogliobresich.model.match.timer.TimerWaitEndTurn;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationQueue;
import it.polimi.ingsw.bogliobresich.model.player.AlienPlayer;
import it.polimi.ingsw.bogliobresich.model.player.HumanPlayer;
import it.polimi.ingsw.bogliobresich.model.player.ItemHand;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 * @author matteo bresich
 */

public class Match {
    /**
     * used for enable message for debug
     */
    private boolean CLIenable=false;
    /**
     * current state of the match (for pattern state)
     */
    private State myState;
    /**
     *  id of the match
     */
    private int idMatch;
    /**
     * signal if the game is started, is true when game start after reach max number of player or timer wait room end, can't add other player
     */
    private boolean isActive=false;
    /**
     * signal if the game is ended or not
     */
    private boolean isEnd=false;
    /**
     * for calculate winners and losers (alien lost if last player escape)
     */
    private boolean isLastPlayerKill=false; //true  when player kill, false when player escape, used for calculate victory of alien
    /**
     * number of the current turn in the game
     */
    private int currentTurn=0;
    /**
     * current player that play turn
     */
    private Player currentPlayer;
    /**
     * index of current player
     */
    private int indexCurrentPlayer=0;
    /**
     * list used for calculate next player that play after a turn is end
     */
    private List<Player> players = new LinkedList<Player>();
    /**
     * list of players used for operations and control in match
     */
    private List<Player> arrayPlayers = new ArrayList<Player>();
    /**
     * game map structure of the game
     */
    private HexMap gameMap;
    /**
     * name of default map
     */
    private String nameFileMap="galilei.txt";
    /**
     * number of current player in the game
     */
    private int numberOfPlayers=0;
    /**
     * item card deck 
     */
    private Deck itemDeck;
    /**
     * character card deck
     */
    private Deck characterDeck;
    /**
     * porthole card deck
     */
    private Deck portholeDeck;
    /**
     * sector card deck
     */
    private Deck sectorDeck;
    /**
     * timer for wait room, when time is up game start (if min player <= number of current player <=max player)
     */
    private Timer timerWaitRoom;
    /**
     * number of max player for the game, default 8 for map galilei
     */
    private int maxNumberPlayer=8;
    /**
     * timer for disconnect player for inactivity
     */
    TimerWaitEndTurn timerClass;
    /**
     * queue used for notify the players
     */
    NotificationQueue notificationQueue;
    
    /**
     * use galilei as default map
     * @param idMatch number id match
     * @param queue used for notification
     */
    public Match(int idMatch,NotificationQueue queue){
        this.notificationQueue=queue;
        this.idMatch=idMatch;
        gameMap=new HexMap();
        setState(new WaitRoomState());
    }
    
    /**
     * you can set different map and number of max player for the map used 
     * @param idMatch number 
     * @param queue used for notification
     * @param nameFileMap file name of the map
     * @param maxNumberPlayer number of max player
     */
    public Match(int idMatch,NotificationQueue queue,String nameFileMap,int maxNumberPlayer){
        this.notificationQueue=queue;
        this.idMatch=idMatch;
        this.nameFileMap=nameFileMap;
        this.maxNumberPlayer=maxNumberPlayer;
        gameMap=new HexMap(this.nameFileMap);
        setState(new WaitRoomState());
    }
    
    /**
     * start the timer for disconnect player for inactivity when his turn start
     * @param currentPlayer
     */
    public void startTimerTurn(Player currentPlayer){
        timerWaitRoom = new Timer();
        timerClass = new TimerWaitEndTurn(this,currentPlayer);
        this.timerWaitRoom.schedule(timerClass, ConstantMatch.TIMETURN);
    }
    
    public void stopTimer(){
        this.timerWaitRoom.cancel();
    }
    

    public int getIdMatch(){
        return this.idMatch;
    }
    
    public String getNameFileMap(){
        return this.nameFileMap;
    }
    
    public int getMaxNumberPlayer() {
        return maxNumberPlayer;
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
    
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public boolean isEnd() {
        return isEnd;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public boolean isLastPlayerKill() {
        return isLastPlayerKill;
    }

    public void setIsLastPlayerKill(boolean IsLastPlayerKill) {
        this.isLastPlayerKill = IsLastPlayerKill;
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
    
    public boolean isCLIenable() {
        return CLIenable;
    }

    public void setIsCLIenable(boolean CLIenable) {
        this.CLIenable = CLIenable;
    }

    public void addPlayer(Player player){
        if(this.players.isEmpty()){
            this.numberOfPlayers=0;
        }
        this.players.add(this.numberOfPlayers,player);
        this.numberOfPlayers++;
        this.arrayPlayers.add(player); //array for check score and other in game
    }
    
    public List<Player> getAllPlayer(){
        return this.arrayPlayers;
    }
    
    public boolean atLeastOnePorthole(){
        return this.gameMap.thereArePortholeActive();
    }
    
    public boolean isLastHumanKill(){
        if(this.isLastPlayerKill&& !this.atLeastOneHumaAlive())
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
            boolean findPlayer=false;
            int start= arrayPlayers.indexOf(this.currentPlayer);
            for(int i=start+1;i<this.numberOfPlayers&&!findPlayer;i++){
                Player tmpPlayer=arrayPlayers.get(i);
                if(tmpPlayer.canPlayTurn())
                    findPlayer= true;
            }
            if(findPlayer)
                return false;
            else
                return true;
        }
        return false;
    }
    
    public boolean thereIsAnotherTurn(){
        if(this.atLeastOneHumanCanPlay() && !this.isLastTurn() && this.atLeastOnePorthole()){
            return true;
        }
        return false;
    }
    
    public Player getNextPlayer(){
        if(this.indexCurrentPlayer>=(this.numberOfPlayers-1)||(this.indexCurrentPlayer==0 && this.currentTurn==0)){
            this.currentTurn++;
            this.indexCurrentPlayer=0;
            return players.get(0);
        }
        this.indexCurrentPlayer++;
        return players.get(indexCurrentPlayer);
    }
    
    public boolean isLastPlayer(Player currentPlayer){
        if(currentPlayer.equals(players.get(this.numberOfPlayers-1)))
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
    
    public State getState(){
        return this.myState;
    }
    
    public void doAction(Player player, Action action){
        if(player==null || player.equals(this.currentPlayer))
            myState.doAction(this,player,action);
        else{
            this.notifyPlayer(player, "Non è il tuo turno");
            this.notifyPlayer(Commands.IS_NOT_YOUR_TURN, null, player);
        }
    }
    
    public void notifyAllPlayer(String notification){
        if(this.CLIenable)
            System.out.println("Broadcast message: "+notification);
    }
    
    public void notifyPlayer(Player player, String notification){
        if(this.CLIenable)
            System.out.println("Player "+player.getNickName()+": "+notification);
    }
    
    public void serviceMessage(String message ){
        if(this.CLIenable)
            System.out.println("Service Message: "+message);
    }
    

    public ItemCard playItemCard(Player player,ItemCard card){
        if(player.canPlayObject()){
            ItemHand hand=player.getHand();
            if(hand.cardIsIn(card)){
                hand.removeCard(card);
                card=card.play(this, player);
                this.itemDeck.discardCard(card); //scarta nel mazzo
                return card;
            }
        }
        return null;
    }
    
    public void discardItemHandInItemDeck(Player player){
        ItemHand tmpHand = player.getHand();
        if(!tmpHand.isEmpty()){
            List<ItemCard> cardList = tmpHand.getAllCard();
            this.serviceMessage("Scartata mano di "+player.getNickName());
            for(ItemCard tmpCard: cardList){
                this.serviceMessage(tmpCard.toString());
            }
            for(ItemCard tmpCard: cardList){
                this.itemDeck.discardCard(tmpCard);
            }
            tmpHand.discardHand();
        }
        return;
    }
    
    public boolean discardItemCardInItemDeck(Player player,ItemCard cardToDiscard){
        ItemHand tmpHand = player.getHand();
        if(tmpHand.cardIsIn(cardToDiscard)){
            tmpHand.removeCard(cardToDiscard);
            this.itemDeck.discardCard(cardToDiscard);
            return true;
        }
        return false;
    }
    
    public void notifyAllPlayer(Commands command, Object argument){
        if(this.notificationQueue!=null)
            notificationQueue.addNotification(new NotificationMessage(command,argument, true, null));
    }
    
    public void serviceMessage(Commands command, Object argument){
        if(this.notificationQueue!=null)
            notificationQueue.addNotification(new NotificationMessage(command,argument));
    }
    
    public void notifyPlayer(Commands command, Object argument, Player player){
        if(this.notificationQueue!=null)
            notificationQueue.addNotification(new NotificationMessage(command,argument, false, player.getUser()));
    }
    
    public void notifyUser(Commands command, Object argument, User user){
        if(this.notificationQueue!=null)
            notificationQueue.addNotification(new NotificationMessage(command,argument, false, user));
    }
    public void notifyAllUser(Commands command, Object argument){
        if(this.notificationQueue!=null)
            notificationQueue.addNotification(new NotificationMessage(command,argument, true, null));
    }
}

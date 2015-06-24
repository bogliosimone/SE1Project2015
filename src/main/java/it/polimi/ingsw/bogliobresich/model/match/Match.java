/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

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
 *
 */

public class Match {
    private boolean CLIenable=true;
    private State myState;
    private int idMatch;
    private boolean isActive=false;
    private boolean isEnd=false;
    private boolean IsLastPlayerKill=false; //true  when player kill, false when player escape, used for calculate victory of alien
    private int currentTurn=0;
    private Player currentPlayer;
    private int indexCurrentPlayer=0;
    private List<Player> players = new LinkedList<Player>();
    private List<Player> arrayPlayers = new ArrayList<Player>();
    private HexMap gameMap=new HexMap();
    private String nameFileMap="galilei.txt";
    private int numberOfPlayers=0;
    private Deck itemDeck;
    private Deck characterDeck;
    private Deck portholeDeck;
    private Deck sectorDeck;
    private Timer timerWaitRoom;
    TimerWaitEndTurn timerClass;
    NotificationQueue notificationQueue;
    
    public Match(NotificationQueue queue){
        this.notificationQueue=queue;
        setState(new WaitRoomState());
    }
    
    public Match(int idMatch,NotificationQueue queue){
        this(queue);
        this.idMatch=idMatch;
    }
    
    public Match(int idMatch,NotificationQueue queue,String nameFileMap){
        this(queue);
        this.idMatch=idMatch;
        this.nameFileMap=nameFileMap;
        gameMap=new HexMap(this.nameFileMap);
    }
    
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

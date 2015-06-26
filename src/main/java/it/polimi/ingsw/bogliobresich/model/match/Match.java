/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

import it.polimi.ingsw.bogliobresich.communication.notification.NotificationMessage;
import it.polimi.ingsw.bogliobresich.communication.notification.NotificationQueue;
import it.polimi.ingsw.bogliobresich.model.ConstantMatch;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.commands.Commands;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.map.HexMap;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.state.State;
import it.polimi.ingsw.bogliobresich.model.match.state.WaitRoomState;
import it.polimi.ingsw.bogliobresich.model.match.timer.TimerWaitEndTurn;
import it.polimi.ingsw.bogliobresich.model.player.AlienPlayer;
import it.polimi.ingsw.bogliobresich.model.player.HumanPlayer;
import it.polimi.ingsw.bogliobresich.model.player.ItemHand;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * class handler for all the data used in the game, hexMap, all deck, all card, player
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
    private Timer timerEndTurn;
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
        timerEndTurn = new Timer();
        timerClass = new TimerWaitEndTurn(this,currentPlayer);
        this.timerEndTurn.schedule(timerClass, ConstantMatch.TIMETURN);
    }
    
    /**
     * stop timer end turn when player end his turn before time out
     */
    public void stopTimer(){
        this.timerEndTurn.cancel();
    }
    

    /**
     * get number id match
     * @return int number id match
     */
    public int getIdMatch(){
        return this.idMatch;
    }
    
    /**
     * get name of the file map
     * @return string name file map
     */
    public String getNameFileMap(){
        return this.nameFileMap;
    }
    
    /**
     * get number of max players
     * @return max number of players
     */
    public int getMaxNumberPlayer() {
        return maxNumberPlayer;
    }

    /**
     * get current turn of the game
     * @return current turn of the game
     */
    public int getCurrentTurn(){
        return this.currentTurn;
    }
    
    /**
     * get current number of players in the game
     * @return current number of players
     */
    public int getNumberOfPlayers(){
        return this.numberOfPlayers;
    }
    
    /**
     * set current turn
     * @param turnNumber number of current turn
     */
    public void setCurrentTurn(int turnNumber){
        this.currentTurn=turnNumber;
    }
    
    /**
     * set number of players
     * @param numberOfPlayers number of players
     */
    public void setNumberOfPlayers(int numberOfPlayers){
        this.numberOfPlayers=numberOfPlayers;
    }
    
    /**
     * return true if the game is started
     * @return true if game is started
     */
    public boolean isActive(){
        return this.isActive;
    }
    
    /**
     * set the game status
     * @param isActive true if game is read for start
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    /**
     * return true if game is end, false instead
     * @return true if game is ended
     */
    public boolean isEnd() {
        return isEnd;
    }

    /**
     * set the game status
     * @param isEnd true when game is end
     */
    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    /**
     * get last player kill status, used for calculate winners
     * @return true if last player human was killed, false if last player human escaped
     */
    public boolean isLastPlayerKill() {
        return isLastPlayerKill;
    }

    /**
     * set true if last player human was killed, false if last player human escaped
     * @param IsLastPlayerKill true if last human was killed
     */
    public void setIsLastPlayerKill(boolean IsLastPlayerKill) {
        this.isLastPlayerKill = IsLastPlayerKill;
    }

    /**
     * get the current player that play turn
     * @return current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * set the current player that play turn
     * @param currentPlayer that need to play turn
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * get the game map (for calculate valid move and neighbors)
     * @return the hex game map
     */
    public HexMap getGameMap(){
        return this.gameMap;
    }
    /**
     * get the sector card deck
     * @return deck of sector card
     */
    public Deck getSectorDeck() {
        return sectorDeck;
    }

    /**
     * set the sector card deck used in the game
     * @param sectorDeck used in the game
     */
    public void setSectorDeck(Deck sectorDeck) {
        this.sectorDeck = sectorDeck;
    }

    /**
     * get porthole card deck
     * @return deck of porthole card
     */
    public Deck getPortholeDeck() {
        return portholeDeck;
    }

    /**
     * set the porthole card used in the game
     * @param portholeDeck used in the game
     */
    public void setPortholeDeck(Deck portholeDeck) {
        this.portholeDeck = portholeDeck;
    }

    /**
     * get the character card deck used in the game
     * @return deck of character card
     */
    public Deck getCharacterDeck() {
        return characterDeck;
    }

    /**
     * set the character card deck used in the game
     * @param characterDeck used in the game 
     */
    public void setCharacterDeck(Deck characterDeck) {
        this.characterDeck = characterDeck;
    }

    /**
     * get the item card deck used in the game
     * @return deck of item card
     */
    public Deck getItemDeck() {
        return itemDeck;
    }

    /**
     * set the item card deck used in the game
     * @param itemDeck used in the game
     */
    public void setItemDeck(Deck itemDeck) {
        this.itemDeck = itemDeck;
    }
    
    /**
     * used for know if the cli for debug is enable
     * @return
     */
    public boolean isCLIenable() {
        return CLIenable;
    }

    /**
     * set for enable the CLI debug (default not enable)
     * @param CLIenable true if you want debug
     */
    public void setIsCLIenable(boolean CLIenable) {
        this.CLIenable = CLIenable;
    }

    /**
     * add player in the game
     * @param player that join the game
     */
    public void addPlayer(Player player){
        if(this.players.isEmpty()){
            this.numberOfPlayers=0;
        }
        this.players.add(this.numberOfPlayers,player);
        this.numberOfPlayers++;
        this.arrayPlayers.add(player); //array for check score and other in game
    }
    
    /**
     * list of all players for do control during game
     * @return list of all players
     */
    public List<Player> getAllPlayer(){
        return this.arrayPlayers;
    }
    
    /**
     * if at least one porthole is active return true (used for end or continue the game)
     * @return true if there is at least one porthole active
     */
    public boolean atLeastOnePorthole(){
        return this.gameMap.thereArePortholeActive();
    }
    
    /**
     * used for calculate winners 
     * @return true if alien win because last player was killed and there isn't another human alive
     */
    public boolean isLastHumanKill(){
        if(this.isLastPlayerKill&& !this.atLeastOneHumaAlive())
            return true;
        return false;
    }
    
    /**
     * used for continue or end game when a turn end
     * @return true if there is at least one human alive and he don't win the game before
     */
    public boolean atLeastOneHumaAliveNoWinner(){
        for(Player tmpPlayer:arrayPlayers){
            if((tmpPlayer instanceof HumanPlayer)&& tmpPlayer.isAlive() && !tmpPlayer.isWinner())
                return true;
        }
        return false;
    }
    
    /**
     * used for continue or end game when a turn end
     * @return true if there is at least one human that can play 
     */
    public boolean atLeastOneHumaAlive(){
        for(Player tmpPlayer:arrayPlayers){
            if((tmpPlayer instanceof HumanPlayer)&& tmpPlayer.isAlive())
                return true;
        }
        return false;
    }
      
    /**
     * used for continue or end game when a turn end
     * @return true if there is at least one human that can play 
     */
    public boolean atLeastOneHumanCanPlay(){
        for(Player tmpPlayer:arrayPlayers){
            if((tmpPlayer instanceof HumanPlayer)&& tmpPlayer.canPlayTurn())
                return true;
        }
        return false;
    }
    
    /**
     * used for continue or end game when a turn end
     * @return true if there is at least one human that can play 
     */
    public boolean AtLeastOnePlayerCanPlay(){
        for(Player tmpPlayer:arrayPlayers){
            if(tmpPlayer.canPlayTurn())
                return true;
        }
        return false;
    }
    
    /**
     * used for continue or end game when a turn end
     * @return true if there is the last turn
     */
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
    
    /**
     * used for continue or end game when a turn end
     * @return true if there is another turn to play
     */
    public boolean thereIsAnotherTurn(){
        if(this.atLeastOneHumanCanPlay() && !this.isLastTurn() && this.atLeastOnePorthole()){
            return true;
        }
        return false;
    }
    
    /**
     * get the next player when start new turn
     * @return next player of the game
     */
    public Player getNextPlayer(){
        if(this.indexCurrentPlayer>=(this.numberOfPlayers-1)||(this.indexCurrentPlayer==0 && this.currentTurn==0)){
            this.currentTurn++;
            this.indexCurrentPlayer=0;
            return players.get(0);
        }
        this.indexCurrentPlayer++;
        return players.get(indexCurrentPlayer);
    }
    
    /**
     * return true if the player is the last player of the players
     * @param currentPlayer that play turn
     * @return true if he is last player
     */
    public boolean isLastPlayer(Player currentPlayer){
        if(currentPlayer.equals(players.get(this.numberOfPlayers-1)))
            return true;
        else
            return false;
        }
    
    /**
     * return true if the player is an alien
     * @param player that you want know nature
     * @return true if he is an alien
     */
    public boolean playerIsAlien(Player player){
        return player instanceof AlienPlayer;
    }
    
    /**
     * return true if the player is an human
     * @param player that you want know nature
     * @return true if he is an human
     */
    public boolean playerIsHuman(Player player){
        return player instanceof HumanPlayer;
    }
    
    /**
     * set the current state of the game (pattern state)
     * @param newState of the match
     */
    public void setState(State newState){
        this.myState = newState;
    }
    
    /**
     * get the current state of the match
     * @return the current state of the match
     */
    public State getState(){
        return this.myState;
    }
    
    /**
     * action that player want do or the server want do (for example add user when game is not started)
     * @param player that do action
     * @param action that player want do
     */
    public void doAction(Player player, Action action){
        if(player==null || player.equals(this.currentPlayer))
            myState.doAction(this,player,action);
        else{
            this.notifyPlayer(player, "Non è il tuo turno");
            this.notifyPlayer(Commands.IS_NOT_YOUR_TURN, null, player);
        }
    }
    
    /**
     * print the notification if CLI is enable (for debug)
     * @param notification that you want print
     */
    public void notifyAllPlayer(String notification){
        if(this.CLIenable)
            System.out.println("Broadcast message: "+notification);
    }
    
    /**
     * print the notification if CLI is enable (for debug)
     * @param player that receive notify
     * @param notification that you want print
     */
    public void notifyPlayer(Player player, String notification){
        if(this.CLIenable)
            System.out.println("Player "+player.getNickName()+": "+notification);
    }
    
    /**
     * print the notification if CLI is enable (for debug)
     * @param message that you want print
     */
    public void serviceMessage(String message ){
        if(this.CLIenable)
            System.out.println("Service Message: "+message);
    }
    

    /**
     * play item card and discard the card in item deck and remove from player hand
     * @param player that play card
     * @param card that he want play
     * @return the card with update info
     */
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
    
    /**
     * discard all hand of a player in the item deck 
     * @param player that you want discard all hand
     */
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
    
    /**
     * discard item card in item deck and remove the card from hand of player
     * @param player that discard card
     * @param cardToDiscard to discard
     * @return
     */
    public boolean discardItemCardInItemDeck(Player player,ItemCard cardToDiscard){
        ItemHand tmpHand = player.getHand();
        if(tmpHand.cardIsIn(cardToDiscard)){
            tmpHand.removeCard(cardToDiscard);
            this.itemDeck.discardCard(cardToDiscard);
            return true;
        }
        return false;
    }
    
    /**
     * add in the queue a command notification for all player with object
     * @param command of notification
     * @param argument object of the notification
     */
    public void notifyAllPlayer(Commands command, Object argument){
        if(this.notificationQueue!=null)
            notificationQueue.addNotification(new NotificationMessage(command,argument, true, null));
    }
    
    /**
     * add in the queue a command notification for the handler of the game with object
     * @param command of notification
     * @param argument object of the notification
     */
    public void serviceMessage(Commands command, Object argument){
        if(this.notificationQueue!=null)
            notificationQueue.addNotification(new NotificationMessage(command,argument));
    }
    
    /**
     * add in the queue a command notification for a player with object
     * @param command of notification
     * @param argument object of the notification
     * @param player receiver of the message
     */
    public void notifyPlayer(Commands command, Object argument, Player player){
        if(this.notificationQueue!=null)
            notificationQueue.addNotification(new NotificationMessage(command,argument, false, player.getUser()));
    }
    
    /**
     * add in the queue a command notification for an user with object (used when user don't already know is character)
     * @param command of notification
     * @param argument object of the notification
     * @param user receiver of the message
     */
    public void notifyUser(Commands command, Object argument, User user){
        if(this.notificationQueue!=null)
            notificationQueue.addNotification(new NotificationMessage(command,argument, false, user));
    }
    /**
     * add in the queue a command notification for all user with object(used when user don't already know is character)
     * @param command of notification
     * @param argument object of the notification
     */
    public void notifyAllUser(Commands command, Object argument){
        if(this.notificationQueue!=null)
            notificationQueue.addNotification(new NotificationMessage(command,argument, true, null));
    }
}

/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;
import it.polimi.ingsw.bogliobresich.model.Characters;
import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * @author matteobresich
 *
 */
public class WaitingRoom {
    
    private int nMaxPlayers = 0;
    private Vector <User> users;
    TimerTask timerTask = new WaitingRoomTimer(this);
    Timer waitingTimer = null;
    
    /**
     * Class constructor
     */
    public WaitingRoom () {
        this(8);
    }
    
    /**
     * Class constructor
     * @param nMaxPlayers is the maximum number of player that the room can contains
     */
    public WaitingRoom (int nMaxPlayers) {
        this.nMaxPlayers = nMaxPlayers;
        users = new Vector<User>();
    }
    
    /**
     * Add a player into the room
     * @param p Player to add
     * @throws WaitingRoomFullException when the room reach the maximum number of player that can be contained in the room
     */
    public synchronized void addUser(User p) throws WaitingRoomFullException {
        if(!isFull()) {
            users.add(p);
            if(isMatchStartable()) {
                waitingTimerStart();
            }
        }
        else throw new WaitingRoomFullException();
    }
    
    /**
     * Return if the room is full of players
     * @return true if the room contains the maximum number of player that the room can contains 
     */
    public synchronized boolean isFull() {
        if (users.size() >= nMaxPlayers) {
            return true;
        }
        return false;
    }
    
    /**
     * Return a list of players 
     * @return list of players that are in the room
     */
    public synchronized List <User> getPlayerList() {
        return vectorToArrayList(users);
    }
    
    private static ArrayList vectorToArrayList(Vector vector){
        if (vector == null){return null;}
        return new ArrayList<Object>(vector);
    }
    
    private boolean isMatchStartable() {
        if(users.size() >= CommunicationUtil.N_MIN_PLAYERS_TO_START) {
            return true;
        }
      return false;
    }
    
    private void waitingTimerStart() {
        if (waitingTimer == null) {
            waitingTimer = new Timer(true);
            waitingTimer.scheduleAtFixedRate(timerTask, CommunicationUtil.WAITING_TIMER_START_SCHEDULE, CommunicationUtil.WAITING_TIMER_RESCHEDULE_EVERY_X_MILLISECONDS);
            System.out.println("TimerTask started");
        }
    }
    
    
    public void initMatch() {
        waitingTimer.cancel();
        System.out.println("TimerTask cancelled");
        System.out.println("INIZZIALIZZO LA PARTITA con " + users.size() + " giocatori");
        Matches.getInstance().addMatch(users);
    }
    
    public static void main(String args[]) {
        WaitingRoom room = new WaitingRoom();
        try {
            room.addUser(new User("u1"));
            room.addUser(new User("u2"));
        
        }
        catch( Exception e ) { System.out.println("Errore! potrebbe essere Stanza full!"); e.printStackTrace();}
        
        try {
          Thread.sleep(120000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
    }
}



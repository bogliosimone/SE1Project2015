package it.polimi.ingsw.bogliobresich.model.match;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.bogliobresich.model.ConstantMatch;
import it.polimi.ingsw.bogliobresich.model.match.action.AddPlayerAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MatchStartTest {

    private User[] users = new User[ConstantMatch.MAXPLAYERS];
    
    @Test
    public void testMatchNotStart() {
        Match match = new Match(1,null);
        for(int i = 0; i < ConstantMatch.MINPLAYERS; i++) {
            users[i] = new User(i,"utente"+i,"");
            match.doAction(null, new AddPlayerAction(users[i]));
        }
        assertFalse(match.isActive());
    }
    
    @Test
    public void testMatchStart() {
        Match match = new Match(1,null);
        for(int i = 0; i < ConstantMatch.MAXPLAYERS; i++) {
            users[i] = new User(i,"utente"+i,"");
            match.doAction(null, new AddPlayerAction(users[i]));
        }
        assertTrue(match.isActive());
    }
    
    @Test
    public void testMatchCreateAllPlayers() {
        Match match = new Match(1,null);
        List <User> userList = new ArrayList<User>();
        for(int i = 0; i < ConstantMatch.MAXPLAYERS; i++) {
            User u = new User(i,"utente"+i,"");
            userList.add(u);
            match.doAction(null, new AddPlayerAction(u));
        }
        
        List<Player> playerList = match.getAllPlayer();
        assertEquals(userList.size(),playerList.size());
        
        for(Player p : playerList) {
            assertTrue(userList.contains(p.getUser()));
        }
    }
    
    @Test
    public void testTheHighlanderVictory() {
//        List<Player> players = match.getAllPlayer();
//        for(Player p : players) {
//            match.setState(new EndTurnState());
//            match.doAction(match.getCurrentPlayer(), new TimerEndTurnAction());
//        }
//        assertTrue(match.isEnd());
    }
}

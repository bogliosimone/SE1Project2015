package it.polimi.ingsw.bogliobresich.model.match;

import it.polimi.ingsw.bogliobresich.model.ConstantMatch;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.AddPlayerAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class MatchTestUtil {
    
    public static String randomString() {
        SecureRandom randomGenerator = new SecureRandom();
        return new BigInteger(130, randomGenerator).toString(32);
    }
    
    public static List<User> generateUsers() {
        List <User> list = new ArrayList<User>();
        for(int i = 0; i < ConstantMatch.MAXPLAYERS; i++) {
            list.add(new User(i,randomString(),randomString()));
        }
        return list;
    }
    
    public static void initMatch(Match match, List <User> userList) {
        for(User u: userList) {
            match.doAction(null, new AddPlayerAction(u));
        }
    }
    
    public static void doActions(Match match, Player player, List <Action> actions) {
        for(Action action : actions) {
            match.doAction(player, action);
        }
    }
}

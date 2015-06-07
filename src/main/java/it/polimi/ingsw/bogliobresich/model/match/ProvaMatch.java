package it.polimi.ingsw.bogliobresich.model.match;

import it.polimi.ingsw.bogliobresich.model.cards.TeleportItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.match.action.RumorCoordinate;

import java.util.ArrayList;
import java.util.List;

public class ProvaMatch {

    public static void main(String[] args) {
        User u1= new User("pippo");
        User u2= new User("pluto");
        User u3= new User("gatto");
        User u4= new User("baubau");
        List<User> users=new ArrayList<User>();
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        Match match = new Match(users);
        match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('L',5)));
        match.doAction(match.getCurrentPlayer(), new PlayItemAction(new TeleportItemCard(1)));
        match.doAction(match.getCurrentPlayer(), new RumorCoordinate(new Coordinate('C',4)));
    }
}

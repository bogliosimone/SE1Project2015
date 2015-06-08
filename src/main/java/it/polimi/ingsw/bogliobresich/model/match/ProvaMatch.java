package it.polimi.ingsw.bogliobresich.model.match;

import it.polimi.ingsw.bogliobresich.model.cards.TeleportItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.action.AttackAction;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawSectorAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndTurnAction;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.match.action.RumorCoordinate;
import it.polimi.ingsw.bogliobresich.model.player.HumanPlayer;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class ProvaMatch {

    public static void main(String[] args) {
        User u1= new User("pippo");
        User u2= new User("pluto");
        User u3= new User("gatto");
        //User u4= new User("baubau");
        List<User> users=new ArrayList<User>();
        users.add(u1);
        users.add(u2);
        users.add(u3);
        //users.add(u4);
        Match match = new Match(users);
        match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('L',5)));
        //match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('K',8)));
        match.doAction(match.getCurrentPlayer(), new AttackAction());
        //match.doAction(match.getCurrentPlayer(), new DrawSectorAction());
        //match.doAction(match.getCurrentPlayer(), new RumorCoordinate(new Coordinate('C',4)));
        //match.doAction(match.getCurrentPlayer(), new PlayItemAction(new TeleportItemCard(1)));
        List<Player> lista =match.getAllPlayer();
        for(Player p:lista)
            if(p instanceof HumanPlayer)
                p.SetIsAlive(false);
        match.doAction(match.getCurrentPlayer(), new EndTurnAction());
    }
}

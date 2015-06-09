package it.polimi.ingsw.bogliobresich.model.match;

import it.polimi.ingsw.bogliobresich.model.cards.DefenceItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.TeleportItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.action.AddPlayerAction;
import it.polimi.ingsw.bogliobresich.model.match.action.AttackAction;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawSectorAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndTurnAction;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.match.action.RumorCoordinate;
import it.polimi.ingsw.bogliobresich.model.player.ItemHand;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class ProvaMatch {

    public static void main(String[] args) {
        User u1= new User("Pippo");
        User u2= new User("Pluto");
        User u3= new User("Paperino");
        User u4= new User("Topolino");
        ItemCard card=new DefenceItemCard(1);
        Match match = new Match();
        match.setIsCLIenable(true);
        match.doAction(null, new AddPlayerAction(u1));
        match.doAction(null, new AddPlayerAction(u2));
        match.doAction(null, new AddPlayerAction(u3));
        match.doAction(null, new AddPlayerAction(u4));
        List<Player> lp=match.getAllPlayer();
        
        Player p2=lp.get(2);
        p2.setCoordinate(new Coordinate('L',5));
        p2.getHand().addCard(card);
        //ItemHand h1 = p1.getHand();
        //h1.addCard(card);
        //match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('L',5)));
        match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('K',8)));
        //match.doAction(match.getCurrentPlayer(), new AttackAction());
        match.doAction(match.getCurrentPlayer(), new DrawSectorAction());
        match.doAction(match.getCurrentPlayer(), new RumorCoordinate(new Coordinate('C',4)));
        //match.doAction(match.getCurrentPlayer(), new PlayItemAction(card));
        match.doAction(match.getCurrentPlayer(), new EndTurnAction());
    }
}

package it.polimi.ingsw.bogliobresich.model.match;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SpotlightItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.action.AddPlayerAction;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawSectorAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndTurnAction;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.match.action.RumorCoordinate;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.util.List;

public class ProvaMatch {

    public static void main(String[] args) {
        User u1= new User(1,"Pippo","");
        User u2= new User(2,"Pluto","");
        User u3= new User(3,"Paperino","");
        User u4= new User(4,"Topolino","");
        User u5= new User(5,"Pippo","");
        User u6= new User(6,"Pluto","");
        User u7= new User(7,"Paperino","");
        User u8= new User(8,"Topolino","");
        ItemCard card=new SpotlightItemCard(1);
        ((SpotlightItemCard)card).setCoordToLight(new Coordinate ('L',8));
        Match match = new Match(1,null);
        match.setIsCLIenable(true);
        match.doAction(null, new AddPlayerAction(u1));
        match.doAction(null, new AddPlayerAction(u2));
        match.doAction(null, new AddPlayerAction(u3));
        match.doAction(null, new AddPlayerAction(u4));
        match.doAction(null, new AddPlayerAction(u5));
        match.doAction(null, new AddPlayerAction(u6));
        match.doAction(null, new AddPlayerAction(u7));
        match.doAction(null, new AddPlayerAction(u8));
        List<Player> lp=match.getAllPlayer();
        
        
        
        Player p1=lp.get(0);
        //p2.setCoordinate(new Coordinate('L',5));
        //p2.getHand().addCard(card);
        p1.getHand().addCard(card);
        //match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('L',5)));
        match.doAction(match.getCurrentPlayer(), new PlayItemAction(card));
        //match.doAction(match.getCurrentPlayer(), new SpotlightCoordinateAction(new Coordinate ('L',8)));
        match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('K',8)));
        //match.doAction(match.getCurrentPlayer(), new AttackAction());
        match.doAction(match.getCurrentPlayer(), new DrawSectorAction());
        match.doAction(match.getCurrentPlayer(), new RumorCoordinate(new Coordinate('C',4)));
        //match.doAction(match.getCurrentPlayer(), new DiscardAction(card));
        //match.doAction(match.getCurrentPlayer(), new PlayItemAction(card));
        match.doAction(match.getCurrentPlayer(), new EndTurnAction());
    }
}

package it.polimi.ingsw.bogliobresich.model.match;

import it.polimi.ingsw.bogliobresich.model.cards.TeleportItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.action.AddPlayerAction;
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
        User u1= new User("Pippo");
        User u2= new User("Pluto");
        User u3= new User("Paperino");
        User u4= new User("Topolino");
        Match match = new Match();
        match.doAction(null, new AddPlayerAction(u1));
        match.doAction(null, new AddPlayerAction(u2));
        match.doAction(null, new AddPlayerAction(u3));
        match.doAction(null, new AddPlayerAction(u4));
        //match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('L',5)));
        match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('K',8)));
        //match.doAction(match.getCurrentPlayer(), new AttackAction());
        //match.doAction(match.getCurrentPlayer(), new DrawSectorAction());
        //match.doAction(match.getCurrentPlayer(), new RumorCoordinate(new Coordinate('C',4)));
        //match.doAction(match.getCurrentPlayer(), new PlayItemAction(new TeleportItemCard(1)));
        match.doAction(match.getCurrentPlayer(), new EndTurnAction());
    }
}

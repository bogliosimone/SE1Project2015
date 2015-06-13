package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.AttackAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.match.action.SafeSectorAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

public class SafeSectorPhaseTurnState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(player==null){
            match.serviceMessage("Comando non valido");
            return;
        }
        
        if(action instanceof SafeSectorAction){
            match.notifyPlayer(player, "Gioca carta, attacca o concludi fase di movimento");
            return;
        }
        
        if(action instanceof PlayItemAction){
            ItemCard card=((PlayItemAction) action).getItemCard();
            if(card.isPlayableMovePhase()&&player.canPlayObject()){
                card = match.playItemCard(player, card);
                if(card!=null){
                    match.notifyAllPlayer("ha giocato la carta: "+card.toString());
                    return;
                }
            }
            match.notifyPlayer(player, "Non puoi giocare questa carta");
            return; 
        }
        
        if(action instanceof EndPhaseAction){
            match.setState(new EndPhaseTurnState()); 
            match.doAction(player, action);
            return;
        }
        if(action instanceof AttackAction && player.canAttack()){
            match.setState(new AttackPhaseTurnState()); 
            match.doAction(player, action);
            return;
        }
        
        match.notifyPlayer(player, "Mossa non consentita durante SafeSectorPhase");
        return;
    }

}

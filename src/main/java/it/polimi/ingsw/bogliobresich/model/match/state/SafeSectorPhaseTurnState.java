package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.commands.Commands;
import it.polimi.ingsw.bogliobresich.model.commands.MovesAvaiable;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.AttackAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.match.action.SafeSectorAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * state when a player go in a safe sector
 * @author simoneboglio
 *
 */
public class SafeSectorPhaseTurnState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(player==null){
            match.serviceMessage("Comando non valido");
            match.serviceMessage(Commands.GENERIC_ERROR,"Azione non disponibile in fase di endPhase turn");
            return;
        }
        
        if(action instanceof SafeSectorAction){
            match.notifyPlayer(player, "Gioca carta, attacca o concludi fase di movimento");
            match.notifyPlayer(Commands.MOVES_AVAIABLE, currentMoves(match,player),player);
            return;
        }
        
        if(action instanceof PlayItemAction){
            ItemCard card=((PlayItemAction) action).getItemCard();
            if(card.isPlayableMovePhase()&&player.canPlayObject()){
                card = match.playItemCard(player, card);
                if(card!=null){
                    match.notifyAllPlayer("ha giocato la carta: "+card.toString());
                    match.notifyAllPlayer(Commands.ITEM_PLAYED, player.getNickName()+" ha giocato la carta: "+card.getName()+card.getInfo());
                    match.notifyPlayer(Commands.DISCARD_CARD, card, player);
                    match.notifyPlayer(Commands.MOVES_AVAIABLE, currentMoves(match,player),player);
                    return;
                }
            }
            match.notifyPlayer(player, "Non puoi giocare questa carta");
            match.notifyPlayer(Commands.CANT_PLAY_CARD, null, player);
            match.notifyPlayer(Commands.MOVES_AVAIABLE, currentMoves(match,player),player);
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
        match.notifyPlayer(Commands.MOVE_NO_AVAIABLE, null, player);
        match.notifyPlayer(Commands.MOVES_AVAIABLE, currentMoves(match,player),player);
        return;
    }
    
    
    
    private MovesAvaiable currentMoves(Match match,Player player){
        MovesAvaiable move=new MovesAvaiable();
        if(player.canAttack())
            move.setCanAttack(true);
        if(player.canPlayObject())
            move.setCanPlayItem(true);
        move.setCanGoInEndPhase(true);
        return move;
    }

}

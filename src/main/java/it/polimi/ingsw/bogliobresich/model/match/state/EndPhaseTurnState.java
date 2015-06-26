/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.commands.Commands;
import it.polimi.ingsw.bogliobresich.model.commands.MovesAvaiable;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndTurnAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * state when player go in his end phase
 * @author simoneboglio
 *
 */
public class EndPhaseTurnState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(player == null){
            match.serviceMessage("Comando non valido");
            match.serviceMessage(Commands.GENERIC_ERROR,"Azione non disponibile in fase di endPhase turn");
            return;
        }
        
        if(action instanceof EndPhaseAction){
            match.notifyPlayer(Commands.START_END_PHASE,null,player);
            match.notifyPlayer(Commands.MOVES_AVAIABLE, currentMoves(match,player),player);
            match.notifyPlayer(player, "Gioca carta o concludi turno");
            return;
        }
        
        if(action instanceof EndTurnAction){
            match.setState(new EndTurnState()); 
            match.doAction(player, action);
            return;
        }
        
        if(action instanceof PlayItemAction &&player.canPlayObject()){
            ItemCard card=((PlayItemAction) action).getItemCard();
            if(card.isPlayableEndPhase()&&player.canPlayObject()){
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
        match.notifyPlayer(player,"Mossa non disponibile nella end phase del turno");
        match.notifyPlayer(Commands.MOVE_NO_AVAIABLE, null, player);
        match.notifyPlayer(Commands.MOVES_AVAIABLE, currentMoves(match,player),player);
        return;
    }
    
    
    private MovesAvaiable currentMoves(Match match,Player player){
        MovesAvaiable move=new MovesAvaiable();
        if(player.canPlayObject())
            move.setCanPlayItem(true);
        move.setCanEndTurn(true);
        return move;
    }

}

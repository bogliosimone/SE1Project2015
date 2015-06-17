/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.AttackAction;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawSectorAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.match.action.UnsafeSectorAction;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.notifications.MovesAvaiable;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class UnsafeSectorPhaseTurnState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(player==null){
            match.serviceMessage(Commands.GENERIC_ERROR,"Azione non disponibile in fase di UnsafeSectorPhase");
            match.serviceMessage("Comando non valido");
            return;
        }

        if(action instanceof UnsafeSectorAction){
            match.notifyPlayer(player, "Attacchi, giochi un oggetto, concludi movimento o peschi una carta settore?");
            match.notifyPlayer(Commands.MOVES_AVAIABLE, currentMoves(match,player),player);
            return;
        }

        if(action instanceof AttackAction && player.canAttack()){
            match.setState(new AttackPhaseTurnState()); 
            match.doAction(player, action);
            return;
        }

        if(action instanceof DrawSectorAction && player.canDrawSectorCard()){
            match.setState(new DrawSectorPhaseState());
            match.doAction(player, action);
            return;
        }    

        if(action instanceof PlayItemAction && player.canPlayObject()){
            ItemCard card=((PlayItemAction) action).getItemCard();
            if(card.isPlayableMovePhase()&&player.canPlayObject()){
                card = match.playItemCard(player, card);
                if(card!=null){
                    match.notifyAllPlayer("ha giocato la carta: "+card.toString());
                    match.notifyAllPlayer(Commands.ITEM_PLAYED, player+" ha giocato la carta: "+card.toString());
                    match.notifyPlayer(Commands.DISCARD_CARD, card, player);
                    match.notifyPlayer(Commands.MOVES_AVAIABLE, currentMoves(match,player),player);
                    return;
                }
            }
            match.notifyPlayer(player, "Non puoi giocare questa carta");
            match.notifyPlayer(Commands.CANT_PLAY_CARD, null, player);
            return; 
        }

        if(action instanceof EndPhaseAction && !player.canDrawSectorCard()){
            match.setState(new EndPhaseTurnState()); 
            match.doAction(player, action);
            return;
        }

        match.notifyPlayer(player, "Mossa non consentita durante UnsafeSectorPhase");
        match.notifyPlayer(Commands.MOVE_NO_AVAIABLE, null, player);
        match.notifyPlayer(Commands.MOVES_AVAIABLE, currentMoves(match,player),player);
    }

    
    private MovesAvaiable currentMoves(Match match,Player player){
        MovesAvaiable move=new MovesAvaiable();
        if(player.canAttack())
            move.setCanAttack(true);
        if(player.canPlayObject())
            move.setCanPlayItem(true);
        if(!player.canDrawSectorCard())
            move.setCanGoInEndPhase(true);
        move.setCanDrawSectorCard(true);
        return move;
    }
}

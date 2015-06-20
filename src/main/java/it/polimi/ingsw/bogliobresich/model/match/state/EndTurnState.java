/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.ActionStartTurn;
import it.polimi.ingsw.bogliobresich.model.match.action.EndGameAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndTurnAction;
import it.polimi.ingsw.bogliobresich.model.match.action.TimerEndTurnAction;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.player.HumanPlayer;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class EndTurnState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(player == null){
            match.serviceMessage("Comando non valido");
            match.serviceMessage(Commands.GENERIC_ERROR,"Azione non disponibile in fase di fine turno");
            return;
        }
        
        if(action instanceof EndTurnAction){
           player.setIsYourTurn(false);
           match.notifyPlayer(player, "è finito il tuo turno");
           match.stopTimer();
           match.notifyPlayer(Commands.END_TURN, null,player);
           match.notifyAllPlayer(Commands.USER_END_TURN, player.getUser());
           if(player instanceof HumanPlayer)
               ((HumanPlayer) player).resetHumanPlayerAbility();
           if(match.thereIsAnotherTurn()){
               match.setState(new StartTurnState());
               match.doAction(null, new ActionStartTurn());
           }
           else{
               match.setState(new EndState());
               match.doAction(null, new EndGameAction());
           }
           return;
        }
        if(action instanceof TimerEndTurnAction){
            Player dcPlayer=match.getCurrentPlayer();
            dcPlayer.setIsConnected(false);
            match.notifyAllPlayer(dcPlayer.getNickName()+" si è disconnesso dal gioco");
            match.notifyAllPlayer(Commands.GAME_INFO_MESSAGE, dcPlayer.getNickName()+" si è disconnesso dal gioco");
            match.notifyPlayer(Commands.USER_END_IS_GAME, null, dcPlayer);
            match.notifyPlayer(dcPlayer, "Ti sei disconnesso");
            match.notifyPlayer(Commands.YOU_DISCONNECTED,null,dcPlayer);
            match.setState(new EndTurnState()); 
            match.doAction(match.getCurrentPlayer(), new EndTurnAction());
            return;
        }
        
        match.notifyPlayer(player,"Azione non disponibile nella fase finale del turno");
        match.notifyPlayer(Commands.MOVE_NO_AVAIABLE, null, player);
        return;

    }

}

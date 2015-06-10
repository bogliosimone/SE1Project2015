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
import it.polimi.ingsw.bogliobresich.model.player.HumanPlayer;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class EndTurnState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(action instanceof EndTurnAction){
           match.notifyPlayer(player, "è finito il tuo turno");
           match.stopTimer();
           if(player instanceof HumanPlayer)
               ((HumanPlayer) player).resetHumanPlayerAbility();
           if(match.thereIsAnotherTurn()){
               match.setState(new StartTurnState());
               match.doAction(null, new ActionStartTurn());
           }
           else{
               match.setState(new EndState());
               match.doAction(player, new EndGameAction());
           }
           return;
        }
        if(action instanceof TimerEndTurnAction){
            Player dcPlayer=match.getCurrentPlayer();
            dcPlayer.setIsConnected(false);
            match.notifyAllPlayer(dcPlayer.getNickName()+" si è disconnesso dal gioco");
            match.setState(new EndTurnState()); 
            match.doAction(match.getCurrentPlayer(), new EndTurnAction());
            return;
        }
        match.serviceMessage("Mossa non disponibile nella fase finale del turno");
        return;

    }

}

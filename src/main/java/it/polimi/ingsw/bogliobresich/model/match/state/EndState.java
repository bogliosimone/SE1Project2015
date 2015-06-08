/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import java.util.List;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.EndGameAction;
import it.polimi.ingsw.bogliobresich.model.player.HumanPlayer;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class EndState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        // TODO Auto-generated method stub
        if(action instanceof EndGameAction){
            match.notifyAllPlayer("La partita è finita");
            calculateWinners(match);
            match.setIsEnd(true);
            return;
        }
        match.serviceMessage("Mossa non disponibile, la partita è finita");
        return;
    }
    
    public void calculateWinners(Match match){
        if(match.atLeastOneHumaAliveNoWinner()||match.isLastHumanKill())
            match.notifyAllPlayer("Gli alieni hanno vinto");
        else
            match.notifyAllPlayer("Gli alieni hanno perso");
        List<Player> tmpPlayers = match.getAllPlayer();
        for(Player tmpPlayer:tmpPlayers){
            if(tmpPlayer instanceof HumanPlayer){
                if(tmpPlayer instanceof HumanPlayer&& tmpPlayer.isWinner())
                    match.notifyAllPlayer("L'umano "+tmpPlayer.getNickName()+" ha vinto");  
                else
                    match.notifyAllPlayer("L'umano "+tmpPlayer.getNickName()+" ha perso");  
            }
        }
        return;
    }

}

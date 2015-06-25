/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import java.util.List;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.EndGameAction;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.player.AlienPlayer;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * state when game is end, calculate winners losers and after end
 * @author simoneboglio
 *
 */
public class EndState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        
        if(action instanceof EndGameAction && player==null){
            match.notifyAllPlayer("La partita è finita");
            match.notifyAllPlayer(Commands.GENERIC_MESSAGE, "La partita è finita");
            calculateWinners(match);
            match.setIsEnd(true);
            match.notifyAllPlayer(Commands.GAME_END,null);
            match.serviceMessage(Commands.GAME_END, "Game id: "+match.getIdMatch()+" terminato");
            return;
        }
        match.serviceMessage("Mossa non disponibile, la partita è finita");
        match.serviceMessage(Commands.GENERIC_ERROR,"Azione non disponibile in fase di inizializzazione del gioco");
        return;
    }
    
    public void calculateWinners(Match match){
        boolean alienWin=false;
        if(match.atLeastOneHumaAliveNoWinner()||match.isLastHumanKill())
            alienWin=true;
        List<Player> tmpPlayers = match.getAllPlayer();
        for(Player tmpPlayer:tmpPlayers)
            if(alienWin && tmpPlayer instanceof AlienPlayer)
                tmpPlayer.setIsWinner(true);
        match.notifyAllPlayer(Commands.LIST_PLAYERS_END_GAME, tmpPlayers);
        /*for(Player tmpPlayer:tmpPlayers){
            if(tmpPlayer instanceof HumanPlayer){
                if(tmpPlayer instanceof HumanPlayer&& tmpPlayer.isWinner()){
                    match.notifyAllPlayer("L'umano "+tmpPlayer.getNickName()+" ha vinto");
                    match.notifyAllPlayer(Commands.GENERIC_MESSAGE,"L'umano "+tmpPlayer.getNickName()+" ha vinto");
                    tmpPlayer.setIsWinner(true);
                }
                else{
                    match.notifyAllPlayer("L'umano "+tmpPlayer.getNickName()+" ha perso");
                    match.notifyAllPlayer(Commands.GENERIC_MESSAGE,"L'umano "+tmpPlayer.getNickName()+" ha perso");
                    tmpPlayer.setIsWinner(false);
                }
            }
            else{
                if(alienWin){
                    match.notifyAllPlayer("L'alieno "+tmpPlayer.getNickName()+" ha vinto");
                    match.notifyAllPlayer(Commands.GENERIC_MESSAGE,"L'alieno "+tmpPlayer.getNickName()+" ha vinto");
                    tmpPlayer.setIsWinner(true);
                }
                else{
                    match.notifyAllPlayer("L'alieno "+tmpPlayer.getNickName()+" ha perso");
                    match.notifyAllPlayer(Commands.GENERIC_MESSAGE,"L'alieno "+tmpPlayer.getNickName()+" ha perso");
                    tmpPlayer.setIsWinner(true);
                }
            }
                
        }*/
        for(Player tmpPlayer:tmpPlayers){
            if(tmpPlayer.isWinner())
                match.notifyPlayer(Commands.YOU_WIN, null, tmpPlayer);
            else
                match.notifyPlayer(Commands.YOU_LOST, null, tmpPlayer);
        }
        return;
    }

}

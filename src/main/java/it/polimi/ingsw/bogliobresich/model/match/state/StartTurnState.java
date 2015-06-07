package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.ActionStartTurn;
import it.polimi.ingsw.bogliobresich.model.player.Player;

public class StartTurnState implements State {
    @Override
    public void doAction(Match match,Player prevPlayer, Action action){
        if(action instanceof ActionStartTurn){
            Player currentPlayer;
            int count=0;
            do{
                currentPlayer=match.getNextPlayer(prevPlayer);
                count++;
            }while(!currentPlayer.isAlive() || !currentPlayer.isConnected() || count<match.getNumberOfPlayers());
            match.notifyPlayer(currentPlayer, "tocca a te  - turno: "+match.getCurrentTurn());
        }
        else
            match.serviceMessage("Mossa non disponibile ad inizio turno");
    }
}

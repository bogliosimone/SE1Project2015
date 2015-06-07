package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.ActionStartTurn;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

public class StartTurnState implements State {
    @Override
    public void doAction(Match match,Player player, Action action){
        if(action instanceof ActionStartTurn){
            Player currentPlayer;
            int count=0;
            do{
                currentPlayer=match.getNextPlayer(player);
                count++;
            }while(!currentPlayer.isAlive() || !currentPlayer.isConnected() || count<match.getNumberOfPlayers());
            currentPlayer.setIsYourTurn(true);
            match.notifyPlayer(currentPlayer, "è il tuo turno  - turno: "+match.getCurrentTurn());
            match.setCurrentPlayer(currentPlayer);
            //fai partire timer
            return;
        }
        if(!player.equals(match.getCurrentPlayer())){
            match.notifyPlayer(player, "Non è il tuo turno");
            return;
        }
        if(action instanceof MovementAction){
            match.setState(new MovementState());
            match.doAction(player, (MovementAction) action);
            return;
        }
        if(action instanceof PlayItemAction){
            ItemCard card=((PlayItemAction) action).getItemCard();
            
        }
        match.serviceMessage("Mossa non disponibile ad inizio turno");
        return;
    }
}

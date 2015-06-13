package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.map.HexMap;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.ActionStartTurn;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PortholeAction;
import it.polimi.ingsw.bogliobresich.model.match.action.SafeSectorAction;
import it.polimi.ingsw.bogliobresich.model.match.action.UnsafeSectorAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

public class StartTurnState implements State {
    @Override
    public void doAction(Match match,Player player, Action action){
        
        if(action instanceof ActionStartTurn && player==null){
            Player currentPlayer;
            do{
                currentPlayer=match.getNextPlayer();
            }while(!currentPlayer.canPlayTurn());
            currentPlayer.setIsYourTurn(true);
            match.notifyPlayer(currentPlayer, "è il tuo turno  - turno: "+match.getCurrentTurn());
            match.notifyPlayer(currentPlayer, "Vuoi giocare un oggetto o vuoi muoverti?");
            match.setCurrentPlayer(currentPlayer);
            match.startTimerTurn();
            return;
        }
        
        if(player==null){
            match.serviceMessage("Comando non valido");
            return;
        }

        if(action instanceof MovementAction){
            Coordinate endCoord=((MovementAction) action).getCoordinate();
            Coordinate startCoord=player.getCoordinate();
            if(isValidMoove(match,player,startCoord,endCoord)){
                player.setCoordinate(endCoord);
                HexMap gameMap= match.getGameMap();
                if(gameMap.coordinateIsSafeSector(endCoord)){
                    match.notifyPlayer(player, "Ti sei spostato da "+startCoord+" in "+endCoord+", il settore è SICURO");
                    match.setState(new SafeSectorPhaseTurnState());
                    match.doAction(player, new SafeSectorAction());
                    return;
                }
                else{
                    if(gameMap.coordinateIsUnsafeSector(endCoord)){
                        match.notifyPlayer(player, "Ti sei spostato da "+startCoord+" in "+endCoord+", il settore è NON SICURO");
                        match.setState(new UnsafeSectorPhaseTurnState());
                        match.doAction(player, new UnsafeSectorAction());
                        return;
                    }
                    else{//porthole sector
                        match.notifyPlayer(player, "Ti sei spostato da "+startCoord+" in "+endCoord+", il settore è un PORTHOLE");
                        match.notifyAllPlayer(player.getNickName()+" si trova nel PortHole numero " + gameMap.getNumberPorthole(player.getCoordinate()));
                        match.setState(new PortholePhaseTurnState());
                        match.doAction(player, new PortholeAction());
                        return;
                    }
                }
                
                
                
            }
            else{
                match.notifyPlayer(player, "spostamento non valido in "+endCoord.toString());
            }
            
            
            return;
        }
        
        if(action instanceof PlayItemAction){
            ItemCard card=((PlayItemAction) action).getItemCard();
            if(card.isPlayableInitPhase()&&player.canPlayObject()){
                card = match.playItemCard(player, card);
                if(card!=null){
                    match.notifyAllPlayer("ha giocato la carta: "+card.toString());
                    return;
                }
            }
            match.notifyPlayer(player, "Non puoi giocare questa carta");
            return; 
        }
        
        
        match.notifyPlayer(player, "Mossa non disponibile ad inizio turno");
        return;
    }
    
    
    
    
    public boolean isValidMoove(Match match,Player player, Coordinate start, Coordinate end){
        boolean validMove;
        HexMap gameMap= match.getGameMap();
        validMove=gameMap.isValidMove(start, end, player.getMovementStep());
        if(validMove && match.playerIsAlien(player) && gameMap.coordinateIsPortholeSector(end))
            validMove = false; //alien can't go in active porthole sector
        return validMove;
    }
}

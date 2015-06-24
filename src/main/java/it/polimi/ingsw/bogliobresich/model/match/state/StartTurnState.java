package it.polimi.ingsw.bogliobresich.model.match.state;

import java.util.Set;

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
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.notifications.MovesAvaiable;
import it.polimi.ingsw.bogliobresich.model.player.AlienPlayer;
import it.polimi.ingsw.bogliobresich.model.player.Player;

public class StartTurnState implements State {
    @Override
    public void doAction(Match match,Player player, Action action){
        
        if(action instanceof ActionStartTurn && player==null){
            Player currentPlayer;
            do{
                currentPlayer=match.getNextPlayer();
            }while(!currentPlayer.canPlayTurn());
            match.setCurrentPlayer(currentPlayer);
            currentPlayer.setIsYourTurn(true);
            match.notifyAllPlayer(Commands.USER_START_TURN, currentPlayer.getUser());
            match.notifyPlayer(Commands.START_TURN, new Integer(match.getCurrentTurn()), currentPlayer);
            match.notifyPlayer(Commands.START_TIMER, null, currentPlayer);
            match.startTimerTurn(currentPlayer);//start timer 120 sec
            match.notifyPlayer(Commands.SET_YOUR_COORDINATE, currentPlayer.getCoordinate(), currentPlayer);
            match.notifyPlayer(currentPlayer, "è il tuo turno  - turno: "+match.getCurrentTurn());
            match.notifyPlayer(Commands.MOVES_AVAIABLE, currentMoves(match,currentPlayer) , currentPlayer);
            match.notifyPlayer(currentPlayer, "Vuoi giocare un oggetto o vuoi muoverti?");
            match.setCurrentPlayer(currentPlayer);
            return;
        }
        
        if(player==null){
            match.serviceMessage("Comando non valido");
            match.serviceMessage(Commands.GENERIC_ERROR,"Azione non disponibile in fase di inizializzazione del gioco");
            return;
        }

        if(action instanceof MovementAction){
            Coordinate endCoord=((MovementAction) action).getCoordinate();
            Coordinate startCoord=player.getCoordinate();
            if(isValidMoove(match,player,startCoord,endCoord)){
                match.notifyPlayer(Commands.START_MOVEMENT_PHASE, null, player);
                player.setCoordinate(endCoord);
                match.notifyPlayer(Commands.SET_YOUR_COORDINATE, endCoord, player);
                match.notifyPlayer(player, "Ti sei spostato da "+startCoord+" in "+endCoord);
                HexMap gameMap= match.getGameMap();
                if(gameMap.coordinateIsSafeSector(endCoord)){
                    match.notifyPlayer(player, "Il settore è SICURO");
                    match.notifyPlayer(Commands.SECTOR_TYPE_MESSAGE,"Il settore è SICURO",player);
                    match.setState(new SafeSectorPhaseTurnState());
                    match.doAction(player, new SafeSectorAction());
                    return;
                }
                else{
                    if(gameMap.coordinateIsUnsafeSector(endCoord)){
                        match.notifyPlayer(player, "Il settore è NON SICURO");
                        match.notifyPlayer(Commands.SECTOR_TYPE_MESSAGE,"Il settore è NON SICURO",player);
                        match.setState(new UnsafeSectorPhaseTurnState());
                        match.doAction(player, new UnsafeSectorAction());
                        return;
                    }
                    else{//porthole sector
                        match.notifyPlayer(player, "Il settore è un PORTHOLE");
                        match.notifyPlayer(Commands.SECTOR_TYPE_MESSAGE,"Il settore è un PORTHOLE",player);
                        match.notifyAllPlayer(player.getNickName()+" si trova nel PortHole numero " + gameMap.getNumberPorthole(player.getCoordinate()));
                        match.notifyAllPlayer(Commands.GAME_INFO_MESSAGE,player.getNickName()+" si trova nel PortHole numero " + gameMap.getNumberPorthole(player.getCoordinate()));
                        match.setState(new PortholePhaseTurnState());
                        match.doAction(player, new PortholeAction());
                        return;
                    }
                }
                
                
                
            }
            else{
                match.notifyPlayer(player, "spostamento non valido in "+endCoord.toString());
                match.notifyPlayer(Commands.GENERIC_ERROR, "Coordinate non valide ", player);
                match.notifyPlayer(Commands.MOVES_AVAIABLE, currentMoves(match,player),player);
            }
            
            
            return;
        }
        
        if(action instanceof PlayItemAction){
            ItemCard card=((PlayItemAction) action).getItemCard();
            if(card.isPlayableInitPhase()&&player.canPlayObject()){
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
        
        
        match.notifyPlayer(player, "Mossa non disponibile ad inizio turno");
        match.notifyPlayer(Commands.MOVE_NO_AVAIABLE, null, player);
        match.notifyPlayer(Commands.MOVES_AVAIABLE, currentMoves(match,player),player);
        return;
    }
    
    
    
    
    private boolean isValidMoove(Match match,Player player, Coordinate start, Coordinate end){
        boolean validMove;
        HexMap gameMap= match.getGameMap();
        validMove=gameMap.isValidMove(start, end, player.getMovementStep());
        if(validMove && match.playerIsAlien(player) && gameMap.coordinateIsPortholeSector(end))
            validMove = false; //alien can't go in active porthole sector
        return validMove;
    }
    
    private Set<Coordinate> listCoordinateReachable(Match match,Player player,Coordinate coord){
        HexMap gameMap= match.getGameMap();
        Set<Coordinate> tmpSet=gameMap.getNeighborsByDistance(coord, player.getMovementStep());
        if(player instanceof AlienPlayer)
            for(Coordinate tmpCoord:tmpSet)
                if(gameMap.coordinateIsPortholeSector(tmpCoord))
                    tmpSet.remove(tmpCoord);
        return tmpSet;
    }
    
    private MovesAvaiable currentMoves(Match match,Player player){
        MovesAvaiable move=new MovesAvaiable();
        if(player.canPlayObject())
            move.setCanPlayItem(true);
        move.setCanMove(true);
        move.setReachableCoordinate(listCoordinateReachable(match,player,player.getCoordinate()));
        return move;
    }
}

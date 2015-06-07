/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.map.HexMap;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class MovementState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(!player.equals(match.getCurrentPlayer())){
            match.notifyPlayer(player, "Non è il tuo turno");
            return;
        }  
        
        if(action instanceof MovementAction){
            Coordinate endCoord=((MovementAction) action).getCoordinate();
            if(isValidMoove(match,player,player.getCoordinate(),endCoord)){
                player.setCoordinate(endCoord);
                match.notifyPlayer(player, "ti sei spostato in "+endCoord.toString());
            }
            else
                match.notifyPlayer(player, "spostamento non valido in "+endCoord.toString());
            return;
        }
        match.serviceMessage("Mossa non disponibile durante la fase di movimento");
        return;
    }
    
    
    
    public boolean isValidMoove(Match match,Player player, Coordinate start, Coordinate end){ //da spostare
        boolean validMove;
        HexMap gameMap= match.getGameMap();
        validMove=gameMap.isValidMove(start, end, player.getMovementStep());
        if(validMove && match.playerIsAlien(player) && gameMap.coordinateIsPortholeSector(end))
            validMove = false; //alien can't go in porthole sector
        return validMove;
    }

}

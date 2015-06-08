/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.map.HexMap;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.PortholeAction;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.match.action.SafeSectorAction;
import it.polimi.ingsw.bogliobresich.model.match.action.UnsafeSectorAction;
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
            Coordinate startCoord=player.getCoordinate();
            if(isValidMoove(match,player,startCoord,endCoord)){
                player.setCoordinate(endCoord);
                match.notifyPlayer(player, "ti sei spostato da "+startCoord+" in "+endCoord.toString());
                
                HexMap gameMap= match.getGameMap();
                if(gameMap.coordinateIsSafeSector(endCoord)){
                    match.notifyPlayer(player, "Il settore è sicuro");
                    match.setState(new SafeSectorPhaseTurnState());
                    match.doAction(player, new SafeSectorAction());
                    return;
                }
                else{
                    if(gameMap.coordinateIsUnsafeSector(endCoord)){
                        match.notifyPlayer(player, "Il settore è non sicuro");
                        match.setState(new UnsafeSectorPhaseTurnState());
                        match.doAction(player, new UnsafeSectorAction());
                        return;
                    }
                    else{//porthole sector
                        match.notifyPlayer(player, "Il settore è un porthole");
                        match.notifyAllPlayer(player.getNickName()+" si trova nel PortHole in coordinate "+player.getCoordinate());
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
                //card=card.play(player);
                match.notifyAllPlayer("Carta giocata");
                //controllare e rimuovere dalla mano e fare il play della carta con return
            }
            else
                match.notifyPlayer(player, "Non puoi giocare questa carta");
            return;
            
        }
        match.serviceMessage("Mossa non disponibile durante la MovementPhase");
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

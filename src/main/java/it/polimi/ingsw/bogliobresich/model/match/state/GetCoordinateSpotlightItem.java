/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.map.HexMap;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.SpotlightCoordinateAction;
import it.polimi.ingsw.bogliobresich.model.match.action.SpotlightItemAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class GetCoordinateSpotlightItem implements State {

    State oldState;
    
    public GetCoordinateSpotlightItem(){
        
    }
    
    public GetCoordinateSpotlightItem(State oldState){
        this.oldState=oldState;
    }
    
    @Override
    public void doAction(Match match, Player player, Action action) {
        
        if(!player.equals(match.getCurrentPlayer())){
            match.notifyPlayer(player, "Non è il tuo turno");
            return;
        }
        
        if(action instanceof SpotlightItemAction){
            this.oldState=((SpotlightItemAction) action).getOldState();
            match.notifyPlayer(player, "Dimmi le coordinate in cui vuoi far luce");
            return;
        }
        
        if(action instanceof SpotlightCoordinateAction && oldState!=null){
            HexMap gameMap=match.getGameMap();
            Coordinate coord=((SpotlightCoordinateAction)action).getCoordinate();
            if(gameMap.isValidCoordinate(coord)){
                List <Player> listPlayerEnlightened = new ArrayList<Player>();
                Set <Coordinate> setCoord= gameMap.allNeighbors(coord);
                setCoord.add(coord);
                List <Player> allPlayer = match.getAllPlayer();
                for (Coordinate tmpCoord: setCoord){
                    for(Player tmpPlayer: allPlayer){
                        if(tmpCoord.equals(tmpPlayer.getCoordinate()))
                            listPlayerEnlightened.add(tmpPlayer);
                    }
                }
                String tmp=new String();
                if(listPlayerEnlightened.isEmpty())
                    tmp=new String("nessuno");
                for(Player tmpPlayer:listPlayerEnlightened)
                    tmp=new String(tmp+" "+tmpPlayer.getNickName()+" in coordinate "+ tmpPlayer.getCoordinate());
                match.notifyAllPlayer("Giocatori illuminati:"+tmp);
                match.setState(this.oldState);
            }
            else
                match.notifyPlayer(player, "Coordinate non valide per l'oggetto luce");
            return;
        }
        
        match.notifyPlayer(player, "Devi dare le coordinate della carta luce");
        return;
    }

}

/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.map.HexMap;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author matteobresich
 *
 */
public class SpotlightItemCard extends ItemCard {

    private boolean isPlayableInit = true;
    private boolean isPlayableMove = true;
    private boolean isPlayableEnd = true;
    private Coordinate coordToLight =null;
    private String stringEnlightened=null;
    
    public SpotlightItemCard(int id) {
        super.setId(id);
    }
    
    @Override
    public SpotlightItemCard play(Match match, Player p) {
        HexMap gameMap=match.getGameMap();
        Coordinate coord=this.coordToLight;
        if(coord!=null && gameMap.isValidCoordinate(coord)){
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
            this.stringEnlightened = new String("Giocatori illuminati:"+tmp);
            this.isPlayed=false;
            return this;
        }
        return null;
    }

    @Override
    public boolean isPlayableInitPhase() {
        return isPlayableInit;
    }

    @Override
    public boolean isPlayableMovePhase() {
        return isPlayableMove;
    }

    @Override
    public boolean isPlayableEndPhase() {
        return isPlayableEnd;
    }
    
    public Coordinate getCoordToLight() {
        return coordToLight;
    }

    public void setCoordToLight(Coordinate coordToLight) {
        this.coordToLight = coordToLight;
    }
    
    @Override
    public String getName(){
        return "Spotlight";
    }
    
    @Override
    public String getInfo(){
        return "\n"+this.stringEnlightened;
    }

    @Override
    public  String toString(){
        if(this.coordToLight==null)
            return new String("Spotlight Card id: "+this.getId());
        else
            return new String("Spotlight Card id: "+this.getId()+"\n"+this.stringEnlightened);
    }

}

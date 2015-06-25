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
 * The <code>SpotlightItemCard</code> class implements abstract class ItemCard.<br>
 * 
 * @author matteo bresich
 * @author simone boglio
 *  
 * @version 1.0
 * @see it.polimi.ingsw.bogliobresich.model.cards.ItemCard
 * 
 */
public class SpotlightItemCard extends ItemCard {

    private static final long serialVersionUID = 2806105238735547351L;
    private boolean isPlayableInit = true;
    private boolean isPlayableMove = true;
    private boolean isPlayableEnd = true;
    private Coordinate coordToLight =null;
    private String stringEnlightened=null;
    
    /**
     * Constructs an SpotlightItemCard with the id of the card
     * @param id the id of the spotlight card
     * 
     */
    public SpotlightItemCard(int id) {
        super.setId(id);
    }
    
    /**
     * {@inheritDoc}
     */
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
                tmp=new String(" - Nessuno");
            for(Player tmpPlayer:listPlayerEnlightened)
                tmp=new String(tmp+" - "+tmpPlayer.getNickName()+" in coordinate "+ tmpPlayer.getCoordinate());
            this.stringEnlightened = new String("Giocatori illuminati in "+coord+tmp);
            this.isPlayed=true;
            return this;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayableInitPhase() {
        return isPlayableInit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayableMovePhase() {
        return isPlayableMove;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayableEndPhase() {
        return isPlayableEnd;
    }
    
    /**
     * {@inheritDoc}
     */
    public Coordinate getCoordToLight() {
        return coordToLight;
    }

    /**
     * This method allows you to set the coordinate of the map used for the play effect on the match 
     * @param coordToLight the coordinate of the map
     * @see it.polimi.ingsw.bogliobresich.model.map.Coordinate
     */
    public void setCoordToLight(Coordinate coordToLight) {
        this.coordToLight = coordToLight;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName(){
        return "Spotlight";
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfo(){
        return "\n> "+this.stringEnlightened;
    }

    @Override
    public  String toString(){
        if(this.coordToLight==null)
            return new String("Spotlight Card id: "+this.getId());
        else
            return new String("Spotlight Card id: "+this.getId()+"\n"+this.stringEnlightened);
    }

}

/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.action;

import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

/**
 * action used by player when he want call rumor in a coordinate
 * @author simoneboglio
 *
 */
public class RumorCoordinate implements Action {
    Coordinate coord;
    public RumorCoordinate(Coordinate coord){
        this.coord=coord;
    }
    
    public Coordinate getCoordinate(){
        return this.coord;
    }
}

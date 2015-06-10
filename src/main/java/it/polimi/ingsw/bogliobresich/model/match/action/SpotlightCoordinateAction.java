/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.action;

import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

/**
 * @author simoneboglio
 *
 */
public class SpotlightCoordinateAction implements Action {
    Coordinate coord;
    public SpotlightCoordinateAction(Coordinate coord){
        this.coord=coord;
    }
    
    public Coordinate getCoordinate(){
        return this.coord;
    }
}

/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.action;

import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

/**
 * action when player want move in a coordinate
 * @author simoneboglio
 */
public class MovementAction implements Action {
    Coordinate coord;
    public MovementAction(Coordinate coord){
        this.coord=coord;
    }
    public Coordinate getCoordinate(){
        return this.coord;
    }
}

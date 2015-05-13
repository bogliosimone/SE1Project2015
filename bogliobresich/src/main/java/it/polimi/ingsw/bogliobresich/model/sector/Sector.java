/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.sector;

import it.polimi.ingsw.bogliobresich.model.Coordinate;

/**
 * @author simoneboglio
 *
 */

public abstract class Sector {
	protected boolean crossable = false;
	protected Coordinate coordinate;
	
	public Sector(char letter, int number){
		this.coordinate = new Coordinate (letter,number);
	}
	
	/**
	 * @return crossable
	 */
	public boolean isCrossable(){
		return this.crossable;
	}
	
	
	
}

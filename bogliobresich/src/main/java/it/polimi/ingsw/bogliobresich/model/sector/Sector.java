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
	protected boolean crossable;
	protected Coordinate coordinate;
	
	public Sector(){
		this.crossable = false;
	}
	
	/**
	 * @return crossable
	 */
	public boolean isCrossable(){
		return this.crossable;
	}
	
	
	
}

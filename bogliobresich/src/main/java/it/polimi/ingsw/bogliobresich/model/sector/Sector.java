/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.sector;

/**
 * @author simoneboglio
 *
 */

public abstract class Sector {
	protected boolean crossable=true;
	/**
	 * @return {@link}crossable
	 */
	public boolean isCrossable(){
		return this.crossable;
	}
	
}

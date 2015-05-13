/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.sector;

/**
 * @author simoneboglio
 */
public class PortholeSector extends Sector {
	
	public PortholeSector(){
		this.crossable=true;
	}
	/**
	 * @param crossable
	 */
	public void setCrossable(boolean crossable){
		this.crossable= crossable;
	}

}

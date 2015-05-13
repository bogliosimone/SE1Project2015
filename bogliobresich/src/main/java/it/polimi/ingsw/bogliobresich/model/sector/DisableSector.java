/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.sector;

/**
 * @author simoneboglio
 *
 */
public class DisableSector extends Sector {
	
	public DisableSector(char letter, int number){
		super(letter,number);
		this.crossable = false;
	}
}

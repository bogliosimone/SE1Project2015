/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.sector;

/**
 * @author simoneboglio
 *
 */
public class UnsafeSector extends Sector {
	public UnsafeSector(char letter,int number){
		super(letter,number);
		this.crossable = true;
	}
}

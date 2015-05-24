package it.polimi.ingsw.bogliobresich.model.sector;

import static org.junit.Assert.*;
import it.polimi.ingsw.bogliobresich.model.map.sector.AlienBaseSector;
import it.polimi.ingsw.bogliobresich.model.map.sector.Sector;

import org.junit.Test;

public class AlienBaseSectorTest {

	@Test
	public void testIsCrossable() {
		int number=0;
		char letter='b';
		Sector sec = new AlienBaseSector(letter,number);
		assertFalse(sec.isCrossable());
	}

}

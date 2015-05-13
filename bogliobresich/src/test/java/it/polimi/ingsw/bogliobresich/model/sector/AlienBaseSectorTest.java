package it.polimi.ingsw.bogliobresich.model.sector;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlienBaseSectorTest {

	@Test
	public void testIsCrossable() {
		Sector sec = new AlienBaseSector();
		assertFalse(sec.isCrossable());
	}

}

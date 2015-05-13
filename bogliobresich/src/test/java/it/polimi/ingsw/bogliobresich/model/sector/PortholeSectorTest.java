package it.polimi.ingsw.bogliobresich.model.sector;

import static org.junit.Assert.*;

import org.junit.Test;

public class PortholeSectorTest {

	@Test
	public void testSetCrossable() {
		PortholeSector sec = new PortholeSector();
		sec.setCrossable(false);
		assertFalse(sec.isCrossable());
	}

	@Test
	public void testIsCrossable() {
		Sector sec = new PortholeSector();
		assertTrue(sec.isCrossable());
	}

}

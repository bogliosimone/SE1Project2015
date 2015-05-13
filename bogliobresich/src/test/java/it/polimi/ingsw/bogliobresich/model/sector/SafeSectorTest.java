package it.polimi.ingsw.bogliobresich.model.sector;

import static org.junit.Assert.*;

import org.junit.Test;

public class SafeSectorTest {

	@Test
	public void testIsCrossable() {
		Sector sec = new SafeSector();
		assertTrue(sec.isCrossable());
	}

}

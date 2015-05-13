package it.polimi.ingsw.bogliobresich.model.sector;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnsafeSectorTest {

	@Test
	public void testIsCrossable() {
		Sector sec = new UnsafeSector();
		assertTrue(sec.isCrossable());
	}

}

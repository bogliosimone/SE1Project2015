package it.polimi.ingsw.bogliobresich.model.sector;

import static org.junit.Assert.*;

import org.junit.Test;

public class DisableSectorTest {

	@Test
	public void testIsCrossable() {
		Sector sec = new DisableSector();
		assertFalse(sec.isCrossable());
	}

}

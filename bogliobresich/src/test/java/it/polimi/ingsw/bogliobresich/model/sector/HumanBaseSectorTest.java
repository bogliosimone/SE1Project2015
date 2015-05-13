package it.polimi.ingsw.bogliobresich.model.sector;

import static org.junit.Assert.*;

import org.junit.Test;

public class HumanBaseSectorTest {

	@Test
	public void testIsCrossable() {
		Sector sec = new HumanBaseSector();
		assertFalse(sec.isCrossable());
	}

}

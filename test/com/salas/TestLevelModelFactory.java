package com.salas;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class TestLevelModelFactory {

	@Test
	public void testConstructFromJson() {
		LevelModel amodel = LevelModelFactory.constructFromJson("test/fixtures/test1.json");
		HashMap<String, DecorationModel> decos = amodel.decorations();
		assertEquals(2, decos.size());
		DecorationModel dmBB = decos.get("big-building");
		assertNotNull(dmBB);
		ArrayList<PlacementModel> plBB = dmBB.getPlacements();
		assertEquals(1, plBB.size());
		DecorationModel dmPP1 = decos.get("park-path-1");
		assertNotNull(dmPP1);
		ArrayList<PlacementModel> plPP1 = dmPP1.getPlacements();
		assertEquals(0, plPP1.size());
	}
}

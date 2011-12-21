package com.salas;

import static org.junit.Assert.*;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.salas.TileModel.TDir;

public class TestRoadmap {

	@Test
	public void testRoadmap() {
		LevelModel r = new LevelModel(20, 30);
		assertNotNull(r);
	}

	@Test
	public void testLocFromTile() {
		LevelModel r = new LevelModel(5, 9);
		TileModel t = new TileModel(new TCoord(3, 5), null, false);
		r.setTileAtCoord(t, new TCoord(3, 5));
		TPos loc = r.getSpriteTLPos(t);
		assertEquals(1500.0f, loc.x, 0.1f);
		assertEquals(900.0f, loc.y, 0.1f);
		
	}

	@Test
	public void testTileAtLoc() {
		LevelModel r = new LevelModel(10, 10);
		TileModel t = new TileModel(new TCoord(3, 3), null, false);
		r.setTileAtCoord(t, new TCoord(3, 3));
		TileModel u = r.getTile(new TCoord(3,3));
		assertSame(t, u);

	}

	@Test
	public void testSetTileAtCoord() {
		LevelModel r = new LevelModel(20,10);
		TileModel t = new TileModel(new TCoord(4, 4), null, false);
		r.setTileAtCoord(t, new TCoord(4,4));
		assertSame(t, r.getTile(new TCoord(4, 4)));
	}

	@Test
	public void testSetTileAtCoordNoArg() {
		LevelModel r = new LevelModel(20,10);
		TileModel t = new TileModel();
		r.setTileAtCoord(t, new TCoord(4,4));
		assertSame(t, r.getTile(new TCoord(4, 4)));
	}
	
	@Test
	public void testContructFromStringMap() {
		LevelModel r;
		String[][] map = { { "R", "R"},  
					 { "R", "R"} };
		r = LevelModelFactory.contructFromStringMap("{ row: 1, col: 1, direction: north}", map);
		TCoord res = r.getMaxCoords();
		assertEquals(2, res.row);
		assertEquals(2, res.col);
		
		TileModel t = r.getTile(new TCoord(0,0));
		assertTrue(t.isOpen(TDir.east) && t.isOpen(TDir.west) && t.isOpen(TDir.north) && t.isOpen(TDir.south));
		assertTrue(t.isRoad());
		
		assertEquals(TDir.north, r.getActor(0).getDir());
		assertEquals(new TCoord(1, 1), r.getActor(0).getStartingTCoord());
	}
	
	@Test
	public void testSample1() {
		LevelModel r = LevelModelFactory.createSample1();
		assertNotNull(r);
		assertEquals(new TCoord(3, 2), r.getActor(0).getStartingTCoord());
		TileModel t = r.getTile(new TCoord(3, 2));
		assertTrue(t.isOpen(TDir.north));
		assertFalse(t.isOpen(TDir.west));
	}
	@Test
	public void testSample2() {
		LevelModel r = LevelModelFactory.createSample2();
		assertNotNull(r);
		assertEquals(new TCoord(3, 2), r.getActor(0).getStartingTCoord());
		TileModel t = r.getTile(new TCoord(3, 2));
		assertTrue(t.isOpen(TDir.north));
		assertFalse(t.isOpen(TDir.west));
	}

	@Test
	public void testSample3() {
		LevelModel r = LevelModelFactory.createSample3();
		assertNotNull(r);
	}
}

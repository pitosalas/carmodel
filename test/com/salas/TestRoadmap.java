package com.salas;

import static org.junit.Assert.*;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.salas.Tile.TDir;

public class TestRoadmap {

	@Test
	public void testRoadmap() {
		GameLevel r = new GameLevel(20, 30);
		assertNotNull(r);
	}

	@Test
	public void testLocFromTile() {
		GameLevel r = new GameLevel(5, 9);
		Tile t = new Tile(new TCoord(3, 5), null, false);
		r.setTileAtCoord(t, new TCoord(3, 5));
		TPos loc = r.getSpriteTLPos(t);
		assertEquals(1000.0f, loc.x, 0.1f);
		assertEquals(600.0f, loc.y, 0.1f);
		
	}

	@Test
	public void testTileAtLoc() {
		GameLevel r = new GameLevel(10, 10);
		Tile t = new Tile(new TCoord(3, 3), null, false);
		r.setTileAtCoord(t, new TCoord(3, 3));
		Tile u = r.getTile(new TCoord(3,3));
		assertSame(t, u);

	}

	@Test
	public void testSetTileAtCoord() {
		GameLevel r = new GameLevel(20,10);
		Tile t = new Tile(new TCoord(4, 4), null, false);
		r.setTileAtCoord(t, new TCoord(4,4));
		assertSame(t, r.getTile(new TCoord(4, 4)));
	}

	@Test
	public void testSetTileAtCoordNoArg() {
		GameLevel r = new GameLevel(20,10);
		Tile t = new Tile();
		r.setTileAtCoord(t, new TCoord(4,4));
		assertSame(t, r.getTile(new TCoord(4, 4)));
	}
	
	@Test
	public void testContructFromStringMap() {
		GameLevel r;
		String[][] map = { { "R", "R"},  
					 { "R", "R"} };
		r = GameLevelFactory.contructFromStringMap("{ row: 1, col: 1, direction: north}", map);
		TCoord res = r.getMaxCoords();
		assertEquals(2, res.row);
		assertEquals(2, res.col);
		
		Tile t = r.getTile(new TCoord(0,0));
		assertTrue(t.isOpen(TDir.east) && t.isOpen(TDir.west) && t.isOpen(TDir.north) && t.isOpen(TDir.south));
		assertTrue(t.isRoad());
		
		assertEquals(TDir.north, r.getStartingDirection());
		assertEquals(new TCoord(1, 1), r.getStartingTCoord());
	}
	
	@Test
	public void testSample1() {
		GameLevel r = GameLevelFactory.createSample1();
		assertNotNull(r);
		assertEquals(new TCoord(3, 2), r.getStartingTCoord());
		Tile t = r.getTile(new TCoord(3, 2));
		assertTrue(t.isOpen(TDir.north));
		assertFalse(t.isOpen(TDir.west));
	}
	@Test
	public void testSample2() {
		GameLevel r = GameLevelFactory.createSample2();
		assertNotNull(r);
		assertEquals(new TCoord(3, 2), r.getStartingTCoord());
		Tile t = r.getTile(new TCoord(3, 2));
		assertTrue(t.isOpen(TDir.north));
		assertFalse(t.isOpen(TDir.west));
	}

	@Test
	public void testSample3() {
		GameLevel r = GameLevelFactory.createSample3();
		assertNotNull(r);
	}
}

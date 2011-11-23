package com.salas;

import static org.junit.Assert.*;

import org.junit.Test;
import com.salas.Tile.TDir;

public class TestTile {

	@Test
	public void testTile() {
		boolean blocked[] = {true, false, false, true};
		Tile t = new Tile(new TCoord(10, 20), blocked, false);
		assertNotNull(t);	
	}

	@Test
	public void testTileTwo() {
		Tile t = new Tile(new TCoord(10, 20), null, false);
		assertFalse(t.isRoad());
	}

	@Test
	public void testIsOpen() {
		boolean blocked[] = {true, false, false, true};
		Tile t = new Tile(new TCoord(10, 20), blocked, false);
		assertFalse(t.isOpen(TDir.west));
		assertTrue(t.isOpen(TDir.east));
		assertFalse(t.isOpen(TDir.north));
	}

	@Test
	public void testIsRoad() {
		Tile t = new Tile(new TCoord(10, 20), null, true);
		assertTrue(t.isRoad());
	}
	
	@Test
	public void testGetCoord() {
		Tile t = new Tile(new TCoord(2,20), null, false);
		TCoord res = t.getCoord();
		assertTrue (res.row==2 && res.col==20);
	}

	@Test
	public void testSetIsRoad() {
		Tile t = new Tile(new TCoord(10, 20), null, true);
		assertTrue(t.isRoad());
		t.setIsRoad(false);
		assertFalse(t.isRoad());
	}

	@Test
	public void testSetBlockedTrue() {
		Tile t = new Tile(new TCoord(10, 20), null, true);
		assertTrue(t.isOpen(TDir.north));
		t.setBlocked(TDir.north, true);
		assertFalse(t.isOpen(TDir.north));
	}
	
	@Test
	public void testSetBlockedfalse() {
		boolean[] temp = {true, false, false, false};
		Tile t = new Tile(new TCoord(10, 20), temp, true);
		assertFalse(t.isOpen(TDir.north));
		t.setBlocked(TDir.north, false);
		assertTrue(t.isOpen(TDir.north));
	}

	@Test
	public void testSetBlockedMultiple() {
		boolean[] temp = {true, false, false, false};
		Tile t = new Tile(new TCoord(10, 20), temp, true);
		t.setBlocked(TDir.north, true);
		t.setBlocked(TDir.north, true);
		assertFalse(t.isOpen(TDir.north));
	}

}

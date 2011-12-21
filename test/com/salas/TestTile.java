package com.salas;

import static org.junit.Assert.*;

import org.junit.Test;
import com.salas.TileModel.TDir;

public class TestTile {

	@Test
	public void testTile() {
		boolean blocked[] = {true, false, false, true};
		TileModel t = new TileModel(new TCoord(10, 20), blocked, false);
		assertNotNull(t);	
	}

	@Test
	public void testTileTwo() {
		TileModel t = new TileModel(new TCoord(10, 20), null, false);
		assertFalse(t.isRoad());
	}

	@Test
	public void testIsOpen() {
		boolean blocked[] = {true, false, false, true};
		TileModel t = new TileModel(new TCoord(10, 20), blocked, false);
		assertFalse(t.isOpen(TDir.west));
		assertTrue(t.isOpen(TDir.east));
		assertFalse(t.isOpen(TDir.north));
	}

	@Test
	public void testIsRoad() {
		TileModel t = new TileModel(new TCoord(10, 20), null, true);
		assertTrue(t.isRoad());
	}
	
	@Test
	public void testGetCoord() {
		TileModel t = new TileModel(new TCoord(2,20), null, false);
		TCoord res = t.getCoord();
		assertTrue (res.row==2 && res.col==20);
	}

	@Test
	public void testSetIsRoad() {
		TileModel t = new TileModel(new TCoord(10, 20), null, true);
		assertTrue(t.isRoad());
		t.setIsRoad(false);
		assertFalse(t.isRoad());
	}

	@Test
	public void testSetBlockedTrue() {
		TileModel t = new TileModel(new TCoord(10, 20), null, true);
		assertTrue(t.isOpen(TDir.north));
		t.setBlocked(TDir.north, true);
		assertFalse(t.isOpen(TDir.north));
	}
	
	@Test
	public void testSetBlockedfalse() {
		boolean[] temp = {true, false, false, false};
		TileModel t = new TileModel(new TCoord(10, 20), temp, true);
		assertFalse(t.isOpen(TDir.north));
		t.setBlocked(TDir.north, false);
		assertTrue(t.isOpen(TDir.north));
	}

	@Test
	public void testSetBlockedMultiple() {
		boolean[] temp = {true, false, false, false};
		TileModel t = new TileModel(new TCoord(10, 20), temp, true);
		t.setBlocked(TDir.north, true);
		t.setBlocked(TDir.north, true);
		assertFalse(t.isOpen(TDir.north));
	}

}

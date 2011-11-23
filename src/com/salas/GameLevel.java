package com.salas;

import com.google.gson.Gson;

import com.salas.Tile.TDir;

public class GameLevel {

	private static float DEFAULT_COORD_SCALE_FACTOR = 200.0f;

	int gridRows, gridColumns;
	TCoord startingPoint;
	TDir startingDirection;
	Tile[][] grid;
	float coordScaleFactor = DEFAULT_COORD_SCALE_FACTOR;

	GameLevel(int rows, int cols) {
		startingPoint = new TCoord(0, 0);
		gridRows = rows;
		gridColumns = cols;
		grid = new Tile[rows][cols];
	}

	void setStartingTCoord(int row, int col) {
		startingPoint.row = row;
		startingPoint.col = col;
	}

	public TCoord getStartingTCoord() {
		return startingPoint;
	}

	public TDir getStartingDirection() {
		return startingDirection;
	}

	void setStartingDirection(TDir direction) {
		startingDirection = direction;

	}

	public TPos getStartingTPos() {
		return getSpriteCenterPos(getTile(getStartingTCoord()));
	}

	TCoord getMaxCoords() {
		return new TCoord( gridRows, gridColumns);
	}

	public TPos getCenterPos() {
		TCoord sz = getMaxCoords();
		return sz.scale(coordScaleFactor/2.0f);
	}
	
	public TPos getBottomRightPos() {
		return getMaxCoords().scale(coordScaleFactor);		
	}

	TPos getSpriteCenterPos(Tile aTile) {
		return (new TPos((aTile.getCoord().col * coordScaleFactor) + coordScaleFactor/2.0f,
				(aTile.getCoord().row * coordScaleFactor) + coordScaleFactor/2.0f));
	}
	
	TPos getSpriteTLPos(Tile aTile) {
		return (new TPos(aTile.getCoord().col * coordScaleFactor,
				aTile.getCoord().row * coordScaleFactor));
	}

	TPos getSpriteTRPos(Tile aTile) {
		return (new TPos((aTile.getCoord().col+1) * coordScaleFactor,
				aTile.getCoord().row * coordScaleFactor));
	}
	TPos getSpriteBLPos(Tile aTile) {
		return (new TPos(aTile.getCoord().col * coordScaleFactor,
				(aTile.getCoord().row+1) * coordScaleFactor));
	}
	TPos getSpriteBRPos(Tile aTile) {
		return (new TPos((aTile.getCoord().col+1) * coordScaleFactor,
				(aTile.getCoord().row+1) * coordScaleFactor));
	}
	
	Tile getTile(TPos spritePos) {
		float x = spritePos.x;
		float y = spritePos.y;
		assert x >= 0 && y >= 0 && x < gridColumns * coordScaleFactor
				&& y < gridRows * coordScaleFactor;
		return grid[(int) (y / coordScaleFactor)][(int) (x / coordScaleFactor)];
	}

	Tile getTile(TCoord coord) {
		Tile res = grid[coord.row][coord.col];
		assert res != null;
		if (res.getCoord().row == -1) { // means first use
			setTileAtCoord(res, coord);
		}
		return res;
	}
	
	Tile nextTile(TDir dir, Tile t) {
		TDir borderDir = onBorder(t);
		if (borderDir == dir) return null;
		int tRow = t.getCoord().row;
		int tCol = t.getCoord().col;
		switch (dir) {
			case north: tRow--; break;
			case south: tRow++; break;
			case east: tCol++; break;
			case west: tCol--; break;
		}
		return getTile(new TCoord(tRow, tCol));
	}

	TDir onBorder(Tile t) {
		if (t.getCoord().row == 0) return TDir.north;
		else if (t.getCoord().col == 0) return TDir.west;
		else if (t.getCoord().row == gridRows - 1) return TDir.south;
		else if (t.getCoord().col == gridColumns - 1) return TDir.east;
		else return null;
	}

	// An intersection is where I can make a turn either right or left
	public boolean isIntersection(TDir direction, Tile tile) {
		switch (direction) {
		case north:
		case south:
			return tile.isOpen(TDir.east) || tile.isOpen(TDir.west);
		case east:
		case west:
			return tile.isOpen(TDir.north) || tile.isOpen(TDir.south);
		}
		return false;
	}
	
	// A dead end is where I cannot go forward, and it is also not an intersection. 
	public boolean isDeadEnd(TDir dir, Tile tile) {
		return !isIntersection(dir, tile) && !tile.isOpen(dir);
	}
	
	Tile nextRequiredTurn(TDir traveldir, Tile t) {
		while (t.isOpen(traveldir)) {
			t = nextTile(traveldir, t);
		}
		return t;
	}

	void setTileAtCoord(Tile t, TCoord targetCoord) {
		TCoord coord = t.getCoord();
		int r = targetCoord.row;
		int c = targetCoord.col;
		assert (coord.row == r && coord.col == c);
		assert (r >= 0 && r < gridRows && c >= 0 && c < gridColumns);
		grid[r][c] = t;
		t.setCoord(targetCoord);
	}


}

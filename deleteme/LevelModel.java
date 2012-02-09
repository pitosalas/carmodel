package com.salas;

import java.util.ArrayList;
import java.util.HashMap;

import com.salas.TileModel.TDir;

public class LevelModel {

	private static float DEFAULT_COORD_SCALE_FACTOR = 300;
	int level;
	int gridRows, gridColumns;
	TileModel[][] grid;
	private ArrayList<ActorModel> actors;
	private HashMap<String, DecorationModel> decorations;
	public String tmxFileName;
	float coordScaleFactor = DEFAULT_COORD_SCALE_FACTOR;

	LevelModel(int rows, int cols) {
		gridRows = rows;
		gridColumns = cols;
		grid = new TileModel[rows][cols];
	}

	public TPos getStartingTPos(int actor) {
		return getSpriteCenterPos(getTile(actors.get(actor).getStartingTCoord()));
	}
	
	public TPos convTCoord2Tpos(TCoord coord) {
		return getSpriteCenterPos(getTile(coord));
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

	TPos getSpriteCenterPos(TileModel aTile) {
		TPos tl = getSpriteTLPos(aTile);		// get top left
		tl.x += coordScaleFactor/2.0f;			// offset x and y to find the center.
		tl.y += coordScaleFactor/2.0f;
		return tl;
	}
	
	TPos getSpriteTLPos(TileModel aTile) {
		return newTposFromTCoord(aTile.getCoord());
	}

	TPos getSpriteTRPos(TileModel aTile) {
		return newTposFromCoords(aTile.getCoord().row, aTile.getCoord().col+1);
	}
	
	TPos getSpriteBLPos(TileModel aTile) {
		return newTposFromCoords(aTile.getCoord().row+1, aTile.getCoord().col);
	}

	TPos getSpriteBRPos(TileModel aTile) {
		return newTposFromCoords(aTile.getCoord().row+1, aTile.getCoord().col+1);
	}
	
	TPos newTposFromTCoord(TCoord aCoord) {
		return newTposFromCoords(aCoord.row, aCoord.col);	}
	
	TPos newTposFromCoords(float row, float col) {
		return new TPos(col * coordScaleFactor, row * coordScaleFactor);
	}
	
	TileModel getTile(TPos spritePos) {
		float x = spritePos.x;
		float y = spritePos.y;
		assert x >= 0 && y >= 0 && x < gridColumns * coordScaleFactor
				&& y < gridRows * coordScaleFactor;
		return grid[(int) (y / coordScaleFactor)][(int) (x / coordScaleFactor)];
	}

	TileModel getTile(TCoord coord) {
		TileModel res = grid[coord.row][coord.col];
		assert res != null;
		if (res.getCoord().row == -1) { // means first use
			setTileAtCoord(res, coord);
		}
		return res;
	}
	
	TileModel nextTile(TDir dir, TileModel t) {
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

	TDir onBorder(TileModel t) {
		if (t.getCoord().row == 0) return TDir.north;
		else if (t.getCoord().col == 0) return TDir.west;
		else if (t.getCoord().row == gridRows - 1) return TDir.south;
		else if (t.getCoord().col == gridColumns - 1) return TDir.east;
		else return null;
	}

	// An intersection is where I can make a turn either right or left
	public boolean isIntersection(TDir direction, TileModel tile) {
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
	public boolean isDeadEnd(TDir dir, TileModel tile) {
		return !isIntersection(dir, tile) && !tile.isOpen(dir);
	}
	
	TileModel nextRequiredTurn(TDir traveldir, TileModel t) {
		while (t.isOpen(traveldir)) {
			t = nextTile(traveldir, t);
		}
		return t;
	}

	void setTileAtCoord(TileModel t, TCoord targetCoord) {
		TCoord coord = t.getCoord();
		int r = targetCoord.row;
		int c = targetCoord.col;
		assert (coord.row == r && coord.col == c);
		assert (r >= 0 && r < gridRows && c >= 0 && c < gridColumns);
		grid[r][c] = t;
		t.setCoord(targetCoord);
	}
	
	public void setActors(ArrayList<ActorModel> actors) {
		this.actors = actors;
	}
	
	public ArrayList<ActorModel> actors() {
		return actors;
	}

	public ActorModel getActor(int i) {
		assert i >= 0 && i < actors.size();
		return actors.get(i);
	}
	
	public HashMap<String, DecorationModel> decorations() {
		return decorations;
	}

	public void setDecorations(HashMap<String, DecorationModel> decHash) {
		this.decorations = decHash;
	}
}

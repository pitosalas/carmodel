package com.salas;

public class Tile {
	
	private boolean isRoad;
	private TCoord gridCoord;
	private boolean[] blocked = new boolean[4];

	enum TDir {
		north (1), south (2), east (4), west (8);
		int value;

		TDir(int val) {
			value = val;
		}
		int val() {
			return value;
		}
		public float toDegrees() {
			switch (this) {
			case north: return 0;
			case south: return 180;
			case east: return 90;
			case west: return 270;
			}
			return 0;
		}
		public float toRadians() {
			return (float) Math.toRadians(toDegrees());
		}
		
		public int toIndex() {
			switch (this) {
			case north: return 0;
			case south: return 1;
			case east: return 2;
			case west: return 3;
			}
			return -1;
		}
		
		public TDir rightTurn() {
			switch (this) {
			case north:
				return TDir.east;
			case south:
				return TDir.west;
			case east:
				return TDir.south;
			case west:
				return TDir.north;
			}
			return null;
		}
		public TDir leftTurn() {
			switch (this) {
			case north:
				return TDir.west;
			case south:
				return TDir.east;
			case east:
				return TDir.north;
			case west:
				return TDir.south;
			}
			return null;
		}
	}
	
	enum TRoad {
		r (16), nr(0);
		int value;
		TRoad(int val) {
			value = val;
		}
		int val() {
			return value;
		}
	}
	
	Tile(TCoord coord, boolean[] blkd, boolean road) {
		assert blkd==null || blkd.length==4;
		
		if (blkd == null) {
			boolean[] temp = {false, false, false, false};
			blocked = temp; 
			}
		else { 
			blocked = blkd;
			}
		isRoad = road;
		gridCoord = coord;
	}
	
	public Tile() {
		this(new TCoord(-1, -1), null, false);
	}
		
	boolean isOpen(TDir dir) {
		return !blocked[dir.toIndex()];
	}
	
	boolean[] getBlocked() {
		return blocked;
	}
	
	boolean isRoad() {
		return isRoad;
	}

	public void setIsRoad(boolean val) {
		isRoad = val;
	}

	public void setBlocked(TDir dir, boolean val) {
		blocked[dir.toIndex()] = val;
	}

	public TCoord getCoord() {
		return gridCoord;
	}

	public void setCoord(TCoord targetCoord) {
		assert gridCoord.row == -1 && gridCoord.col == -1;
		gridCoord = targetCoord;
	}

}

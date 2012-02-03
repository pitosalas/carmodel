package com.salas;

import com.salas.TileModel.TDir;

// Base class for GameActors, which are things like cars, trucks, towtrucks, and so on, which are
// located on and travel around the game space for a GameLevel. When instantiated they lead to the
// creation of Sprites on the screen. 

public class ActorModel {
	private int startRow;
	private int startCol;
	private float startXPos;
	private float startYPos;
	private float startRotation;
	private TDir startDir;
	private String type;
	private String name;

	public ActorModel(String typ, String nme, int row, int col, TDir dr, float startX, float startY, float startRot) {
		startRow = row;
		startCol = col;
		type = typ;
		startDir = dr;
		name = nme;
		startXPos = startX;
		startYPos = startY;
		startRotation = startRot;
	}
	
	public TCoord getStartingTCoord() {
		return new TCoord(startRow, startCol);
	}
		
	public TDir getDir() {
		return startDir;
	}

	public TPos getStartingTPos(LevelModel rmap) {
		return rmap.convTCoord2Tpos(getStartingTCoord());
	}

	public TDir getStartingDir() {
		return startDir;
	}

	public String getName() {
		return name;
	}

	public Vector2 getStartingPos() {
		return new Vector2(startXPos, startYPos);
	}
	public float getStartingRotation() {
		return startRotation;
	}


}

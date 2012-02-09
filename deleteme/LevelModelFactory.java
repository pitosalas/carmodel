package com.salas;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.salas.TileModel.TDir;

// Processes GameLevel info (from json file) and creates a complete GameLevel object (which has a variety of parts to it.)

public class LevelModelFactory {
	
	// Factories
	class Params {
		int row;
		int col;
		TDir direction;

		Params() {
		}

		@Override
		public boolean equals(Object p) {
			if (this == p)
				return true;
			Params pp = (Params) p;
			return (row == pp.row && col == pp.col && direction == pp.direction);
		}

		public Params(int r, int c, TDir dir) {
			row = r;
			col = c;
			direction = dir;
		}
	}

// A LevelMap is pre-populated with zero or more actors.
	class ActorDesc {
		int row;			// Initial Row or X
		int col;			// Initial Column or Y
		String type;		// String denotes the type
		TDir dir;			// direction (N,S,E or W)
		String name;		// Name of it
		float startXPos;	// XPosition (alternative to Row and Col
		float startYPos;	// YPosition
		float startRotation;// Rotation (alternative to direction)
		ActorDesc() {}
	}

// It can have zero or more decorations
	class DecorationDesc {
		String name;
		int[] pos;
		int[] dim;
		DecorationDesc() {}
	}
	
// And the decorations can be used in specific places
	class DecPlaceDesc {
		String name;
		int[] coord;
		float[] offsetFactor;
		DecPlaceDesc() {}
	}

// Describes a full level
	class LevelDesc {
		int level;			// level number
		int rows;			// Width of map
		int cols;			// Height of map
		String tmxFilename;	// Filename of TMX tile map descriptor file
		ActorDesc[] actors;	// Zero or more initial actors
		DecorationDesc[] decList; // Zero or more decorations over the map
		DecPlaceDesc[] decPlacement; // Each decoration may be used in more than one place.
		String [][] map;	// Two dimensional map of interesection descriptors
		LevelDesc() {}
	}
		
	class DecDim {
		int width, height;
		DecDim() {}
	}
	
	static Level constructFromJson(String fileName) {
		try {
			FileReader f = new FileReader(fileName);
			return constructFromJson(f);			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Level constructFromJson(InputStreamReader aJsonFileStream) {
		Gson gson = new Gson();
		LevelDesc levelDesc = gson.fromJson(aJsonFileStream, LevelDesc.class);
		Level level = gameMapfromStringMap(levelDesc.rows, levelDesc.cols, levelDesc.map);
		ArrayList<ActorModel> actors  = actorsFromDesc(levelDesc.actors);
		HashMap<String, DecorationModel> decorations = decorationsFromDesc(levelDesc.decList, levelDesc.decPlacement);
		level.setActors(actors);
		level.setDecorations(decorations);
		level.tmxFileName = levelDesc.tmxFilename;
		level.level = levelDesc.level;
		return level;
	}

	private static ArrayList<ActorModel> actorsFromDesc(ActorDesc[] actorDescs) {
		ArrayList<ActorModel> actors = new ArrayList<ActorModel>(actorDescs.length);
		for (int i = 0; i < actorDescs.length; i++) {
			ActorDesc ad = actorDescs[i];
			actors.add(i, new ActorModel(ad.type, ad.name, ad.row, ad.col, ad.dir, ad.startXPos, ad.startYPos, ad.startRotation));
		}
		return actors;
	}
	
	private static HashMap<String, DecorationModel> decorationsFromDesc(DecorationDesc[] decList, DecPlaceDesc[] decPlaces) {
		HashMap<String, DecorationModel> decorations = new HashMap<String, DecorationModel>(decList.length);
		// First fill in each of the named decorations, plus their size and location in the graphic file
		for (int i = 0; i < decList.length; i++ ) {
			DecorationDesc dl = decList[i];
			decorations.put(dl.name, new DecorationModel(dl.name, dl.pos[0], dl.pos[1], dl.dim[0], dl.dim[1]));
		}

// Next indicate where each decoration is used in the overall display of the level
		for (int i = 0; i < decPlaces.length; i++) {
			DecPlaceDesc dp = decPlaces[i];
			DecorationModel md = decorations.get(dp.name);
// Make sure the name of the decoration in the placement list matches one of the known decorations
			assert md != null; 

// Record the coordiantes where the decoration goes, and an offset factor between 0 and 1 which nuges it over up to one
// full cell over in each direction.
			assert dp.offsetFactor[0] >= 0 && dp.offsetFactor[0] <= 1.0 && dp.offsetFactor[1] >= 0 && dp.offsetFactor[1] <= 1;
			md.addAPlacement(new TCoord(dp.coord[0], dp.coord[1]), dp.offsetFactor[0], dp.offsetFactor[1]);
		}
		return decorations;
	}
	
	
	private static Level gameMapfromStringMap(int rows, int cols, String[][] map) {
		Level m = new Level(rows, cols);
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				String c = map[row][col];
				tileFromSpecString(m, row, col, c);
			}
		}
		return m;
	}

	public static void tileFromSpecString(Level m, int row, int col, String c) {
		TileModel t = new TileModel();
		t.setIsRoad(c.contains("R"));
		t.setBlocked(TDir.west, (c.contains("W")|| c.contains("X")));
		t.setBlocked(TDir.north, (c.contains("N")|| c.contains("X")));
		t.setBlocked(TDir.east, (c.contains("E")|| c.contains("X")));
		t.setBlocked(TDir.south, (c.contains("S")|| c.contains("X")));
		m.setTileAtCoord(t, new TCoord(row, col));
	}
	
	static Level contructFromStringMap(String paramsJ, String[][] map) {
		int rows = map.length;
		int cols = map[0].length;
		for (int i = 0; i < rows; i++) {
			assert cols == map[i].length;
		}
		Level m = gameMapfromStringMap(rows, cols, map);
		Gson gson = new Gson();
		Params p = gson.fromJson(paramsJ, Params.class);

		// Setup list of actors to be a single one
		ArrayList<ActorModel> actors = new ArrayList<ActorModel>(1);
		actors.add(0, new ActorModel("Car", "Car", p.row, p.col, p.direction, 0, 0, 0));
		m.setActors(actors);
		return m;
	}

	
	static Level createSample2() {
		String[][] map = { { "WNR", "NSR", "NSR", "NSR", "NSR", "NER", "WN" },
				{ "WER", "WNS", "NS", "NS", "NSE", "EWR", "W" },
				{ "WSR", "NSR", "NR", "NSR", "NSR", "ESR", "W" },
				{ "WNS", "NES", "WESR", "WNS", "SN", "ESN", "W" } };
		return contructFromStringMap("{ row: 3, col: 2, direction: north}", map);
	}
	
	static Level createSample1() {
		String[][] map = { { "WNR", "NSR", "NSR", "NSR", "NSR", "NER" },
				{ "WER", "WNS", "NS", "NS", "NSE", "EWR" },
				{ "WSR", "NSR", "NR", "NSR", "NSR", "ESR" },
				{ "WNS", "NES", "WESR", "WNS", "SN", "ESN" } };
		return contructFromStringMap("{ row: 3, col: 2, direction: north}",
				map);
	}
	
	static Level createSample3() {
		String[][] map = 
				{ { "NWR", "NSR",  "NR", "NSR", "NSR", "NSR", "NR",  "NESR"},
				  { "EWR", "NSEWS","EWR","NWS", "NS" , "NE",  "EWR", "NWSE"},
				  { "WR",  "NSR",  "SR", "NR",  "NESR","EW",  "WR",  "NESR"},
				  { "EWR", "NW",   "NE", "EWR", "NWS", "SE",  "EWR", "NEW"},
				  { "EWR", "WS",   "ES", "WR",  "NSR","NR",  "ESR", "EW"},
				  { "SWR", "NSR",  "NSR","ESR", "NSEW","WSER","NWS",  "SE" } };
		return contructFromStringMap("{ row: 0, col: 0, direction: south}",
				map);
	}

	

	

}

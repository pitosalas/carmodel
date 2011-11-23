package com.salas;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import com.salas.Tile.TDir;


public class GameLevelFactory {
	
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
	class Actor {
		int level;			// level number
		int row;			// Initial Row 
		int col;			// Initial Column
		String type;		// String denotes the type
		TDir dir;			// direction (N,S,E or W)
		String name;		// Name of it
		Actor() {}
	}

	class LevelMap {
		int rows;			// Width of map
		int cols;			// Height of map
		Actor[] actors;		// Zero or more initial actors
		String [][] map;	// Two dimensional map of interesection descriptors
		String roadImages;	// Filename for road tile images
		String parkImages;	// Filenam for park ti
		LevelMap() {}
	}
	
	
	static GameLevel constructFromJson(String fileName) {
		try {
			FileReader f = new FileReader(fileName);
			return constructFromJson(f);			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	static GameLevel createSample2() {
		String[][] map = { { "WNR", "NSR", "NSR", "NSR", "NSR", "NER", "WN" },
				{ "WER", "WNS", "NS", "NS", "NSE", "EWR", "W" },
				{ "WSR", "NSR", "NR", "NSR", "NSR", "ESR", "W" },
				{ "WNS", "NES", "WESR", "WNS", "SN", "ESN", "W" } };
		return contructFromStringMap("{ row: 3, col: 2, direction: north}", map);
	}
	
	static GameLevel createSample1() {
		String[][] map = { { "WNR", "NSR", "NSR", "NSR", "NSR", "NER" },
				{ "WER", "WNS", "NS", "NS", "NSE", "EWR" },
				{ "WSR", "NSR", "NR", "NSR", "NSR", "ESR" },
				{ "WNS", "NES", "WESR", "WNS", "SN", "ESN" } };
		return contructFromStringMap("{ row: 3, col: 2, direction: north}",
				map);
	}
	
	static GameLevel createSample3() {
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

	
	public static GameLevel constructFromJson(InputStreamReader aJsonFileStream) {
		Gson gson = new Gson();
		LevelMap levelMap = gson.fromJson(aJsonFileStream, LevelMap.class);
		GameLevel gameMap = gameMapfromStringMap(levelMap.rows, levelMap.cols, levelMap.map);
		Actor a = levelMap.actors[0];
		gameMap.setStartingTCoord(a.row, a.col);
		gameMap.setStartingDirection(a.dir);
		return gameMap;
	}

	private static GameLevel gameMapfromStringMap(int rows, int cols, String[][] map) {
		GameLevel m = new GameLevel(rows, cols);
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				Tile t = new Tile();
				String c = map[row][col];
				t.setIsRoad(c.contains("R"));
				t.setBlocked(TDir.west, (c.contains("W")|| c.contains("X")));
				t.setBlocked(TDir.north, (c.contains("N")|| c.contains("X")));
				t.setBlocked(TDir.east, (c.contains("E")|| c.contains("X")));
				t.setBlocked(TDir.south, (c.contains("S")|| c.contains("X")));
				m.setTileAtCoord(t, new TCoord(row, col));
			}
		}
		return m;
	}
	
	static GameLevel contructFromStringMap(String paramsJ, String[][] map) {
		int rows = map.length;
		int cols = map[0].length;
		for (int i = 0; i < rows; i++) {
			assert cols == map[i].length;
		}
		GameLevel m = gameMapfromStringMap(rows, cols, map);
		Gson gson = new Gson();
		Params p = gson.fromJson(paramsJ, Params.class);

		m.setStartingTCoord(p.row, p.col);
		m.setStartingDirection(p.direction);
		return m;
	}


	

}

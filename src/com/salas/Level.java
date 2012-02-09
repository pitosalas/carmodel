package com.salas;

import java.util.*;

public class Level {
	BaseGraph <Intersection, Road> graph;
	
	Level() {
	   Vector<Intersection> vectorOfIntersections = new Vector<Intersection>();
	   Vector<ArrayList<Road>> vectorOfRoadsStartingAtIntersection = new Vector<ArrayList<Road>>();
		graph = new BaseGraph<Intersection, Road>(vectorOfIntersections, vectorOfRoadsStartingAtIntersection);
	}
	
	void addNode(String name, float x, float y) {
		graph.addNode(new Intersection(name, new Vector2(x, y)));
	}
}

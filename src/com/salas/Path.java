package com.salas;

import java.util.ArrayList;

public class Path {
	boolean loop;
	ArrayList<Vector2> waypoints;
	int current = 0;
	
	Path() {
		waypoints = new ArrayList<Vector2>();
	}
	
	Path(Vector2...points) {
		this();
		setPoints(points);
	}	
	
	public Path (boolean loop, Vector2...points) {
		this();
		setPoints(points);
		setLoop(loop);
	}
	
	private void setPoints(Vector2...points) {
		for (Vector2 v: points) {
			waypoints.add(v);
		}		
	}

	public void setLoop(boolean setting) {
		loop = setting;
	}
	
	public boolean finished() {
		return (!loop && current >= waypoints.size());
	}
	
	public Vector2 getNextWayPoint() {
		// Note that this is coded so that current is always valid. This is because getCurrentWayPoint can be called
		// at any time and it should always be pointing to something current.
		// If current is pointing at the last valid one, path is not a closed loop, then there is no next.
		if (current+1 == waypoints.size()) {
			if (!loop) {
				return null;
			} else {
				current = 0;
			}
		} else {
			current++;
		}
		return getCurrentWayPoint();
	}

	public Vector2 getCurrentWayPoint() {
		return waypoints.get(current);
	}
	
}

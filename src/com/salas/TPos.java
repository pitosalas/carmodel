package com.salas;

// An X-Y position.
public class TPos {
	public float x;
	public float y;
	public TPos() {}

	public TPos(float xpos, float ypos) {
		x = xpos;
		y = ypos;
	}

	// Make a clone copy of the object
	public TPos(TPos pos) {
		x = pos.x;
		y = pos.y;
	}

	public String toString() {
		return String.format("[%3.0f, %3.0f]", x, y);
	}
}

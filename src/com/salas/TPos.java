package com.salas;

public class TPos {
	public float x;
	public float y;
	public TPos(float xpos, float ypos) {
		x = xpos;
		y = ypos;
	}
	public String toString() {
		return String.format("[%3.0f, %3.0f]", x, y);
	}
}

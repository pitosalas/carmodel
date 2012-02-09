package com.salas;

public class Intersection extends BaseNode {
	Vector2 pos = new Vector2();
	String name;
	
	Intersection(String  nme, Vector2 p) {
	   name = nme;
		pos.x = p.x;
		pos.y = p.y;
	}

}

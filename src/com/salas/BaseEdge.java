package com.salas;

public class BaseEdge {

	private static int nextIndex = 1;
	
// Edge connects these pair of nodes
	final int from;
	final int to;
	
	
	public BaseEdge(int f, int t) {
		from = f;
		to = t;
	}

}

package com.salas.graph;

public class BaseEdge {

// Edge connects these pair of nodes
	public final int from;
	public final int to;
	
	
	public BaseEdge(int f, int t) {
		from = f;
		to = t;
	}
	
	public int otherEnd(int one) {
	   if (from == one) {
	      return to;
	   }
	   else {
	      return from;
	   }
	}

}

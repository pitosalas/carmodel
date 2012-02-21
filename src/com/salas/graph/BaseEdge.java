package com.salas.graph;

public abstract class BaseEdge {

   protected abstract BaseEdge clone();
   
// Edge connects these pair of nodes
	public int from;
	public int to;
	
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
	
	// Swaps my own from and to with each other. 
	public void reversePolarity() {
	   int tempFrom, tempTo;
	   tempFrom = from; tempTo = to;
	   from = tempTo; to = tempFrom;
	}
	
	public String toString() {
	   return "Edge: "+from+" -> "+to;
	}

}

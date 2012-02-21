package com.salas;

import com.salas.graph.*;

public class Road extends BaseEdge {
	
	Road(int f, int t) {
		super(f, t);
	}

   @Override
   protected BaseEdge clone() {
      return new Road(this.from, this.to);
   }
	
}

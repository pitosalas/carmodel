package com.salas.graph;

import static com.google.common.base.Preconditions.*;

public class BaseNode {
	public final static int INVALID = -1;
	private String name;
	
	public int index;
	
	public BaseNode(int theIndex, String newname) {
      checkNotNull(theIndex);
		index = theIndex;
		name = newname;
	}
	
	public String name() {
	   return name;
	}
	
	public void setName(String s) {
	   name = s;
	}
}

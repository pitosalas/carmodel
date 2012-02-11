package com.salas;

import static com.google.common.base.Preconditions.*;

public class BaseNode {
	final static int INVALID = -1;
	private String name;
	
	int index;
	
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

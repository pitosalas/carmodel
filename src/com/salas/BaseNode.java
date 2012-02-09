package com.salas;

public class BaseNode {
	private static int nextIndex = 1;
	final static int INVALID = -1;
	
	int index;
	
	public BaseNode() {
		this(nextIndex);
		nextIndex++;
	}
	
	public BaseNode(int theIndex) {
		index = theIndex;
	}
}

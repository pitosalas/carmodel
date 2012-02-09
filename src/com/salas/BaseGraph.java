package com.salas;

import java.util.ArrayList;
import java.util.Vector;

public class BaseGraph<Node extends BaseNode, Edge extends BaseEdge> {
	// The nodes in this graph
	private Vector<Node> nodes;
	
	// For each node (index) there is a list of Edges that start or end there. So: Vector(n)
	// contains a list of Edges which start or end at node n.
	private Vector<ArrayList<Edge>> edges;
	
	BaseGraph(Vector<Node> nodevector, Vector<ArrayList<Edge>> edgeVector) {
	   nodes = nodevector;
	   edges = edgeVector;
	}

	public void addNode(Node newnode) {
		nodes.add(newnode);
	}

}

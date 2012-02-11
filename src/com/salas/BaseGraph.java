package com.salas;

import static com.google.common.base.Preconditions.*;

import java.util.*;

public class BaseGraph<Node extends BaseNode, Edge extends BaseEdge> {

   // The nodes in this graph
	private ArrayList<Node> nodes;
	
	// For each node (index) there is a list of Edges that start or end there. So: Vector(n)
	// contains a list of Edges which start or end at node n.
	private ArrayList<ArrayList<Edge>> edges;
	
	BaseGraph(ArrayList<Node> nodeArray, ArrayList<ArrayList<Edge>> edgeArray) {
	   nodes = nodeArray;
	   edges = edgeArray;
	}

	List<Node>nodeList() {
      return nodes;
   }

   List<ArrayList<Edge>>edgeList() {
      return edges;
   }

   public int addNode(Node newnode) {
	   padTo(nodes, newnode.index+1);
	   nodes.set(newnode.index, newnode);
		return newnode.index;
	}
	
	public int getNode(String name) {
      for (Node anode: nodes) {
         if (anode != null && anode.name() != null && anode.name().equalsIgnoreCase(name)) {
            return anode.index;
         }
      }
      return BaseNode.INVALID;
   }

   public Node getNode(int ident) {
      checkArgument(ident != BaseNode.INVALID, "BaseHGraph::getNode - INVALID ident ");
      return nodes.get(ident);
   }

   public boolean hasNode(String name) {
      return getNode(name)!= BaseNode.INVALID;
   }

   public int nodeCount() {
      return nodes.size();
   }

   // New edge is added twice to the vector of edge lists, one for the source and one for the destination
	public void addEdge(Edge newedge) {
	   edgesSlotPrepare(newedge.from);
	   edgesSlotPrepare(newedge.to);
	   
	   edges.get(newedge.from).add(newedge);
      edges.get(newedge.to).add(newedge);
	}
	
	public int edgesFromCount(int index) {
      edgesSlotPrepare(index);
      return edges.get(index).size();
   }
   
   public boolean connected(String node1, String node2) {
      int ind1 = getNode(node1);
      int ind2 = getNode(node2);
      if (ind1==BaseNode.INVALID || ind2==BaseNode.INVALID) {
         return false;
      }
      ArrayList<Edge> e1 = edges.get(ind1);
      ArrayList<Edge> e2 = edges.get(ind2);
      boolean e1HasInd2 = searchEdgeArray(e1, ind2);
      boolean e2HasInd1 = searchEdgeArray(e2, ind1);
      checkArgument(!(e1HasInd2 ^ e2HasInd1), "Basegraph connected: links are not bidirectional.");
      return e1HasInd2 && e2HasInd1;
   }

   private boolean searchEdgeArray(ArrayList<Edge> edgearray, int nodeIndex) {
      for (Edge anEdge: edgearray) {
         if (anEdge.from == nodeIndex || anEdge.to == nodeIndex) {
            return true;
         }
      }
      return false;
   }

   private void edgesSlotPrepare(int nodeindex) {
      padTo(edges, nodeindex+1);
      if (edges.get(nodeindex) == null) {
         edges.set(nodeindex, new ArrayList<Edge>());
      }
   }

   private void padTo(List<?> list, int size) {
       for (int i=list.size(); i<size; i++)
           list.add(null);
   }
}

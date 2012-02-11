package com.salas;

import static com.google.common.base.Preconditions.*;
import java.util.*;

public class Level {
   private BaseGraph <Intersection, Road> graph;
	private int nextIntersectionIndex = 0;
	
	Level() {
		graph = new BaseGraph<Intersection, Road>(new ArrayList<Intersection>(), new ArrayList<ArrayList<Road>>());
	}
	
	void addIntersection(String name, float x, float y) {
	   int inter = graph.getNode(name);
	   if (inter == BaseNode.INVALID) {
	      graph.addNode(new Intersection(nextIntersectionIndex++, name, x, y));
	   } else {
	      Intersection interSect = graph.getNode(inter);
	      interSect.setPos(x, y);
	   }
	}
	
	void addIntersection(String name) {
      int inter = graph.getNode(name);
      if (inter == BaseNode.INVALID) {
         graph.addNode(new Intersection(nextIntersectionIndex++, name));
      }
	}
	
	void addRoad(String roadname, String xname1, String xname2) {
	   addIntersection(xname1);
	   addIntersection(xname2);
	   checkArgument(graph.hasNode(xname1) && graph.hasNode(xname2), "addRoad: unknown xections: '%s' or '%s'", xname1, xname2);
	   int xectn1 = graph.getNode(xname1);
	   int xectn2 = graph.getNode(xname2);
	   graph.addEdge(new Road(xectn1, xectn2));
	}

   public Intersection getIntersection(String string) {
      int intIdent = graph.getNode(string);
      return graph.getNode(intIdent);
   }

   public int intersectionCount() {
      return graph.nodeCount();
   }

   public boolean intersectionsConnected(String node1, String node2) {
      return graph.connected(node1, node2);
   }

   public void loopOverAllIntersections(IntersectionHandler intersectionHandler) {
      for (Intersection xctn: graph.nodeList()) {
         intersectionHandler.intersection(xctn.name(), xctn.position());
      }
   }
   
   public void loopOverAllRoads(RoadLoopHandler handler) {
      for (ArrayList<Road> eList: graph.edgeList()) {
            for (Road e: eList) {
               handler.road("Road-"+e.from+"-"+e.to, roadEndPoint(e.from), roadEndPoint(e.to));
            }
         }
      }

   private Vector2 roadEndPoint(int i) {
      return graph.getNode(i).position();
   }


   // Interfaces for methods above   
   public interface IntersectionHandler {
      void intersection(String name, Vector2 position);
   }
   
   public interface RoadLoopHandler {
      void road(String name, Vector2 fromVector, Vector2 toVector);
   }
}
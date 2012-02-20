package com.salas;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

import com.salas.graph.*;

public class TestBaseGraph {
   BaseGraph<BaseNode, BaseEdge> graph;
   
  
   
   @Before public void setup() {
      ArrayList<BaseNode> nodes = new ArrayList<BaseNode>();
      ArrayList<ArrayList<BaseEdge>> edges = new ArrayList<ArrayList<BaseEdge>>();
      graph = new BaseGraph<BaseNode, BaseEdge>(nodes, edges);      
   }
   
   @Test
   public void testSucceed() {
      assertTrue(true);
   }
   
   @Test
   public void testAddNode() {
      BaseNode n = new BaseNode(1, "Howdie");
      graph.addNode(n);
      assertTrue (graph.hasNode("Howdie"));
   }
   
   @Test 
   public void testNodeWithoutName() {
      BaseNode n = new BaseNode(1, null);
      graph.addNode(n);
      assertFalse (graph.hasNode("Howdie"));
   }
   
   @Test
   public void testAddEdge() {
      BaseNode[] nodes = new BaseNode[5];
      for (int i=0; i<5; i++) {
         nodes[i] = new BaseNode(i, "node-"+i);
         graph.addNode(nodes[i]);
      }
      for (int i=0; i<4; i++) {
         graph.addEdge(new BaseEdge(nodes[i].index, nodes[i+1].index));
      }
      assertEquals(graph.nodeCount(), 5);
   }
   
   @Test
   public void testAddLittleGraph() {
      BaseNode a = new BaseNode(1, "a");
      BaseNode b = new BaseNode(2, "b");
      BaseNode c = new BaseNode(3, "c");
      BaseNode d = new BaseNode(4, "d");
      graph.addEdge(new BaseEdge(a.index, b.index));
      graph.addEdge(new BaseEdge(a.index, c.index));
      assertEquals(graph.edgesFromCount(a.index), 2);
      assertEquals(graph.edgesFromCount(b.index), 1);
      assertEquals(graph.edgesFromCount(c.index), 1);
      assertEquals(graph.edgesFromCount(d.index), 0);
      
   }
   
   
   

}

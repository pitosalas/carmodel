package com.salas;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

import com.salas.graph.*;

public class TestBaseGraph {
   BaseGraph<BaseNode, BaseEdge> graph;
   
   class TestEdge extends BaseEdge {

      public TestEdge(int f, int t) {
         super(f, t);
         // TODO Auto-generated constructor stub
      }

      @Override
      protected TestEdge clone() {
         return new TestEdge(this.from, this.to);
      }
   }
   
  
   
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
         graph.addEdge(new TestEdge(nodes[i].index, nodes[i+1].index));
      }
      assertEquals(graph.nodeCount(), 5);
   }
   
   @Test
   public void testEdgesDeDup() {
      // create 2 nodes
      graph.addNode(new BaseNode(1, "a"));
      graph.addNode(new BaseNode(2, "b"));
        
      // Put an edge from each one to the other. So two edges.
      graph.addEdge(new TestEdge(1, 2));
      graph.addEdge(new TestEdge(2, 1));
        
      // Verify that we have two nodes
      assertEquals(2, graph.nodeCount());
      
      // Verify that the edge list has info on those two nodes
      assertEquals(2, graph.edgeListNodeCount());
      
      // Verify that each list has been pruned to remove the redundent edges
      assertEquals(1, graph.edgeList().get(1).size());
      assertEquals(1, graph.edgeList().get(2).size());
      
      // Verify that each edge in each list has the owning node as the 'from'
      assertEquals(1, graph.edgeList().get(1).get(0).from);
      assertEquals(2, graph.edgeList().get(2).get(0).from);
      
   }
   
   @Test
   public void testAddLittleGraph() {
      BaseNode a = new BaseNode(1, "a");
      BaseNode b = new BaseNode(2, "b");
      BaseNode c = new BaseNode(3, "c");
      BaseNode d = new BaseNode(4, "d");
      graph.addEdge(new TestEdge(a.index, b.index));
      graph.addEdge(new TestEdge(a.index, c.index));
      assertEquals(graph.edgesFromCount(a.index), 2);
      assertEquals(graph.edgesFromCount(b.index), 1);
      assertEquals(graph.edgesFromCount(c.index), 1);
      assertEquals(graph.edgesFromCount(d.index), 0);
      
   }
   
   
   

}

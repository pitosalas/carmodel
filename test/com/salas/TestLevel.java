/**
 * 
 */
package com.salas;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * @author pitosalas
 *
 */
public class TestLevel {
   Level l;
   /**
    * @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception {
      l = new Level();
   }

    /**
    * Test method for {@link com.salas.Level#addIntersection(java.lang.String, float, float)}.
    */
   @Test
   public void testAddIntersection() {
      l.addIntersection("hello", 1, 10);
      l.addIntersection("goodbye", 2, 20);
      assertEquals(2, l.intersectionCount());
   }

   /**
    * Test method for {@link com.salas.Level#addRoad(java.lang.String, java.lang.String, java.lang.String)}.
    */
   @Test
   public void testAddRoad() {
      l.addRoad("main", "one", "two");
      l.addRoad("side", "one", "three");
      assertEquals(3, l.intersectionCount());
      assertTrue(l.intersectionsConnected("one", "two"));
      assertTrue(l.intersectionsConnected("one", "three"));
      assertFalse(l.intersectionsConnected("three", "two"));
   }
   
   @Test
   public void testAdding() {
      l.addIntersection("hello", 2,3);
      l.addRoad("myroad", "hello", "goodbye");
      assertTrue(l.intersectionsConnected("hello", "goodbye"));
      assertFalse(l.intersectionsConnected("hello", "foobar"));
   }
   
   @Test
   public void testAddingOther() {
      l.addIntersection("One", 2, 3);
      l.addRoad("me", "one", "two");
      l.addIntersection("two", 99, 88);
      assertTrue(l.intersectionsConnected("one", "two"));
   }

}

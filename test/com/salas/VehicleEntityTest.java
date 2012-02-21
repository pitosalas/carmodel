package com.salas;

import static org.junit.Assert.*;

import org.junit.*;

import com.salas.entity.*;
import com.salas.vehicle.*;
import com.salas.world.*;

public class VehicleEntityTest {
   
   class MyEntityBody extends EntityBody {
      Vector2 pos;

      @Override
      public void createBody(EntitySprite sprite) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public Vector2 getPos() {
         return pos;
      }

      @Override
      public void setPos(Vector2 pos) {
         this.pos = pos;
         
      }

      @Override
      public void setRotation(float degrees) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public Vector2 getVelocity() {
         // TODO Auto-generated method stub
         return null;
      }

      @Override
      public void applyForce(Vector2 steeringForce) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void recalcMass() {
         // TODO Auto-generated method stub
         
      }

      @Override
      public float getHeading() {
         // TODO Auto-generated method stub
         return 0;
      }

      @Override
      public void applyImpulse(Vector2 imp) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public String getTooltTipText() {
         // TODO Auto-generated method stub
         return null;
      }
      
   }
   
   class MyEntitySprite extends EntitySprite {

      @Override
      public void loadResources(World<WorldBodies, WorldSprites> context) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void setPos(Vector2 pos) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void setRotation(float heading) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void createSprite() {
         // TODO Auto-generated method stub
         
      }
      
   }
   
   VehicleEntity cot;
   EntityBody bod;
   EntitySprite spr;

   @Before
   public void setUp() throws Exception {
      bod = new MyEntityBody();
      spr = new MyEntitySprite();
      cot = new VehicleEntity(null, bod, spr);
   }

   @Test
   public void testHandleMessage() {
      fail("Not yet implemented"); // TODO
   }

   @Test
   public void testVehicleEntity() {
      fail("Not yet implemented"); // TODO
   }

   @Test
   public void testSetStartingPos() {
      cot.setStartingPos(2.0f, 3.0f);
      assertEquals(3.0f, cot.getStartingPos().y, 0.1f);
   }

   @Test
   public void testSetStartingRotation() {
      fail("Not yet implemented"); // TODO
   }

   @Test
   public void testSetName() {
      fail("Not yet implemented"); // TODO
   }

   @Test
   public void testSetPos() {
      cot.setPos(new Vector2(1.0f, 1.0f));
      assertEquals(1.0f, cot.getPos().x,  0.0f);
   }

   @Test
   public void testSetRotation() {
      fail("Not yet implemented"); // TODO
   }

   @Test
   public void testStart() {
      fail("Not yet implemented"); // TODO
   }

}

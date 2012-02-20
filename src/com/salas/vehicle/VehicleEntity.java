package com.salas.vehicle;

import com.salas.*;
import com.salas.entity.*;
import com.salas.messageing.*;
import com.salas.statemachine.*;
import com.salas.world.*;

public class VehicleEntity extends BaseEntity {

   public StateMachine<VehicleEntity> state;

   SteeringBehaviors steering;
   public Vector2 startingPos;
   public float startingRotation;
   String name;

   public VehicleEntity(World gameCtx, EntityBody ebody, EntitySprite esprite) {
      super(gameCtx, ebody, esprite);
      startingPos = new Vector2();
      steering = new SteeringBehaviors(this, world);

      state = new StateMachine<VehicleEntity>(this);
      state.setGlobalState(new VehicleGlobalState());
   }
   
   public void updateState() {
      state.update();
   }


   public void setStartingPos(float x, float y) {
      startingPos.x = x;
      startingPos.y = y;
   }
   
   public Vector2 getStartingPos() {
      return startingPos;
   }

   public void setStartingRotation(float degrees) {
      startingRotation = degrees;
   }

   public void setName(String s) {
      name = s;
   }

   public void setPos(Vector2 pos) {
      body.setPos(pos);
      sprite.setPos(pos);
   }
   
   public Vector2 getPos() {
      return body.getPos();
   }

   public void setRotation(float degrees) {
      body.setRotation(degrees);
      sprite.setRotation(degrees);
   }

   public boolean handleMessage(BaseMessage theMessage) {
      return state.handleMessage(theMessage);
   }
   
// Update vehicle dynamics. Called often in the game loop.
   public void update() {
      // Compute the steering force by applying all the steering behaviors
      Vector2 steeringForce = steering.calculate();
            
      // Apply the calculated steering force to the car's entity
      body.applyForce(steeringForce);
      
      // Make the car's sprite (display) point in the direction of motion
      sprite.setRotation(body.getHeading());      
   }

   // Return true if this object is closer to place than "d" meters
   public boolean near(Vector2 place, double d) {
      float dist2 = place.dst2(getPos());
      logI("getPos()= "+getPos()+", dist2="+dist2);
      return (dist2 < d);
   }
}

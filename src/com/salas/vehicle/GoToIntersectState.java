package com.salas.vehicle;

import com.salas.*;
import com.salas.messageing.*;
import com.salas.statemachine.*;
import com.salas.world.*;

public class GoToIntersectState extends State<VehicleEntity> {

   Intersection target;
   Intersection source;
   Vector2 place;
   World<?, ?> world;
   
   public GoToIntersectState(Intersection destination, Intersection origin) {
      
      target = destination;
      source = origin;
      place = target.position();
      name = "Go To Intersection";
   }

   @Override
   public void enter(VehicleEntity t) {
      t.steering.setDestination(place);
      t.steering.disableAll();
      t.steering.enableArrivalSteering();
   }

   @Override
   public void execute(VehicleEntity t) {
      if (t.near(place, SteeringBehaviors.wayPointSeekDistance.getVal())){
         World<?,?> w = World.singleton();
         Intersection newTarget = w.levelMgr.getCurrentLevel().randomNextIntersection(target, source);
         t.state.changeState(new GoToIntersectState(newTarget, target));
        }
   }

   @Override
   public void exit(VehicleEntity t) {
      // TODO Auto-generated method stub

   }

   @Override
   public boolean onMessage(BaseMessage m) {
      // TODO Auto-generated method stub
      return false;
   }

}

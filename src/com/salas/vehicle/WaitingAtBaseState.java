package com.salas.vehicle;

import java.util.*;

import com.salas.*;
import com.salas.messageing.*;
import com.salas.statemachine.*;
import com.salas.world.*;

public class WaitingAtBaseState extends State<VehicleEntity> {

   @Override
   public void enter(VehicleEntity t) {
   // if there are no current emergencies then this vehicle starts patrolling
      ArrayList<Intersection> patrollroute =  World.singleton().department.getPatrollRoute();
      t.state.changeState(new PatrollingState(patrollroute));
   }
   @Override
   public void execute(VehicleEntity t) {
      
   }

   @Override
   public void exit(VehicleEntity t) {
      
   }

   @Override
   public boolean onMessage(BaseMessage m) {
      return false;
   }

}

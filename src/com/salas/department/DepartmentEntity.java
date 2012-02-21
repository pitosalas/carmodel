package com.salas.department;

import java.util.*;

import com.salas.*;
import com.salas.Level.IntersectionHandler;
import com.salas.entity.*;
import com.salas.messageing.*;
import com.salas.messageing.BaseMessage.MessageTypes;
import com.salas.statemachine.*;
import com.salas.vehicle.*;
import com.salas.world.*;

public class DepartmentEntity extends BaseEntity {
   public StateMachine<DepartmentEntity> state;

   public EntityManager<VehicleEntity> vehicles = new EntityManager<VehicleEntity>();
   Vector2 departmentOrigin = new Vector2(3,3);
   
   // Department is an Entity without sprites or body
   public DepartmentEntity(World<?,?> world) {
      super(world, null, null);
      state = new StateMachine<DepartmentEntity>(this);
      state.setGlobalState(new DepartmentGlobalState());

   }

   public void returnAllToBase() {
      for (VehicleEntity v : vehicles.all()) {
         // Send message from Department to each Vehicle, to tell them
         // to travel to the Department's location.
         world.dispatch.dispatchMessage(0.0, new BaseMessage(
               MessageTypes.RETURNTOBASE, this, v, departmentOrigin, null));
      }
   }
   
   public void dispatchMessageToAllVehicles(BaseMessage theMessage) {
      for (VehicleEntity v : vehicles.all()) {
         // Send message from Department to each Vehicle
         theMessage.messageReceiver = v;
         theMessage.messageSender = this;
         world.dispatch.dispatchMessage(0.0, theMessage);
      }
   }

   public void clear() {
      vehicles.clear();
   }

   public void updateAllStateMachines() {
      state.update();                  // Department's own state machine
      vehicles.updateStateMachines();  // Vehicles entity manager's state machines
   }
   
   public void updateState() {
      state.update();
   }

   // Return the patrolling route for this department. It's used when vehicles are in
   // PatrollingState
   public ArrayList<Intersection> getPatrollRoute() {
      Level curLev = World.singleton().levelMgr.getCurrentLevel();
      final ArrayList<Intersection> route = new ArrayList<Intersection>();
      curLev.loopOverAllIntersections(new IntersectionHandler() {

         @Override
         public void intersection(Intersection inter, String name, Vector2 position) {
            route.add(inter);
         } });
      return route;
   }

   @Override
   public String getToolTipText() {
      String s = "State: " + state.toString() + "\n" + body.getTooltTipText();
      return s;
   }
}

package com.salas.world;

import static com.google.common.base.Preconditions.*;

import java.awt.Component.*;

import com.salas.*;
import com.salas.department.*;
import com.salas.messageing.*;
import com.salas.messageing.BaseMessage.*;
import com.salas.vehicle.*;

// World is used to carry context of the overall game. 

public abstract class World<BOD extends WorldBodies, SPRT extends WorldSprites> {

   private static World<?, ?> theWorld = null;

   static public World<?, ?> singleton() {
      checkNotNull(theWorld);
      return theWorld;
   }

   public World() {
      theWorld = this;
   }

   // Message management is at the World level. All messages go through here/
   public MessageDispatcher dispatch = new MessageDispatcher();

   // World has a department which controls all the vehicls
   public DepartmentEntity department = new DepartmentEntity(this);

   public void launchVehicles() {
      for (VehicleEntity v : department.vehicles.all()) {
         v.sprite.createSprite();
         sprites.attach(v.sprite);
         v.body.createBody(v.sprite);
         v.setPos(v.startingPos);
         v.setRotation(v.startingRotation);
      }
      department.dispatchMessageToAllVehicles(new BaseMessage(
            MessageTypes.GOTOINTERSECT, null, null, null, levelMgr
                  .getCurrentLevel().getIntersection("n1")));
   }

   // Implementation model: how is car game instantiated on this platform
   public BOD bodies;
   public SPRT sprites;
   public LevelManager levelMgr;

   public void unloadResources() {
      department.clear();
   }

   // Framework will call this method once LONG_TICK_SECONDS
   public static float LONG_TICK_SECONDS = 5.0f;

   public void longClockTickUpdates() {
      // logI("TICK", "Log Tick Update.");
   }

   // Framework will call this method once MEDIUM_TICK_SECONDS
   public static float MED_TICK_SECONDS = 0.5f;

   public void mediumClockTickUpdates() {
      // logI("TICK", "Medium Tick Update.");
      department.updateAllStateMachines();
   }

   // Framework will call this once per SHORT_TICK_SECONDS
   public static float SHORT_TICK_SECONDS = 0.1f;

   public void shortClockTickUpdates() {
      // logI("TICK", "Short Tick Update.");
      for (VehicleEntity v : department.vehicles.all()) {
         v.update();
      }
   }

   abstract public void logI(String tag, String message);
}

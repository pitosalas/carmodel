package com.salas.vehicle;

import com.salas.*;
import com.salas.messageing.*;
import com.salas.messageing.BaseMessage.*;
import com.salas.statemachine.*;
import com.salas.world.*;

public class ReturnToBaseState extends State<VehicleEntity> {
   Vector2 place;

   public ReturnToBaseState(Vector2 where) {
      place = where;
   }
   
   @Override
   public void enter(VehicleEntity t) {
      t.steering.setDestination(place);
      t.steering.disableAll();
      t.steering.enableArrivalSteering();
   }

   @Override
   public void execute(VehicleEntity t) {
      if (t.near(place, 0.5)){
        t.state.changeState(new WaitingAtBaseState());
       }
   }

   @Override
   public void exit(VehicleEntity t) {
      logI("arrived at base");
   }

   @Override
   public boolean onMessage(BaseMessage m) {
      // TODO Auto-generated method stub
      return false;
   }

}

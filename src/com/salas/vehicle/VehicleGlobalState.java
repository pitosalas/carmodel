package com.salas.vehicle;

import com.salas.messageing.*;
import com.salas.statemachine.*;
import com.salas.vehicle.*;

public class VehicleGlobalState extends State<VehicleEntity> {

   @Override
   public void enter(VehicleEntity t) {
      // TODO Auto-generated method stub
   }

   @Override
   public void execute(VehicleEntity t) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exit(VehicleEntity t) {
      // TODO Auto-generated method stub
   }

   /* 
    * Message dispatcher for Vehicle Class uses the Global VehicleState. If the current
    * state does not respond to a message then it gets dropped into the GlobalState which is supposed 
    * to do. 
    * Return: true if the message was handled and false if no one took it.
   */
   @Override
   public boolean onMessage(BaseMessage m) {
      VehicleEntity target = (VehicleEntity) m.messageReceiver;
      switch (m.type) {
      case RETURNTOBASE:
         target.state.changeState(new ReturnToBaseState(m.vectorOne));
         break;
      case GOTOINTERSECT:
         target.state.changeState(new GoToIntersectState(m.intersectionOne, null));
         break;
       default:
          return false;
         }
      return true;
   }

}

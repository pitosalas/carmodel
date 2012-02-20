package com.salas.vehicle;

public class Experiment {
   class BaseMessage<Messageable> {
      Messageable once;

      BaseMessage(Messageable x) {
         once = x;
      }
   }
   
   class VehicleMessage extends BaseMessage<VehicleEntity> {
      VehicleMessage(VehicleEntity v) {
         super(v);
      }
      
   }

   class StateMachine<Thing> {
      public boolean handleMessage(BaseMessage<Thing> msg) {
         return true;
      }

   }
   class BaseEntity {
   }
   

   class VehicleEntity extends BaseEntity {
      
      public StateMachine<VehicleEntity> state;

      boolean handleMessage(VehicleMessage theMessage) {
         return state.handleMessage(theMessage);
      }
   }
}
package com.salas.entity;

import java.util.*;

public class EntityManager<ENT extends BaseEntity> {
   ArrayList<ENT> entities = new ArrayList<ENT>();
   
   public void registerEntity(ENT entity){
      entities.add(entity);
   }
   
   void removeEntity(ENT entity) {
      entities.remove(entity);
   }

   public ArrayList<ENT> all() {
      return entities;
   }

   public void clear() {
      entities.clear();
   }

   public void updateStateMachines() {
      for (BaseEntity e: entities) {
         e.updateState();
      }
      
   }
}

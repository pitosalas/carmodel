package com.salas.entity;

import com.salas.messageing.*;
import com.salas.statemachine.*;
import com.salas.vehicle.*;
import com.salas.world.*;


public abstract class BaseEntity {	

	public EntityBody body;
	public EntitySprite sprite;
	public World world;

	public BaseEntity(World ctx, EntityBody bdy, EntitySprite sprt) {
		world = ctx;
		body = bdy;
		sprite = sprt;

	}

   public boolean handleMessage(BaseMessage theMessage) {
      return false;
   }
   
   public void logI(String message) {
      world.logI("ENTITY", message);
   }

   public abstract void updateState();
   

}

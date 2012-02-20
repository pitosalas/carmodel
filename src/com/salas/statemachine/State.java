package com.salas.statemachine;

import com.salas.entity.*;
import com.salas.messageing.*;
import com.salas.world.*;

public abstract class State<T extends BaseEntity> {
   
	public abstract void enter(T t);
	public abstract void execute(T t);
	public abstract void exit(T t);
	public abstract boolean onMessage(BaseMessage m);
	
   public void logI(String message) {
      World.singleton().logI("STATE", message);
   }

}
package com.salas.statemachine;

import com.salas.entity.*;
import com.salas.messageing.*;
import static com.google.common.base.Preconditions.*;


public class StateMachine<T extends BaseEntity> {

	private T owner;
	private State<T> currentState;
	private State<T> previousState;
	private State<T> globalState;
	
   public StateMachine(T own) { owner = own; currentState = null; previousState = null; globalState = null; }

   public void update() {
		if (globalState != null) {
			globalState.execute(owner);
		}
		
		if (currentState != null) {
			currentState.execute(owner);
		}
	}
	
	public void changeState(State<T> newState) {
	   checkNotNull(newState);
		previousState = currentState;
		
		// at the beginning of time there is no initial state
		if (currentState!=null) currentState.exit(owner);
		currentState = newState;
		currentState.enter(owner);
	}
	
	void revertToPrev() {
		changeState(previousState);
	}

	public void setCurrentState(State<T> s) { currentState = s; }
	public void setPreviousState(State<T> s) { previousState = s; }
	public void setGlobalState(State<T> s) { globalState = s;}
   public T getOwner() {  return owner; }

	
   public State<T> currentState() { return currentState; }
   public State<T> previousState() { return previousState; }
   public State<T> globalState() { return globalState; }
	
	public boolean handleMessage(BaseMessage msg) {
	   if (currentState != null && currentState.onMessage(msg)) {
	      return true;
	   } else  if (globalState != null && globalState.onMessage(msg)) {
         return true;
      } else {
         return false;
      }
	}
	
	public String toString() {
	   return currentState.toString();
	}
	
}

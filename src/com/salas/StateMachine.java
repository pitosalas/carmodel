package com.salas;

public class StateMachine<StatefulThing> {

	private StatefulThing owner;
	private	State<StatefulThing> currentState;
	private	State<StatefulThing> previousState;
	private State<StatefulThing> globalState;
	
	void update() {
		if (globalState != null) {
			globalState.execute(owner);
		}
		
		if (currentState != null) {
			currentState.execute(owner);
		}
	}
	
	void changeState(State<StatefulThing> newState) {
		assert(newState != null);
		
		previousState = currentState;
		currentState.exit(owner);
		currentState = newState;
		currentState.enter(owner);
	}
	
	void revertToPrev() {
		changeState(previousState);
	}

	StateMachine(StatefulThing own) { owner = own; }
	
	void setCurrentState(State<StatefulThing> s) { currentState = s; }
	void setPreviousState(State<StatefulThing> s) { previousState = s;}
	void setGlobalState(State<StatefulThing> s) { globalState = s;}
	
	State<StatefulThing> currentState() { return currentState; }
	State<StatefulThing> previousState() { return previousState; }
	State<StatefulThing> globalState() { return globalState; }
}

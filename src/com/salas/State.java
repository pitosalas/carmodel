package com.salas;

public abstract class State<T> {
	
	abstract void enter(T t);
	abstract void execute(T t);
	abstract void exit(T t);

}
package com.salas;
import java.util.HashMap;


public class TweakBox {
	static private HashMap<String, TweakBox> boxes = new HashMap<String, TweakBox>();
	String label;
	Float value;
	Float increment;

	public TweakBox(String string, float val, float incr) {
		boxes.put(string, this);
		value = val;
		increment = incr;
		label = string;
	}

	public float getVal() {
		return value;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setVal(float newval) {
		value = newval;
	}
	
	public void increment() {
		value += increment;
		apply();
	}
	
	public void decrement() {
		value -= increment;
		if (value < 0) value = 0.0f;
		apply();
	}
	
	public void apply() {
		
	}

	public static HashMap<String, TweakBox> boxes() {
		return boxes;
	}

	public String fullString() {
		return label + ": " + String.format("%3.1f", value);
	}
}

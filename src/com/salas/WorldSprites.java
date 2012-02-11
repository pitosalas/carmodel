package com.salas;

public abstract class WorldSprites {

	abstract void attach(EntitySprite s);
	
	// A series of funtions to display information to user.
	abstract void showCursorAt(Vector2 loc);
	abstract void showRedVectorFrom(Vector2 origin, Vector2 vector);
	abstract void showGreenVectorFrom(Vector2 origin, Vector2 vector);
	abstract void showRoadGraph(Level toshow);
	abstract void showTooltipA(float x, float y, String text);
	abstract void showTooltipB(float x, float y, String text);
	abstract void log(String string);

	
	void showCursorAt(float x, float y) {
		showCursorAt(new Vector2(x,y));
	}

}

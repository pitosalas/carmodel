package com.salas.world;

import com.salas.*;
import com.salas.entity.*;

public abstract class WorldSprites {

	public abstract void attach(EntitySprite s);
	
	// A series of funtions to display information to user.
	public abstract void showCursorAt(Vector2 loc);
	public abstract void showRedVectorFrom(Vector2 origin, Vector2 vector);
	public abstract void showGreenVectorFrom(Vector2 origin, Vector2 vector);
	public abstract void showRoadGraph(Level toshow);
	public abstract void showTooltipA(float x, float y, String text);
	public abstract void showTooltipB(float x, float y, String text);

	
	public void showCursorAt(float x, float y) {
		showCursorAt(new Vector2(x,y));
	}

}

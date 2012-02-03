package com.salas;

public abstract class EntitySprite {

	public abstract void loadResources(World context);
	public abstract void setPos(Vector2 pos);
	public abstract void setRotation(float heading);
	public abstract void createSprite();
}

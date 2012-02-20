package com.salas.entity;

import com.salas.*;
import com.salas.world.*;

public abstract class EntitySprite {

	public abstract void loadResources(World<WorldBodies, WorldSprites> context);
	public abstract void setPos(Vector2 pos);
	public abstract void setRotation(float heading);
	public abstract void createSprite();
}

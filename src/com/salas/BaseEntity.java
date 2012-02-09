package com.salas;

public abstract class BaseEntity {	

	public World world;
	public EntityBody body;
	public EntitySprite sprite;
	
	Vector2 startingPos = new Vector2();
	float startingRotation;
	
	String name;

	public BaseEntity(World ctx, EntityBody bdy, EntitySprite sprt) {
		world = ctx;
		body = bdy;
		sprite = sprt;
	}
	
	public void setStartingPos(float x, float y) {
		startingPos.x = x;
		startingPos.y = y;
	}
	
	public void setStartingRotation(float degrees) {
		startingRotation = degrees;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public void setPos(Vector2 pos) {
		body.setPos(pos);
		sprite.setPos(pos);
	}
	
	public void setRotation(float degrees) {
		body.setRotation(degrees);
		sprite.setRotation(degrees);
	}
	
	
	
}

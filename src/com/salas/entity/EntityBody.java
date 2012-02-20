package com.salas.entity;

import com.salas.*;

public abstract class EntityBody {
	private float maxSpeed;
	private double maxForce;
	private double maxTurnRate;

	abstract public void createBody(EntitySprite sprite);

	public abstract Vector2 getPos();
	public abstract void setPos(Vector2 pos);
	public abstract void setRotation(float degrees);
	
	public abstract Vector2 getVelocity();
	
	public abstract void applyForce(Vector2 steeringForce);
	public abstract void recalcMass();
	public abstract float getHeading();
	public abstract void applyImpulse(Vector2 imp);
	public abstract String getTooltTipText();

	
	public float maxSpeed() {
		return maxSpeed;
	}
	
	protected void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
}

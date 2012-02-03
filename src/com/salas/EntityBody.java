package com.salas;

public abstract class EntityBody {
	private float maxSpeed;
	private double maxForce;
	private double maxTurnRate;

	abstract public void createBody(EntitySprite sprite);

	abstract Vector2 getPos();
	abstract void setPos(Vector2 pos);
	abstract void setRotation(float degrees);
	
	abstract Vector2 getVelocity();
	
	abstract void applyForce(Vector2 steeringForce);
	abstract void recalcMass();
	abstract float getHeading();
	abstract void applyImpulse(Vector2 imp);
	abstract String getTooltTipText();

	
	public float maxSpeed() {
		return maxSpeed;
	}
	
	protected void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
}

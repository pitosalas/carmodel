package com.salas;
import com.salas.TileModel.TDir;

// Abstract representation of a Physics entity. Meant to abstract out any particular physics engine.
// Instantiate a concrete instance of this with physics logic.

public abstract class Entity {
	
	private float maxSpeed;
	private double maxForce;
	private double maxTurnRate;

	abstract Vector2 getPos();
	abstract Vector2 getVelocity();
	abstract void applyForce(Vector2 steeringForce);
	abstract void setPosAndRotatation(TPos pos, TDir rot);
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

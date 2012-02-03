package com.salas;

public class VehicleEntity extends BaseEntity {

	SteeringBehaviors steering;
	
	public VehicleEntity(World gameCtx, EntityBody ebody, EntitySprite esprite) {
		super(gameCtx, ebody, esprite);
		world.vehicles.add(this);
	}
	
	public void start() {
		steering = new SteeringBehaviors(this, world);
		steering.path = new Path(true, new Vector2(5, 5), new Vector2(5, 22), new Vector2(22, 22), new Vector2(22, 5));
	}


}

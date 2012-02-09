package com.salas;

import org.anddev.andengine.engine.handler.timer.TimerHandler;

public class VehicleUpdateHandler {
	private VehicleEntity vehicle;

	public VehicleUpdateHandler(VehicleEntity theVehicle) {
		vehicle = theVehicle;
	}

	public void onTimePassed(TimerHandler timerHandler) {
		
		// Compute the steering force by applying all the steering behaviors
		Vector2 steeringForce = vehicle.steering.calculate();
				
		// Apply the calculated steering force to the car's entity
		vehicle.body.applyForce(steeringForce);
		
		// Make the car's sprite (display) point in the direction of motion
		vehicle.sprite.setRotation(vehicle.body.getHeading());		
	}


}

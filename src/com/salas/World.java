package com.salas;

import java.util.ArrayList;

// World is used to carry context of the overall game. 

public abstract class World {
	
// Domain model: information about the game semnatics and AI
		public static ArrayList<VehicleEntity> vehicles = new ArrayList<VehicleEntity>();
		
		public void prepareVehicles() {
			for (VehicleEntity v : World.vehicles) {
				v.sprite.createSprite();
				sprites.attach(v.sprite);
				v.body.createBody(v.sprite);
				v.setPos(v.startingPos);
				v.setRotation(v.startingRotation);
				v.start();
				registerUpdateHandler(1.0f/10, createVehicleUpdateHandler(v));
			}
		}

// Implementation model: how is car game instantiated on this platform
		WorldBodies bodies;
		WorldSprites sprites;

		abstract void registerUpdateHandler(WorldBodies bodies);
		abstract public void registerUpdateHandler(float secondInterval, VehicleUpdateHandler handler);
		abstract VehicleUpdateHandler createVehicleUpdateHandler(VehicleEntity v);
		
		void unloadResources() {
			vehicles.clear();
		}
}

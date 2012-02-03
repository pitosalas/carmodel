package com.salas;

public class SteeringBehaviors {
	
	static TweakBox wayPointSeekDistance = new TweakBox("WayPtDist", 1.5f, 0.5f);
	static TweakBox decelTweak = new TweakBox("DecelTwk", 20.0f, 1.0f);
	static TweakBox decelBeginDist = new TweakBox("DecelBegDist", 3.0f, 0.5f);
	static TweakBox maxSteeringForce = new TweakBox("MaxSteering", 70.0f, 10.0f);
		
	public BaseEntity entity;
	public World world;
	public Path path;
	
	public SteeringBehaviors(BaseEntity ent, World ctx) {
		entity = ent;
		world = ctx;
	}

	public Vector2 calculate2() {
		Vector2 target = new Vector2(10,10);
		Vector2 steer = arrive(target, Deceleration.fast);
		world.sprites.showCursorAt(target);
		world.sprites.showTooltipA(entity.body.getPos().x, entity.body.getPos().y, entity.body.getTooltTipText());
		return steer;
	}

	public Vector2 calculate() {
		world.sprites.showPath(path);
		Vector2 steer = followPath(path);
		steer.truncate(maxSteeringForce.getVal());

		// Display some helpful info
		world.sprites.showRedVectorFrom(entity.body.getPos(), steer);
		world.sprites.showTooltipA(entity.body.getPos().x, entity.body.getPos().y, entity.body.getTooltTipText());
		return steer;
	}
	
/*
 * Each of these methods return a Force to be applied to the entity to get it one step towards it's goal.
 */
	
/*
 * followPath: Follows the specified path
 */
	private Vector2 followPath(Path apath) {
		Vector2 currWayPoint = apath.getCurrentWayPoint();
		Vector2 pos = entity.body.getPos();

		// If we are close enough to current way point (i.e. current destination) then move focus to the next waypoint
		float dist = pos.dst(currWayPoint);
		if ( dist < wayPointSeekDistance.getVal()) {
			currWayPoint = apath.getNextWayPoint();
		}
		// If we are not finished yet, then seek to the next waypoint, else arrive at it.
		world.sprites.showCursorAt(currWayPoint);
		if (apath.finished()) {
			return arrive(currWayPoint.cpy(), Deceleration.fast);
		} else {
			return arrive(currWayPoint.cpy(), Deceleration.fast);
		}
	}

/*
 * Heads directly to targetPos
 */
	public Vector2 seek(Vector2 targetPos) {
		Vector2 desVel = targetPos.cpy();
		desVel.sub(entity.body.getPos());
		desVel.nor();
		desVel.mul(entity.body.maxSpeed());
		Vector2 curVel = entity.body.getVelocity();
		Vector2 steeringForce = desVel.sub(curVel);
		world.sprites.showGreenVectorFrom(entity.body.getPos(), steeringForce);
		return steeringForce; 
	}
	
	public enum Deceleration {
		slow (3), normal (2), fast (1);
		int decel;
		private Deceleration(int num) {
			decel = num;
		}
		float decelSeconds() {
			return decel * decelTweak.getVal();
		}
	}
	
/*
 * Goes towards target and slows as it approaches it. 
 */
	public Vector2 arrive(Vector2 targetPos, Deceleration decel) {
		// Compute vector from current position to target
		Vector2 toTarget = targetPos.cpy();
		toTarget.sub(entity.body.getPos());
		
		// Compute the distance
		double dist = toTarget.len();
		
		if (dist > 0) {
			// If we are not yet inside the deceleration distance, go full speed. Otherwise,
			// calculate speed required to reach the target given the desired deceleration.
			// Speed is in meters per second.
			double speed;
			if (dist > decelBeginDist.getVal()) {
				speed = entity.body.maxSpeed();
			} else {
				speed = dist / decel.decelSeconds();
			}
			
			// Make sure speed doesn't exceed maximum for this entity
			speed = Math.min(speed, entity.body.maxSpeed());

			String tt = String.format("Sp:%3.1f, Ds:%3.1f, \nDcl:%3.1f, MaxSp:%3.1f",
	    				   speed, dist, decel.decelSeconds(), entity.body.maxSpeed());
			world.sprites.showTooltipB(entity.body.getPos().x, entity.body.getPos().y, tt);

			// Now compute the desired velocity vector
			Vector2 desiredVelocity = toTarget.mul((float)(speed / dist));
			world.sprites.showGreenVectorFrom(entity.body.getPos(), desiredVelocity);
			Vector2 steeringForce = desiredVelocity.sub(entity.body.getVelocity());
			world.sprites.showRedVectorFrom(entity.body.getPos(), steeringForce);
			return steeringForce;
		} else {
			return Vector2.zero;
		}
	}

}

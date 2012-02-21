package com.salas.vehicle;

import java.util.*;

import com.salas.*;
import com.salas.messageing.*;
import com.salas.statemachine.*;

public class PatrollingState extends State<VehicleEntity> {

	private ArrayList<Intersection> pRoute;

   public PatrollingState(ArrayList<Intersection> patrollroute) {
	   pRoute = patrollroute;
   }

   @Override
   public void enter(VehicleEntity t) {
      t.steering.disableAll();
      t.steering.enablePatrollingSteering();
      t.steering.setPatrollingRoute(pRoute);
	}

	@Override
	public void execute(VehicleEntity t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(VehicleEntity t) {
		// TODO Auto-generated method stub

	}

   @Override
   public boolean onMessage(BaseMessage aMessage) {
      // TODO Auto-generated method stub
      return false;
   }

}

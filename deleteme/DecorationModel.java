package com.salas;

import java.util.ArrayList;

public class DecorationModel {
	private int xPos, yPos, height, width;
	private String name;
	private ArrayList<PlacementModel> placements;


	public DecorationModel(String name, int xPos, int yPos, int height, int width) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.height = height;
		this.width = width;
		this.name = name;
		this.placements = new ArrayList<PlacementModel>();
	}
	
	public void addAPlacement(TCoord place, float xOffset, float yOffset) {
		placements.add(new PlacementModel(place, xOffset, yOffset));
	}
	
	public ArrayList<PlacementModel> getPlacements() {
		return placements;
	}
	
	public String getName() {
		return name;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}


}

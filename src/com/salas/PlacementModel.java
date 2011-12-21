package com.salas;

public class PlacementModel {
	TCoord coord;
	float xOffset, yOffset;
	TPos offset;

	public PlacementModel(TCoord coord, float xOffset, float yOffset) {
		this.coord = coord;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}

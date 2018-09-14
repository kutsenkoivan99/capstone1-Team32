package de.openhpi.capstone1.game.model;

public class Coordinate2d {
	public float x;
	public float y;
	
	public Coordinate2d(float xValue, float yValue){
		x=xValue;
		y=yValue;
	}
	
	public Coordinate2d add(Coordinate2d coor) {
		this.x += coor.x;
		this.y += coor.y;
		return this;
	}

}

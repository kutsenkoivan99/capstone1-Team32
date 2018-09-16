package de.openhpi.capstone1.game.model;

public class Coordinate2d {
	public float x;
	public float y;
	
	
	public Coordinate2d(float xValue, float yValue){
		x=xValue;
		y=yValue;
	}
	
	public Coordinate2d() {
		// TODO Auto-generated constructor stub
	}

	public static Coordinate2d add(Coordinate2d pos1, Coordinate2d pos2) {
		return new Coordinate2d(pos1.x + pos2.x, pos1.y +pos2.y);
	}
	
	public static Coordinate2d add(Coordinate2d position, float xSize, float ySize) {
		return new Coordinate2d(position.x+xSize,position.y+ySize);
	}
	
	public Coordinate2d add(Coordinate2d coor) {
		this.x += coor.x;
		this.y += coor.y;
		return this;
	}

	public static Coordinate2d sub(Coordinate2d pos1, Coordinate2d pos2) {
		return new Coordinate2d(pos1.x - pos2.x, pos1.y -pos2.y);
	}
	public static Coordinate2d sub(Coordinate2d position, float xSize, float ySize) {
		return new Coordinate2d(position.x-xSize,position.y-ySize);
	}
	
	public Coordinate2d sub(Coordinate2d coor) {
		this.x -= coor.x;
		this.y -= coor.y;
		return this;
	}

	public float mag() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Coordinate2d copy() {
		// TODO Auto-generated method stub
		return null;
	}

	public Coordinate2d normalize() {
		// TODO Auto-generated method stub
		return null;
	}

	public Coordinate2d mult(float distanceCorrection) {
		// TODO Auto-generated method stub
		return null;
	}

	public float heading() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static Coordinate2d rotate(Coordinate2d vector, Coordinate2d center, float angle) {
		// TODO Auto-generated method stub
		return null;
	}

	public void rotate(float angle) {
		double theta = Math.atan2(-y, x);
		double lenght = Math.sqrt((x*x)+(y*y));
		double alpha = Math.toRadians(angle);
		x = (float) ( Math.cos(theta-alpha) *lenght);
		y = (float) ( -(Math.sin(theta-alpha) *lenght)); 
		
	}


}

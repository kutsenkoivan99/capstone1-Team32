package de.openhpi.capstone1.game.model;

import processing.core.PApplet;

public abstract class Obstacle implements Drawable {

	protected Coordinate2d location;
	Coordinate2d velocity;
	int color = 0;
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
		System.out.println(this.color);
	}
	public void setColor(int r, int g, int b) {
//		this.color = -((r*256+g)*256 +b);
//		this.color = (((255<<8)&r<<8)&g<<8)&b;
		this.color = (((255<<8)|r)<<8|g)<<8|b;
		System.out.println(this.color);
	}

	@Override
	abstract public void draw(PApplet p);
	
	public void move(){
		getLocation().add(velocity);
	}
	public void wrap(float xStart, float xEnd, float yStart, float yEnd) {
		  if (location.x > xEnd) {
			    location.x= xStart;
			  } else if (location.x < xStart) {
				  location.x= xEnd;
			}
		  if (location.y > yEnd) {
			    location.y= yStart;
			  } else if (location.y < yStart) {
				  location.y= yEnd;
			}
		  

	}
	public void setVelocity(float x, float y) {
		velocity.x = x;
		velocity.y = y;
	}
	public void setVelocity(Coordinate2d dist) {
		velocity.x = dist.x;
		velocity.y = dist.y;
	}

	public Coordinate2d getLocation() {
		return location;
	}

	public void setLocation(Coordinate2d location) {
		this.location = location;
	}
}

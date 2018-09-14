package de.openhpi.capstone1.game.model;

import processing.core.PApplet;

public class Rectangle extends Obstacle implements Drawable {

	float ySize;
	float xSize;
	public Rectangle(float x, float y, float xSize, float ySize){
		location= new Coordinate2d(x,y);
		this.xSize=xSize;
		this.ySize = ySize;
		velocity= new Coordinate2d(0f,0f);
	}
	@Override
	public void draw(PApplet p) {
		  p.fill(color);
		  p.rect(getLocation().x,getLocation().y,xSize,ySize);

	}

}

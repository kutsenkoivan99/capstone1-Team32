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
		  float c = (PApplet.radians (angle));
		  p.pushMatrix();
		  p.translate(getLocation().x+(xSize/2), getLocation().y+(ySize/2));
		  p.rotate(c);
		  p.rect(-(xSize/2),-(ySize/2),xSize,ySize);
		  p.popMatrix();
//		  c = PApplet.cos(PApplet.radians (360f -angle));
//		  p.rotate(c);
//		  p.translate(-(getLocation().x+(xSize/2)), -(getLocation().y+(ySize/2)));
//		  p.translate(-xSize/2, -ySize/2);
//		  p.rotate(-c);


	}

}

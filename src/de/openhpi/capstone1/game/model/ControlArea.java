package de.openhpi.capstone1.game.model;


import processing.core.PApplet;

public class ControlArea extends AlingedFigures {
	
	public float ySize;
	public float xSize;
	
	public ControlArea(float x, float y, float xSize, float ySize){
		position= new Vector2d(x,y);
		this.xSize=xSize;
		this.ySize = ySize;
	}
	@Override
	public void draw(PApplet p) {
		  p.noStroke();
		  p.fill(color);
		  p.rect(position.x,position.y,xSize,ySize);
		  p.stroke(0);
		  for(Drawable drawable: children) {
			  drawable.draw(p);
		  }
	}

	@Override
	public Vector2d getCenter() {
		return Vector2d.add(position, xSize/2, ySize/2);
	}


}

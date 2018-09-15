package de.openhpi.capstone1.game.model;

import processing.core.PApplet;

public class MousePaddle extends Rectangle implements Drawable {

	public MousePaddle(float x, float y, float xSize, float ySize) {
		super(x, y, xSize, ySize);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void draw(PApplet p) {
		location.x = p.mouseX - (xSize/2);
		super.draw(p);
	}

}

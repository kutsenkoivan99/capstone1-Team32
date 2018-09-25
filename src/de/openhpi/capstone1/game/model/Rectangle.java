package de.openhpi.capstone1.game.model;

import processing.core.PApplet;

public class Rectangle extends Obstacle {

	public float ySize;
	public float xSize;
	public Rectangle(float x, float y, float xSize, float ySize) {
		position = new Vector2d(x, y);
		this.xSize = xSize;
		this.ySize = ySize;
		velocity = new Vector2d(0f, 0f);
	}

	@Override
	public void draw(PApplet p) {
		if (invert > 0) {
			invert--;
			p.fill((-color) - 1);
		} else {
			p.fill(color);
		}
		float c = (PApplet.radians(angle));
		p.pushMatrix();
		p.translate(getPosition().x + (xSize / 2), getPosition().y + (ySize / 2));
		p.rotate(c);
		p.rect(-(xSize / 2), -(ySize / 2), xSize, ySize);
		p.popMatrix();

	}

	public void wrap(float xStart, float xEnd, float yStart, float yEnd) {
		if (position.x + (xSize / 2) > xEnd) {
			position.x = xStart - (xSize / 2);
		} else if (position.x < xStart - (xSize / 2)) {
			position.x = xEnd + (xSize / 2);
		}
		if (position.y + (ySize / 2) > yEnd) {
			position.y = yStart - (ySize / 2);
		} else if (position.y < yStart - (ySize / 2)) {
			position.y = yEnd + (ySize / 2);
		}
	}

	public Vector2d getCenter() {
		return Vector2d.add(position, xSize / 2, ySize / 2);
	}

}

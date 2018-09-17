package de.openhpi.capstone1.game.model;

import java.util.Observable;

import de.openhpi.capstone1.game.model.strategy.DetectionStrategy;
import de.openhpi.capstone1.game.model.strategy.ResolutionStrategy;
import processing.core.PApplet;

public abstract class Obstacle extends Observable implements Drawable {
	protected float angle = 0;  // the rotation angle in degrees 0 ... 360
	

	protected Vector2d position;
	protected Vector2d velocity;
	protected int color = 0;
	protected DetectionStrategy detectionStrategy = null;
	protected ResolutionStrategy resolutionStrategy = null;

	@Override
	abstract public void draw(PApplet p);
	
	public void move(){
		getPosition().add(velocity);
	}
	
	public void translate(float x, float y) {
		position.x +=x;
		position.y +=y;
	}

	public void translate(Vector2d vector) {
		position.x +=vector.x;
		position.y +=vector.y;
	}

	
	abstract public void wrap(float xStart, float xEnd, float yStart, float yEnd);
	
	public boolean dedectAndHandleCollision(Drawable drawable) {
		boolean hasCollision = false ;
		if (drawable instanceof Ball) {
			Ball ball = (Ball) drawable;
			if (detectionStrategy != null) {
				hasCollision = detectionStrategy.detectCollision(this, ball);
			}
			if ((resolutionStrategy != null) && hasCollision ) {
				resolutionStrategy.handleCollision(this, ball);
			}
			
		} else {
			System.out.println("dedectAndHandleCollision can not handle the type: "+ drawable.getClass().getName());
		}
		return hasCollision;
	}
	
	// getters and setters
	
	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
		System.out.println(this.color);
	}
	public void setColor(int r, int g, int b) {
		this.color = (((255<<8)|r)<<8|g)<<8|b;
		System.out.println(this.color);
	}

	
	public void setVelocity(float x, float y) {
		velocity.x = x;
		velocity.y = y;
	}
	public void setVelocity(Vector2d dist) {
		velocity.x = dist.x;
		velocity.y = dist.y;
	}

	public Vector2d getPosition() {
		return position;
	}

	public void setPosition(Vector2d location) {
		this.position = location;
	}

	public DetectionStrategy getDetectionStrategy() {
		return detectionStrategy;
	}

	public void setDetectionStrategy(DetectionStrategy detectionStrategy) {
		this.detectionStrategy = detectionStrategy;
	}

	public ResolutionStrategy getResolutionStrategy() {
		return resolutionStrategy;
	}

	public void setResolutionStrategy(ResolutionStrategy resolutionStrategy) {
		this.resolutionStrategy = resolutionStrategy;
	}

	public Vector2d getVelocity() {
		return velocity;
	}

}

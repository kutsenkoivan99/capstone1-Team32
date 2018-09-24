package de.openhpi.capstone1.game.model;

import java.util.Observable;

import de.openhpi.capstone1.game.model.strategy.DetectionStrategy;
import de.openhpi.capstone1.game.model.strategy.ResolutionStrategy;
import processing.core.PApplet;

public abstract class AlingedFigures extends Observable implements Drawable {

	protected Vector2d position;
	protected int color = 0;
	protected DetectionStrategy detectionStrategy = null;
	protected ResolutionStrategy resolutionStrategy = null;
	protected float angle = 0; // in degrees 0.. 360 , clockwise
	protected boolean isVisible = true; // by default new objects are visible
	public AlingedFigures() {
		super();
	}

	public class KillEvent {
		public DrawEdges edge;

		public KillEvent(DrawEdges edge) {
			this.edge = edge;
		}
	}

	public KillEvent buildKillEvent(DrawEdges edge) {
		return new KillEvent(edge);
	}

	@Override
	public abstract void draw(PApplet p);

	@Override
	public boolean dedectAndHandleCollision(Drawable drawable) {
		boolean hasCollision = false;
		// only work on visible objects
		if (isVisible) {
			if (drawable instanceof Ball) {
				Ball ball = (Ball) drawable;
				if (detectionStrategy != null) {
					hasCollision = detectionStrategy.detectCollision(this, ball);
				}
				if ((resolutionStrategy != null) && hasCollision) {
					resolutionStrategy.handleCollision(this, ball);
				}

			} else {
				System.out.println("dedectAndHandleCollision can not handle the type: " + drawable.getClass().getName());
			}
		}
		return hasCollision;
	}

	public int getColor() {
		return color;
	}

	@Override
	public void setColor(int color) {
		this.color = color;
		//System.out.println(this.color);
	}

	@Override
	public void setColor(int r, int g, int b) {
		this.color = (((255 << 8) | r) << 8 | g) << 8 | b;
		//System.out.println(this.color);
	}

	@Override
	public Vector2d getPosition() {
		return position;
	}

	@Override
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

	@Override
	public float getAngle() {
		return angle;
	}

	@Override
	public boolean isVisible() {
		return isVisible;
	}

	@Override
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;

	}

	@Override
	public void move() {
		// do nothing

	}

	public void addChild(Drawable drawable) {
		if (!children.contains(drawable)) {
			drawable.getPosition().add(this.position);
			children.add(drawable);
		}
	}

	public void removeChild(Drawable drawable) {
		if (children.contains(drawable)) {
			children.remove(drawable);
			drawable.getPosition().sub(this.position);
		}
	}

	public void changed() {
		this.setChanged();
	}
}
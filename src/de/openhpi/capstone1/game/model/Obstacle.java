package de.openhpi.capstone1.game.model;



public abstract class Obstacle extends AlingedFigures implements Drawable {
	protected Vector2d velocity;
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
	
	
	
	// getters and setters
	/**set the drawing angle. The angle is measured clockwise. The object will be rotated by this angle before it will be drawn.
	 * @param angle the drawing angle
	 */
	public void setAngle(float angle) {
		this.angle = angle;
	}

	public void setVelocity(float x, float y) {
		velocity.x = x;
		velocity.y = y;
	}
	public void setVelocity(Vector2d dist) {
		velocity.x = dist.x;
		velocity.y = dist.y;
	}

	public Vector2d getVelocity() {
		return velocity;
	}

}

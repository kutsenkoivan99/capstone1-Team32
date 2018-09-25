package de.openhpi.capstone1.game.model;

public abstract class Obstacle extends AlingedFigures {
	protected Vector2d velocity;
	private boolean hasCollision = false;
	protected int invert =0;
	
	public class ObstacleEvent {
		public int value;
		
		public ObstacleEvent(int value){
			this.value =value;
		}
	}
	
	public ObstacleEvent buildObstacleEvent(int value) {
		return new ObstacleEvent(value);
	}

	
	
	@Override
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

	/**check if the Obstacle has a collision. This check helps to avoid counting a single collision multiple times.
	 * if it is already set, the collision is already handled.
	 * @return true if this collision was already dedected.
	 */
	public boolean hasCollision() {
		return hasCollision ;
	}
	
	/**Set the collision flag to indicate, that a collision has been detected and has already been resolved
	 * Sets the hasCollision flag of the Obstacle to true
	 */
	public void setCollision() {
		hasCollision = true ;
		invert=10;
	}
	
	/**Clears the collision flag to indicate, that there is currently no collision. This should be done 
	 * in the detection strategy
	 * Sets the hasCollision flag of the Obstacle to false
	 */
	public void clearCollision() {
		hasCollision = false ;
	}

}

package de.openhpi.capstone1.game.model;

import processing.core.PApplet;

public class Ball extends Obstacle  {
	public float radius;

	public Ball(float x, float y, float radius) {
		position = new Vector2d(x, y);
		this.radius = radius;
		velocity = new Vector2d(0f, 0f);
	}

	@Override
	public void draw(PApplet p) {
		if (isVisible) {
			p.fill(color);
//		    p.noStroke(); // removes the line around
			p.ellipse(position.x, position.y, radius * 2, radius * 2);
		}
	}

	public void wrap(float xStart, float xEnd, float yStart, float yEnd) {
		if (position.x + (radius) > xEnd) {
			position.x = xStart - (radius);
		} else if (position.x < xStart - (radius)) {
			position.x = xEnd + (radius);
		}
		if (position.y + (radius) > yEnd) {
			position.y = yStart - (radius);
		} else if (position.y < yStart - (radius)) {
			position.y = yEnd + (radius);
		}
	}

	public void bounce(float xStart, float xEnd, float yStart, float yEnd) {
		if (position.x > xEnd - radius || position.x < xStart + radius) {
			velocity.x *= -1;
		}
		if (position.y > yEnd - radius || position.y < yStart + radius) {
			velocity.y *= -1;
		}
	}

	/**
	 * just copied in to have it in place. Not working yet
	 * 
	 * @param other another Ball
	 */
	void checkCollision(Ball other) {

		// Get distances between the balls components
		Vector2d distanceVect = Vector2d.sub(other.position, position);

		// Calculate magnitude of the vector separating the balls
		float distanceVectMag = distanceVect.length();

		// Minimum distance before they are touching
		float minDistance = radius + other.radius;

		if (distanceVectMag < minDistance) {
			float distanceCorrection = (minDistance - distanceVectMag) / 2.0f;
			Vector2d d = distanceVect.copy();
			Vector2d correctionVector = d.normalize().mult(distanceCorrection);
			other.position.add(correctionVector);
			position.sub(correctionVector);

			// get angle of distanceVect
			float theta = distanceVect.heading();
			// precalculate trig values
			float sine = PApplet.sin(theta);
			float cosine = PApplet.cos(theta);

			/*
			 * bTemp will hold rotated ball positions. You just need to worry about bTemp[1]
			 * position
			 */
			Vector2d[] bTemp = { new Vector2d(), new Vector2d() };

			/*
			 * this ball's position is relative to the other so you can use the vector
			 * between them (bVect) as the reference point in the rotation expressions.
			 * bTemp[0].position.x and bTemp[0].position.y will initialize automatically to
			 * 0.0, which is what you want since b[1] will rotate around b[0]
			 */
			bTemp[1].x = cosine * distanceVect.x + sine * distanceVect.y;
			bTemp[1].y = cosine * distanceVect.y - sine * distanceVect.x;

			// rotate Temporary velocities
			Vector2d[] vTemp = { new Vector2d(), new Vector2d() };

			vTemp[0].x = cosine * velocity.x + sine * velocity.y;
			vTemp[0].y = cosine * velocity.y - sine * velocity.x;
			vTemp[1].x = cosine * other.velocity.x + sine * other.velocity.y;
			vTemp[1].y = cosine * other.velocity.y - sine * other.velocity.x;

			/*
			 * Now that velocities are rotated, you can use 1D conservation of momentum
			 * equations to calculate the final velocity along the x-axis.
			 */
			Vector2d[] vFinal = { new Vector2d(), new Vector2d() };

			// final rotated velocity for b[0]
			// vFinal[0].x = ((m - other.m) * vTemp[0].x + 2 * other.m * vTemp[1].x) / (m +
			// other.m);
			vFinal[0].y = vTemp[0].y;

			// final rotated velocity for b[0]
			// vFinal[1].x = ((other.m - m) * vTemp[1].x + 2 * m * vTemp[0].x) / (m +
			// other.m);
			vFinal[1].y = vTemp[1].y;

			// hack to avoid clumping
			bTemp[0].x += vFinal[0].x;
			bTemp[1].x += vFinal[1].x;

			/*
			 * Rotate ball positions and velocities back Reverse signs in trig expressions
			 * to rotate in the opposite direction
			 */
			// rotate balls
			Vector2d[] bFinal = { new Vector2d(), new Vector2d() };

			bFinal[0].x = cosine * bTemp[0].x - sine * bTemp[0].y;
			bFinal[0].y = cosine * bTemp[0].y + sine * bTemp[0].x;
			bFinal[1].x = cosine * bTemp[1].x - sine * bTemp[1].y;
			bFinal[1].y = cosine * bTemp[1].y + sine * bTemp[1].x;

			// update balls to screen position
			other.position.x = position.x + bFinal[1].x;
			other.position.y = position.y + bFinal[1].y;

			position.add(bFinal[0]);

			// update velocities
			velocity.x = cosine * vFinal[0].x - sine * vFinal[0].y;
			velocity.y = cosine * vFinal[0].y + sine * vFinal[0].x;
			other.velocity.x = cosine * vFinal[1].x - sine * vFinal[1].y;
			other.velocity.y = cosine * vFinal[1].y + sine * vFinal[1].x;
		}
	}

	@Override
	public Vector2d getCenter() {
		// TODO Auto-generated method stub
		return position;
	}

}

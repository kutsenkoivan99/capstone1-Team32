package de.openhpi.capstone1.game.model.strategy;

import de.openhpi.capstone1.game.model.Ball;
import de.openhpi.capstone1.game.model.Drawable;
import de.openhpi.capstone1.game.model.Vector2d;
import de.openhpi.capstone1.game.model.Rectangle;

public class RectReflect implements ResolutionStrategy {

	@Override
	public void handleCollision(Drawable obstacle, Ball ball) {
		// this strategy works only for rectangles
		if (obstacle instanceof Rectangle) {
			Rectangle rect = (Rectangle) obstacle;
			if (!rect.hasCollision()) {
				// change Coordinate system to get the center of the rectangle as Origin 0,0
				Vector2d transformedBallPos = Vector2d.sub(ball.getPosition(), rect.getCenter());
				// check if rect rotated
				float angle = rect.getAngle();
				if (angle != 0.0f) {
					transformedBallPos.rotate(-angle);
					ball.getVelocity().rotate(-angle);
				}
				boolean isX = intersectType(transformedBallPos.x, transformedBallPos.y, ball.radius, -rect.xSize / 2,
						-rect.ySize / 2, rect.xSize / 2, rect.ySize / 2);
				if (isX) {
					ball.getVelocity().y = -ball.getVelocity().y;
					rect.changed();
					rect.notifyObservers(rect.buildObstacleEvent(5));

				} else {
					ball.getVelocity().x = -ball.getVelocity().x;
					rect.changed();
					rect.notifyObservers(rect.buildObstacleEvent(20));
				}
				ball.getVelocity().rotate(angle);
				rect.setCollision();
			}
		} else {
			System.out.println("this stategy:" + this.getClass().getName() + " can only applied to the class Rectangle");
		}

	}

	// return true if bouncing against an border in x direction;
	public boolean intersectType(float cx, float cy, float radius, float left, float top, float right, float bottom) {
		float closestX = (cx < left ? left : (cx > right ? right : cx));
		float closestY = (cy < top ? top : (cy > bottom ? bottom : cy));
		float dx = closestX - cx;
		float dy = closestY - cy;

		return (dx * dx) < (dy * dy);
	}

}

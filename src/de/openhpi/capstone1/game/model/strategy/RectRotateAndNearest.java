package de.openhpi.capstone1.game.model.strategy;

import de.openhpi.capstone1.game.model.Ball;
import de.openhpi.capstone1.game.model.Drawable;
import de.openhpi.capstone1.game.model.Vector2d;
import de.openhpi.capstone1.game.model.Rectangle;

public class RectRotateAndNearest implements DetectionStrategy {

	@Override
	public boolean detectCollision(Drawable obs, Ball ball) {
		Rectangle rect = (Rectangle) obs;
		// change Coordinate system to get the center of the rectangle as Origin 0,0
		Vector2d transformedBallPos = Vector2d.sub(ball.getPosition(), rect.getCenter());
		// check if rect rotated
		float angle = rect.getAngle();
		if (angle !=0.0f) {
			transformedBallPos.rotate(-angle);
			
		}
		boolean isHit= intersects(transformedBallPos.x,transformedBallPos.y,ball.radius,-rect.xSize/2, -rect.ySize/2, rect.xSize/2, rect.ySize/2);
		if (!isHit) {
			rect.clearCollision();
		}
		return isHit;
	}
	public boolean intersects(float cx, float cy, float radius, float left, float top, float right, float bottom)
	{
	   float closestX = (cx < left ? left : (cx > right ? right : cx));
	   float closestY = (cy < top ? top : (cy > bottom ? bottom : cy));
	   float dx = closestX - cx;
	   float dy = closestY - cy;

	   return ( dx * dx + dy * dy ) <= radius * radius;
	}
}

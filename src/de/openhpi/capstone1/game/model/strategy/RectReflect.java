package de.openhpi.capstone1.game.model.strategy;

import de.openhpi.capstone1.game.model.Ball;
import de.openhpi.capstone1.game.model.Coordinate2d;
import de.openhpi.capstone1.game.model.Obstacle;
import de.openhpi.capstone1.game.model.Rectangle;

public class RectReflect implements ResolutionStrategy {

	@Override
	public void handleCollision(Obstacle obs, Ball ball) {
		Rectangle rect = (Rectangle) obs;
		// change Coordinate system to get the center of the rectangle as Origin 0,0
		Coordinate2d transformedBallPos = Coordinate2d.sub(ball.getPosition(), rect.getCenter());
		// check if rect rotated
		float angle = rect.getAngle();
		if (angle !=0.0f) {
			transformedBallPos.rotate(-angle);
			ball.getVelocity().rotate(-angle);
		}
		boolean isX =intersectType(transformedBallPos.x,transformedBallPos.y,ball.radius,-rect.xSize/2, -rect.ySize/2, rect.xSize/2, rect.ySize/2);
		if (isX) {
			ball.getVelocity().y = -ball.getVelocity().y;
		} else {
			ball.getVelocity().x = -ball.getVelocity().x;
		}
		ball.getVelocity().rotate(angle);

	}
	
	// return true if bouncing against an border in x direction;
	public boolean intersectType(float cx, float cy, float radius, float left, float top, float right, float bottom)
	{
	   float closestX = (cx < left ? left : (cx > right ? right : cx));
	   float closestY = (cy < top ? top : (cy > bottom ? bottom : cy));
	   float dx = closestX - cx;
	   float dy = closestY - cy;

	   return dx < dy;
	}

}

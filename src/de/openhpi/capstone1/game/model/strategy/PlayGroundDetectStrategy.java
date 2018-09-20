package de.openhpi.capstone1.game.model.strategy;

import de.openhpi.capstone1.game.model.AlingedFigures;
import de.openhpi.capstone1.game.model.Ball;
import de.openhpi.capstone1.game.model.Rectangle;
import de.openhpi.capstone1.game.model.Vector2d;

public class PlayGroundDetectStrategy implements DetectionStrategy {

	@Override
	public boolean detectCollision(AlingedFigures obs, Ball ball) {
		// Strategy for a PlayGround
		Rectangle rect = (Rectangle) obs;

		// check if borders are hit
		Vector2d ballCenter = ball.getCenter();
		float radius = ball.radius;
		if ((ballCenter.x -radius  <=  rect.getPosition().x) ||
				(ballCenter.x +radius  >=  rect.getPosition().x+rect.xSize) ||
				(ballCenter.y -radius  <=  rect.getPosition().y) ||
				(ballCenter.y +radius  >=  rect.getPosition().y+rect.ySize) 
				) {
			return true;
		}
		return false;
	}

}

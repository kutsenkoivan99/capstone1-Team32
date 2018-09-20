package de.openhpi.capstone1.game.model.strategy;

import de.openhpi.capstone1.game.model.AlingedFigures;
import de.openhpi.capstone1.game.model.Ball;
import de.openhpi.capstone1.game.model.PlayGround;
import de.openhpi.capstone1.game.model.Rectangle;
import de.openhpi.capstone1.game.model.Vector2d;

public class PlayGroundDetectStrategy implements DetectionStrategy {

	@Override
	public boolean detectCollision(AlingedFigures obs, Ball ball) {
		// Strategy for a PlayGround
		PlayGround playGround = (PlayGround) obs;

		// check if borders are hit
		Vector2d ballCenter = ball.getCenter();
		float radius = ball.radius;
		if ((ballCenter.x -radius  <=  playGround.getPosition().x) ||
				(ballCenter.x +radius  >=  playGround.getPosition().x+playGround.xSize) ||
				(ballCenter.y -radius  <=  playGround.getPosition().y) ||
				(ballCenter.y +radius  >=  playGround.getPosition().y+playGround.ySize) 
				) {
			return true;
		}
		return false;
	}

}

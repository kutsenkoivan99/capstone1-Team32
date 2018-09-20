package de.openhpi.capstone1.game.model.strategy;

import java.util.Map;
import java.util.Map.Entry;

import de.openhpi.capstone1.game.model.AlingedFigures;
import de.openhpi.capstone1.game.model.Ball;
import de.openhpi.capstone1.game.model.PlayGround;
import de.openhpi.capstone1.game.model.Rectangle;
import de.openhpi.capstone1.game.model.Vector2d;
import de.openhpi.capstone1.game.model.PlayGround.BORDER_LOC;
import de.openhpi.capstone1.game.model.PlayGround.BORDER_TYPE;

public class PlayGroundResolutionStrategy implements ResolutionStrategy {

	@Override
	public void handleCollision(AlingedFigures obs, Ball ball) {
		// Strategy for a PlayGround
		PlayGround playGround = (PlayGround) obs;
		Map<BORDER_LOC, BORDER_TYPE> borderMap = playGround.getBorderMap();

		// check if borders are hit
		Vector2d ballCenter = ball.getCenter();
		float radius = ball.radius;
		
		for (Entry<BORDER_LOC, BORDER_TYPE> border : borderMap.entrySet()) {
			switch (border.getKey()) {
			case TOP:
				if (ballCenter.y -radius  <=  playGround.getPosition().y) {
					if (border.getValue() == BORDER_TYPE.KILL) {
						handleKill(ball);
					} else {
						ball.getVelocity().y = - ball.getVelocity().y;
					}
				}
				break;
			case BOTTOM:
				if (ballCenter.y +radius  >=  playGround.getPosition().y+playGround.ySize) {
					if (border.getValue() == BORDER_TYPE.KILL) {
						handleKill(ball);
					} else {
						ball.getVelocity().y = - ball.getVelocity().y;
					}
				}
				
				break;
			case LEFT:
				if (ballCenter.x -radius  <=  playGround.getPosition().x) {
					if (border.getValue() == BORDER_TYPE.KILL) {
						handleKill(ball);
					} else {
						ball.getVelocity().x = - ball.getVelocity().x;
					}
				}
				
				break;
			case RIGHT:
				if (ballCenter.x +radius  >=  playGround.getPosition().x+playGround.xSize) {
					if (border.getValue() == BORDER_TYPE.KILL) {
						handleKill(ball);
					} else {
						ball.getVelocity().x = - ball.getVelocity().x;
					}
				}
				
				break;

			}
			
		}

	}

	private void handleKill(Ball ball) {
		ball.setVisible(false);
		
	}

}

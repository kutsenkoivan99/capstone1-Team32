package de.openhpi.capstone1.game.model.strategy;

import java.util.Map;
import java.util.Map.Entry;

import de.openhpi.capstone1.game.model.Ball;
import de.openhpi.capstone1.game.model.Drawable;
import de.openhpi.capstone1.game.model.Drawable.DrawEdges;
import de.openhpi.capstone1.game.model.PlayGround;
import de.openhpi.capstone1.game.model.Vector2d;
import de.openhpi.capstone1.game.model.PlayGround.EdgeType;

public class PlayGroundResolutionStrategy implements ResolutionStrategy {

	@Override
	public void handleCollision(Drawable obs, Ball ball) {
		// Strategy for a PlayGround
		PlayGround playGround = (PlayGround) obs;
		Map<DrawEdges, EdgeType> borderMap = playGround.getBorderMap();

		// check if borders are hit
		Vector2d ballCenter = ball.getCenter();
		float radius = ball.radius;
		DrawEdges killLoc = null;
		
		for (Entry<DrawEdges, EdgeType> border : borderMap.entrySet()) {
			switch (border.getKey()) {
			case TOP:
				if (ballCenter.y -radius  <=  playGround.getPosition().y) {
					if (border.getValue() == EdgeType.KILL) {
						killLoc = DrawEdges.TOP;
					} else {
						ball.getVelocity().y = - ball.getVelocity().y;
					}
				}
				break;
			case BOTTOM:
				if (ballCenter.y +radius  >=  playGround.getPosition().y+playGround.ySize) {
					if (border.getValue() == EdgeType.KILL) {
						killLoc = DrawEdges.BOTTOM;
					} else {
						ball.getVelocity().y = - ball.getVelocity().y;
					}
				}
				
				break;
			case LEFT:
				if (ballCenter.x -radius  <=  playGround.getPosition().x) {
					if (border.getValue() == EdgeType.KILL) {
						killLoc = DrawEdges.LEFT;
					} else {
						ball.getVelocity().x = - ball.getVelocity().x;
					}
				}
				
				break;
			case RIGHT:
				if (ballCenter.x +radius  >=  playGround.getPosition().x+playGround.xSize) {
					if (border.getValue() == EdgeType.KILL) {
						killLoc = DrawEdges.RIGHT;
					} else {
						ball.getVelocity().x = - ball.getVelocity().x;
					}
				}
				
				break;

			}
			
		}
		if ((ball.isVisible()) && (killLoc != null)) {
			ball.setVisible(false);
			ball.setVelocity(0, 0);
			
		playGround.changed();
		playGround.notifyObservers(playGround.buildEdgeEvent(killLoc));  
		}

	}


}

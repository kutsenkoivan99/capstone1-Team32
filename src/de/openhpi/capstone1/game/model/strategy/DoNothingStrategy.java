/**
 * 
 */
package de.openhpi.capstone1.game.model.strategy;

import de.openhpi.capstone1.game.model.Ball;
import de.openhpi.capstone1.game.model.Obstacle;

/**
 * @author walter
 *
 */
public class DoNothingStrategy implements DetectionStrategy, ResolutionStrategy {

	/* (non-Javadoc)
	 * @see de.openhpi.capstone1.game.model.strategy.ResolutionStrategy#handleCollision(de.openhpi.capstone1.game.model.Obstacle, de.openhpi.capstone1.game.model.Ball)
	 */
	@Override
	public void handleCollision(Obstacle obs, Ball ball) {
		
	}

	/* (non-Javadoc)
	 * @see de.openhpi.capstone1.game.model.strategy.DetectionStrategy#detectCollision(de.openhpi.capstone1.game.model.Obstacle, de.openhpi.capstone1.game.model.Ball)
	 */
	@Override
	public boolean detectCollision(Obstacle obs, Ball ball) {
		return false;
	}

}

/**
 * 
 */
package de.openhpi.capstone1.game.model.strategy;

import de.openhpi.capstone1.game.model.AlingedFigures;
import de.openhpi.capstone1.game.model.Ball;

/**
 * @author walter
 *
 */
public class DoNothingStrategy implements DetectionStrategy, ResolutionStrategy {

	/* (non-Javadoc)
	 * @see de.openhpi.capstone1.game.model.strategy.ResolutionStrategy#handleCollision(de.openhpi.capstone1.game.model.Obstacle, de.openhpi.capstone1.game.model.Ball)
	 */
	@Override
	public void handleCollision(AlingedFigures obs, Ball ball) {
		
	}

	/* (non-Javadoc)
	 * @see de.openhpi.capstone1.game.model.strategy.DetectionStrategy#detectCollision(de.openhpi.capstone1.game.model.Obstacle, de.openhpi.capstone1.game.model.Ball)
	 */
	@Override
	public boolean detectCollision(AlingedFigures obs, Ball ball) {
		return false;
	}

}

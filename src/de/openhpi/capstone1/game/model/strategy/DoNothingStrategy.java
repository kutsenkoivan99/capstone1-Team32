/**
 * 
 */
package de.openhpi.capstone1.game.model.strategy;

import de.openhpi.capstone1.game.model.Ball;
import de.openhpi.capstone1.game.model.Drawable;

/**
 * @author 
 *
 */
public class DoNothingStrategy implements DetectionStrategy, ResolutionStrategy {

	/* (non-Javadoc)
	 * @see de.openhpi.capstone1.game.model.strategy.ResolutionStrategy#handleCollision(de.openhpi.capstone1.game.model.Obstacle, de.openhpi.capstone1.game.model.Ball)
	 */
	@Override
	public void handleCollision(Drawable obs, Ball ball) {
		
	}

	/* (non-Javadoc)
	 * @see de.openhpi.capstone1.game.model.strategy.DetectionStrategy#detectCollision(de.openhpi.capstone1.game.model.Obstacle, de.openhpi.capstone1.game.model.Ball)
	 */
	@Override
	public boolean detectCollision(Drawable obs, Ball ball) {
		return false;
	}

}

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
public interface ResolutionStrategy {
	public void handleCollision(AlingedFigures obs, Ball ball);

}

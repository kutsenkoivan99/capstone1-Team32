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
public interface ResolutionStrategy {
	public void handleCollision(Drawable obs, Ball ball);

}

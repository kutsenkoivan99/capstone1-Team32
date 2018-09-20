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
public interface DetectionStrategy {
	public boolean detectCollision(Obstacle obs, Ball ball);

}

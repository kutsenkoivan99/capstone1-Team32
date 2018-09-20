/**
 * 
 */
package de.openhpi.capstone1.game.controller;

import de.openhpi.capstone1.game.model.MousePaddle;
import de.openhpi.capstone1.game.model.Rectangle;
import processing.core.PApplet;

/**
 * @author walter
 *
 */
public class KeyboardPaddleController implements Controller {

	private Rectangle paddle;

	public KeyboardPaddleController(Rectangle paddle) {
		// TODO Auto-generated constructor stub
		this.paddle = paddle;
	}

	/* (non-Javadoc)
	 * @see de.openhpi.capstone1.game.controller.Controller#handleEvent()
	 */
	@Override
	public void handleEvent(PApplet p) {
		if (p.keyPressed) {
			if (p.key == 'a') {
				paddle.setAngle(10f);
			  } else if (p.key == 'f') {
				  paddle.setAngle(-10f);
			  } else if (p.key == 's'){ 
				  paddle.translate(-20f,0);
			  } else if (p.key == 'd'){ 
				  paddle.translate(20f,0);
			  }
		} else {
			paddle.setAngle(0f);
		}
		

	}

}

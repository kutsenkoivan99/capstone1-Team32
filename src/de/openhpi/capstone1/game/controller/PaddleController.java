/**
 * 
 */
package de.openhpi.capstone1.game.controller;

import de.openhpi.capstone1.game.model.MousePaddle;
import processing.core.PApplet;

/**
 * @author walter
 *
 */
public class PaddleController implements Controller {
	public static boolean MOUSE_CLICKED = true;
	public static boolean MOUSE_RELEASED = false;

	private MousePaddle mPaddle;

	public PaddleController(MousePaddle mPaddle) {
		// TODO Auto-generated constructor stub
		this.mPaddle = mPaddle;
	}

	/* (non-Javadoc)
	 * @see de.openhpi.capstone1.game.controller.Controller#handleEvent()
	 */
	@Override
	public void handleEvent(PApplet p, boolean status) {
		if (status == MOUSE_CLICKED) {
			if (p.mouseButton == p.LEFT) {
				mPaddle.setAngle(10f);
			  } else if (p.mouseButton == p.RIGHT) {
				  mPaddle.setAngle(-10f);
			  } else { 
				  mPaddle.setAngle(0f);
			  }
		} else {
			mPaddle.setAngle(0f);
		}
		

	}

}

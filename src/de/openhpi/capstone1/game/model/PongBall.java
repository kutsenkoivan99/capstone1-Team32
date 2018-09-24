package de.openhpi.capstone1.game.model;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import de.openhpi.capstone1.game.controller.KeyboardController;

public class PongBall extends Ball implements  Observer {

	private char[] keyMap;

	public PongBall(float x, float y, float radius, char[] keyMap, KeyboardController controller) {
		super(x, y, radius);
		controller.addObserver(this, keyMap);
		this.keyMap = keyMap;
	}

	@Override
	public void update(Observable o, Object arg) {
		// restart the ball
		this.isVisible=true;
		setPosition(new Vector2d(250f,250f));
		Random rand = new Random();
		int  n = rand.nextInt(100) ;
		float yDir = (((float)n)-50f)/50;
		setVelocity(yDir,2);

	}

}

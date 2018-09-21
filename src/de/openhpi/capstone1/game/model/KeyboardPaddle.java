package de.openhpi.capstone1.game.model;

import java.util.Observable;
import java.util.Observer;

import de.openhpi.capstone1.game.controller.KeyboardController;

public class KeyboardPaddle extends Rectangle implements Drawable, Observer {
	char[] keyMap ;
	int LEFT_FAST=0;
	int LEFT_SLOW=1;
	int RIGHT_SLOW=2;
	int RIGHT_FAST=3;
	int TILD_LEFT=4;
	int TILD_RIGHT=5;

	public KeyboardPaddle(float x, float y, float xSize, float ySize, char[] keyMap, KeyboardController controller) {
		super(x, y, xSize, ySize);
		// register at controller 
		controller.addObserver(this, keyMap);
		this.keyMap = keyMap;
	}

	@Override
	public
	void translate(float x,float y) {
		super.translate(x,y);
		if (position.x < 0 ) position.x = 0;
		if (position.x > 500 - xSize ) position.x = 500 - xSize;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		KeyboardController.KeyInfo keyInfo = (KeyboardController.KeyInfo)arg;
		if (keyInfo.pressed) {
			if (keyInfo.key == keyMap[LEFT_FAST]) {
				this.translate(-40,0);
			} else if (keyInfo.key == keyMap[LEFT_SLOW]) {
				this.translate(-10,0);
			} else if (keyInfo.key == keyMap[RIGHT_SLOW]) {
				this.translate(10,0);
			} else if (keyInfo.key == keyMap[RIGHT_FAST]) {
				this.translate(40,0);
			} else if (keyInfo.key == keyMap[TILD_LEFT]) {
				this.setAngle(10f);
			} else if (keyInfo.key == keyMap[TILD_RIGHT]) {
				this.setAngle(-10f);
			}
		} else {
			if ((keyInfo.key == keyMap[TILD_LEFT])||(keyInfo.key == keyMap[TILD_RIGHT])) {
				this.setAngle(0f);
			}
		}
		
	}

}

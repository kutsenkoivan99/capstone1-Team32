package de.openhpi.capstone1.game.starter;

import de.openhpi.capstone1.game.model.*;
import processing.core.PApplet;

public class TheApp extends PApplet {
	final int PLAYGROUND_X_SIZE = 500;
	final int GAME_Y_SIZE = 500;
	final int CONTROL_X_SIZE = 300;
	Game game = new Game(PLAYGROUND_X_SIZE,CONTROL_X_SIZE , GAME_Y_SIZE);


	@Override
	public void settings() {
		size(PLAYGROUND_X_SIZE+CONTROL_X_SIZE+2, GAME_Y_SIZE+4);
	}

	@Override
	public void setup() { // setup() runs once
		frameRate(60);
		game.build(2); // build 2 player game 
	}

	@Override
	public void draw() { // draw() loops forever, until stopped
		game.draw(this);
	}

	// Add further user interaction as necessary
	@Override
	public void mousePressed() {
		game.mousePressed(this);
	}

	@Override
	public void mouseReleased() {
		game.mouseReleased(this);
	}

	@Override
	public void keyPressed() {
		game.keyPressed(this);
	}

	@Override
	public void keyReleased() {
		game.keyReleased(this);
	}

}

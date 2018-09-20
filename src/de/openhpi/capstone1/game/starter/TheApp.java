package de.openhpi.capstone1.game.starter;

import de.openhpi.capstone1.game.controller.KeyboardPaddleController;
import de.openhpi.capstone1.game.controller.MousePaddleController;
import de.openhpi.capstone1.game.model.*;
import de.openhpi.capstone1.game.model.strategy.PlayGroundDetectStrategy;
import de.openhpi.capstone1.game.model.strategy.PlayGroundResolutionStrategy;
import de.openhpi.capstone1.game.model.strategy.RectReflect;
import de.openhpi.capstone1.game.model.strategy.RectRotateAndNearest;
import processing.core.PApplet;
import processing.core.PFont;

public class TheApp extends PApplet {
	final int PLAYGROUND_X_SIZE = 500;
	final int GAME_Y_SIZE = 500;
	final int CONTROL_X_SIZE = 300;

	Ball ball = new Ball(PLAYGROUND_X_SIZE/2, 100f, 10f);
	MousePaddle mPaddle = new MousePaddle(200f, 390f, 40f, 3f);
	Rectangle kPaddleTop = new Rectangle(PLAYGROUND_X_SIZE/2-20, 10f, 40f, 3f);
	Rectangle kPaddleBottom = new Rectangle(PLAYGROUND_X_SIZE/2-20, GAME_Y_SIZE -10, 40f, 3f);
	private MousePaddleController mPaddleController;
	private KeyboardPaddleController kPaddleController;
	PlayGround playGround = new PlayGround(0f,0f,PLAYGROUND_X_SIZE,GAME_Y_SIZE);
	PFont font;
	

	@Override
	public void settings() {
		size(PLAYGROUND_X_SIZE+CONTROL_X_SIZE+2, GAME_Y_SIZE+4);
	}

	@Override
	public void setup() { // setup() runs once
		frameRate(60);
		mPaddleController = new MousePaddleController(mPaddle);
		kPaddleController = new KeyboardPaddleController(kPaddleTop);
		kPaddleController = new KeyboardPaddleController(kPaddleBottom);
		
		mPaddle.setColor(255, 255, 0);
		mPaddle.setDetectionStrategy(new RectRotateAndNearest());
		mPaddle.setResolutionStrategy(new RectReflect());
		kPaddleTop.setDetectionStrategy(new RectRotateAndNearest());
		kPaddleTop.setResolutionStrategy(new RectReflect());
		kPaddleBottom.setDetectionStrategy(new RectRotateAndNearest());
		kPaddleBottom.setResolutionStrategy(new RectReflect());
		
		ball.setColor(0, 255, 255);
		ball.setVelocity(0f, 2f);

        playGround.setColor(200, 200, 200);
        playGround.setDetectionStrategy(new PlayGroundDetectStrategy());
        playGround.setResolutionStrategy(new PlayGroundResolutionStrategy());
        
        // Create the font
        printArray(PFont.list());
        font = createFont("SansSerif.plain", 24);
        textFont(font);
	}

	@Override
	public void draw() { // draw() loops forever, until stopped
		move();
		handleCollision();
		display();
	}

	void move() {
		ball.move();
	}
	void handleCollision() {
		/* mPaddle.dedectAndHandleCollision(ball);*/
		kPaddleTop.dedectAndHandleCollision(ball);
		kPaddleBottom.dedectAndHandleCollision(ball);
		playGround.dedectAndHandleCollision(ball);
	}

	void display() {
		background(255);
		playGround.draw(this);
		/*  mPaddle.draw(this); */
		kPaddleTop.draw(this);
		kPaddleBottom.draw(this);
		ball.draw(this);
		fill(200,200,200);
		rect(PLAYGROUND_X_SIZE, 0, CONTROL_X_SIZE, GAME_Y_SIZE);
		text("Control-Area", 300, 300);

	}

	// Add further user interaction as necessary
	@Override
	public void mousePressed() {
		// System.out.println("MousePressed = " + mousePressed);
		mPaddleController.handleEvent(this);
	}

	@Override
	public void mouseReleased() {
		mPaddleController.handleEvent(this);
	}

	@Override
	public void keyPressed() {
		// System.out.println("MousePressed = " + mousePressed);
		kPaddleController.handleEvent(this);
	}

	@Override
	public void keyReleased() {
		kPaddleController.handleEvent(this);
	}

}

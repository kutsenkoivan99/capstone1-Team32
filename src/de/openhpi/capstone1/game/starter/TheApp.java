package de.openhpi.capstone1.game.starter;

import de.openhpi.capstone1.game.controller.KeyboardPaddleController;
import de.openhpi.capstone1.game.controller.MousePaddleController;
import de.openhpi.capstone1.game.model.*;
import de.openhpi.capstone1.game.model.strategy.PlayGroundDetectStrategy;
import de.openhpi.capstone1.game.model.strategy.PlayGroundResolutionStrategy;
import de.openhpi.capstone1.game.model.strategy.RectReflect;
import de.openhpi.capstone1.game.model.strategy.RectRotateAndNearest;
import processing.core.PApplet;

public class TheApp extends PApplet {
	int c = color(100, 100, 200);
	float x = 100;
	float y = 150;
	float speed = 1;
	Rectangle rect1 = new Rectangle(80.0f, 120f, 40f, 10f);
	Rectangle rect2 = new Rectangle(300.0f, 250f, 60f, 10f);
	Ball ball = new Ball(100f, 100f, 10f);
	MousePaddle mPaddle = new MousePaddle(200f, 390f, 40f, 3f);
	Rectangle kPaddle = new Rectangle(200f, 10f, 40f, 3f);
	Rectangle topBorder = new Rectangle(0f, 0f, 400, 2);
	private MousePaddleController mPaddleController;
	private KeyboardPaddleController kPaddleController;
	PlayGround playGround = new PlayGround(0f,0f,400f,400f);

	@Override
	public void settings() {
		size(410, 410);
	}

	@Override
	public void setup() { // setup() runs once
		frameRate(60);
		mPaddleController = new MousePaddleController(mPaddle);
		kPaddleController = new KeyboardPaddleController(kPaddle);
		rect1.setVelocity(1f, 1f);
		rect1.setColor(200, 0, 0);
		rect1.setAngle(46f);
		rect1.setDetectionStrategy(new RectRotateAndNearest());
		rect1.setResolutionStrategy(new RectReflect());
		System.out.println(c);
		rect2.setVelocity(1f, 1f);
		rect2.setColor(0, 0, 200);
		rect2.setDetectionStrategy(new RectRotateAndNearest());
		rect2.setResolutionStrategy(new RectReflect());
		ball.setVelocity(0f, 3f);
		mPaddle.setColor(255, 255, 0);
		mPaddle.setDetectionStrategy(new RectRotateAndNearest());
		mPaddle.setResolutionStrategy(new RectReflect());
		kPaddle.setDetectionStrategy(new RectRotateAndNearest());
		kPaddle.setResolutionStrategy(new RectReflect());
		topBorder.setColor(255, 0, 0);
		ball.setColor(0, 255, 255);
        playGround.setColor(200, 200, 200);
        playGround.setDetectionStrategy(new PlayGroundDetectStrategy());
        playGround.setResolutionStrategy(new PlayGroundResolutionStrategy());
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
		rect1.dedectAndHandleCollision(ball);
		rect2.dedectAndHandleCollision(ball);
		mPaddle.dedectAndHandleCollision(ball);
		kPaddle.dedectAndHandleCollision(ball);
		playGround.dedectAndHandleCollision(ball);
	}

	void display() {
		background(255);
		playGround.draw(this);
		rect1.draw(this);
		rect2.draw(this);
		mPaddle.draw(this);
		kPaddle.draw(this);
		ball.draw(this);
		topBorder.draw(this);

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

package de.openhpi.capstone1.game.starter;

import de.openhpi.capstone1.game.controller.KeyboardPaddleController;
import de.openhpi.capstone1.game.controller.MousePaddleController;
import de.openhpi.capstone1.game.model.*;
import de.openhpi.capstone1.game.model.strategy.RectReflect;
import de.openhpi.capstone1.game.model.strategy.RectRotateAndNearest;
import processing.core.PApplet;

public class TheApp extends PApplet {
	int c = color(100, 100, 200);
	float x = 100;
	float y = 150;
	float speed = 1;
	Rectangle car = new Rectangle(80.0f, 120f, 40f, 10f);
	Rectangle car2 = new Rectangle(20.0f, 50f, 20f, 10f);
	Ball ball = new Ball(100f, 100f, 10f);
	MousePaddle mPaddle = new MousePaddle(200f, 390f, 40f, 3f);
	Rectangle kPaddle = new Rectangle(200f, 10f, 40f, 3f);
	private MousePaddleController mPaddleController;
	private KeyboardPaddleController kPaddleController;

	@Override
	public void settings() {
		size(400, 400);
	}

	@Override
	public void setup() { // setup() runs once
		frameRate(60);
		mPaddleController = new MousePaddleController(mPaddle);
		kPaddleController = new KeyboardPaddleController(kPaddle);
		car.setVelocity(1f, 1f);
		car.setColor(200, 0, 0);
		car.setAngle(46f);
		car.setDetectionStrategy(new RectRotateAndNearest());
		car.setResolutionStrategy(new RectReflect());
		System.out.println(c);
		car2.setVelocity(1f, 1f);
		car2.setColor(0, 0, 200);
		ball.setVelocity(0f, 3f);
		mPaddle.setColor(255, 255, 0);
		ball.setColor(0, 255, 255);

	}

	@Override
	public void draw() { // draw() loops forever, until stopped
		background(255);
		move();
		handleCollision();
		display();
	}

	void move() {
//		car.move();
//		car.wrap(0f, 400f, 0f, 400f);
//		car2.move();
//		car2.wrap(0f, 400f, 0f, 400f);
		ball.move();
		ball.bounce(0f, 400f, 0f, 400f);
//		  x = x + speed;
//		  if (car.getLocation().x > width) {
//		    car.setLocation(new Coordinate2d(0f,car.getLocation().y));x = 0;
//		  }
	}
	void handleCollision() {
		car.dedectAndHandleCollision(ball);
	}

	void display() {
//		  fill(c);
//		  rect(x,y,30,10);
		car.draw(this);
//		car2.draw(this);
//		fill(c);
//		rect(x, y, 30, 10);
//		mPaddle.draw(this);
//		kPaddle.draw(this);
		ball.draw(this);

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

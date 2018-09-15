package de.openhpi.capstone1.game.starter;




import de.openhpi.capstone1.game.controller.PaddleController;
import de.openhpi.capstone1.game.model.*;
import processing.core.PApplet;

public class TheApp extends PApplet {
	int c = color(100,100,200);
	float x = 100;
	float y = 150;
	float speed = 1;
	Rectangle car = new Rectangle(20.0f,20f, 30f,10f);
	Rectangle car2 = new Rectangle(20.0f,50f, 20f,10f);
	MousePaddle mPaddle = new MousePaddle(200f, 390f,40f, 3f);
	private PaddleController paddleController;
 	
	@Override
	public void settings() {
		size(400,400);
	}

	@Override
	public void setup() {  // setup() runs once
		frameRate( 60);
		paddleController = new PaddleController(mPaddle);
		car.setVelocity(1f,1f);
		car.setColor(200,0,0);
		car.setAngle(90f);
		System.out.println(c);
		car2.setVelocity(1f,1f);
		car2.setColor(0,0,200);
		mPaddle.setColor(255,	255, 0);
		fill(c);
		rect(x,y,30,10);
		
	}

	@Override
	public void draw() {  // draw() loops forever, until stopped
		background(255);
		  move();
		  display();
	}
	void move() {
//		car.move();
//		car.wrap(0f, 400f, 0f, 400f);
		car2.move();
		car2.wrap(0f, 400f, 0f, 400f);
//		  x = x + speed;
//		  if (car.getLocation().x > width) {
//		    car.setLocation(new Coordinate2d(0f,car.getLocation().y));x = 0;
//		  }
		}

		void display() {
//		  fill(c);
//		  rect(x,y,30,10);
			car.draw(this);
			car2.draw(this);
			fill(c);
			rect(x,y,30,10);
			mPaddle.draw(this);
			
			
			 if (mousePressed == true) {
				    if (mouseButton == LEFT) {
				      fill(0); // Black
				    } else if (mouseButton == RIGHT) { 
				      fill(255); // White
				    }
				  } else {
				    fill(126); // Gray
				  }
				  rect(100, 100, 50, 50);		}	
	//Add further user interaction as necessary
	@Override
	public void mousePressed() {
		//System.out.println("MousePressed = " + mousePressed);
		paddleController.handleEvent(this, PaddleController.MOUSE_CLICKED);
	}
	
	@Override
	public void mouseReleased() {
		paddleController.handleEvent(this, PaddleController.MOUSE_RELEASED);
	}

}

package de.openhpi.capstone1.game.starter;




import de.openhpi.capstone1.game.model.*;
import processing.core.PApplet;

public class TheApp extends PApplet {
	int c = color(100,100,200);
	float x = 100;
	float y = 150;
	float speed = 1;
	Rectangle car = new Rectangle(0.0f,100f, 30f,10f);
	Rectangle car2 = new Rectangle(0.0f,50f, 20f,10f);
	
	@Override
	public void settings() {
		size(200,200);
	}

	@Override
	public void setup() {  // setup() runs once
		car.setVelocity(-1f,1f);
		car.setColor(c);
		System.out.println(c);
		car2.setVelocity(1f,1f);
		car2.setColor(100,100,200);
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
		car.move();
		car.wrap(0f, 100f, 20f, 180f);
//		car2.move();
		car2.wrap(0f, 200f, 0f, 200f);
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
		
		}	
	//Add further user interaction as necessary
	@Override
	public void mouseClicked() {

	}
}

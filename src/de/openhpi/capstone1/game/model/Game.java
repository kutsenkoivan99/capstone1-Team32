package de.openhpi.capstone1.game.model;

import java.util.Observable;
import java.util.Observer;

import de.openhpi.capstone1.game.controller.CounterController;
import de.openhpi.capstone1.game.controller.KeyboardController;
import de.openhpi.capstone1.game.controller.MousePaddleController;
import de.openhpi.capstone1.game.controller.KeyboardController.KeyEvent;
import de.openhpi.capstone1.game.model.PlayGround.EdgeType;
import de.openhpi.capstone1.game.model.strategy.PlayGroundDetectStrategy;
import de.openhpi.capstone1.game.model.strategy.PlayGroundResolutionStrategy;
import de.openhpi.capstone1.game.model.strategy.RectReflectResolutionStrategy;
import de.openhpi.capstone1.game.model.strategy.RectRotateAndNearestDetectionStrategy;
import processing.core.PApplet;

public class Game extends AlingedFigures implements  Observer {
	
	KeyboardController kController;
	char[] topKeys = {'k','l','ö','ä','p','o'};
	char[] bottomKeys = {'a','s','d','f','w','e'};
	char[] newBallKey = {'b'};
	char[] resetKey = {'r'};
	char[] playerKeys = {'1','2'};
	CounterController cController;
	Counter counterTop;
	DrawEdges[] borderListTop = { DrawEdges.TOP};
	Counter counterBottom;
	DrawEdges[] borderListBottom = { DrawEdges.BOTTOM };
	MousePaddleController mPaddleController;
	
	KeyboardPaddle kPaddleTop;
	KeyboardPaddle kPaddleBottom;
	MousePaddle mPaddle;
	PongBall ball;
	Rectangle target;
	Rectangle block1;
	Rectangle block2;
	
	PlayGround playGround;
	ControlArea controlArea;
	
	public Game(int PLAYGROUND_X_SIZE, int CONTROL_X_SIZE,int GAME_Y_SIZE) {
		kController = new KeyboardController();
		kPaddleTop = new KeyboardPaddle(PLAYGROUND_X_SIZE/2-20, 10f, 40f, 3f, topKeys,kController );
		kPaddleBottom = new KeyboardPaddle(PLAYGROUND_X_SIZE/2-20, GAME_Y_SIZE -10, 40f, 3f, bottomKeys,kController );
		mPaddle = new MousePaddle(PLAYGROUND_X_SIZE/2-20, GAME_Y_SIZE -10, 40f, 3f);
		mPaddleController = new MousePaddleController(mPaddle);
		ball = new PongBall(PLAYGROUND_X_SIZE/2, 100f, 10f, newBallKey, kController);
		
		target= new Rectangle(PLAYGROUND_X_SIZE/2-20,50,40,20);
		target.setColor(255,100,0);
		block1 = new Rectangle(PLAYGROUND_X_SIZE/2-30,150,60,20);
		block2= new Rectangle(20,30,40,20);
		block2.setAngle(-45);
		
		
		playGround = new PlayGround(0f,0f,PLAYGROUND_X_SIZE,GAME_Y_SIZE);
		controlArea = new ControlArea(PLAYGROUND_X_SIZE+2,0f, CONTROL_X_SIZE, GAME_Y_SIZE);
		
		cController = new CounterController();
		counterTop = new Counter(10f,20f,120,120,borderListTop);
		counterBottom = new Counter(150f,20f,120,120,borderListBottom);

	}
	
	public void build(int players) {
		mPaddle.setColor(255, 255, 0);
		mPaddle.setDetectionStrategy(new RectRotateAndNearestDetectionStrategy());
		mPaddle.setResolutionStrategy(new RectReflectResolutionStrategy());
		kPaddleTop.setDetectionStrategy(new RectRotateAndNearestDetectionStrategy());
		kPaddleTop.setResolutionStrategy(new RectReflectResolutionStrategy());
		kPaddleBottom.setDetectionStrategy(new RectRotateAndNearestDetectionStrategy());
		kPaddleBottom.setResolutionStrategy(new RectReflectResolutionStrategy());
		target.setDetectionStrategy(new RectRotateAndNearestDetectionStrategy());
		target.setResolutionStrategy(new RectReflectResolutionStrategy());
		block1.setDetectionStrategy(new RectRotateAndNearestDetectionStrategy());
		block1.setResolutionStrategy(new RectReflectResolutionStrategy());
		block2.setDetectionStrategy(new RectRotateAndNearestDetectionStrategy());
		block2.setResolutionStrategy(new RectReflectResolutionStrategy());
		
		ball.setColor(0, 255, 255);
		ball.setVelocity(0f, 2f);

        playGround.setColor(200, 200, 200);
        playGround.setDetectionStrategy(new PlayGroundDetectStrategy());
        playGround.setResolutionStrategy(new PlayGroundResolutionStrategy());

        playGround.addObserver(cController);
        playGround.addChild(ball);

        controlArea.setColor(0,200,200);
        controlArea.addChild(counterBottom);
        cController.addObserver(counterBottom);
        
        kController.addObserver(cController, resetKey);
        kController.addObserver(this, playerKeys);
        
        controlArea.addChild(counterTop);
        cController.addObserver(counterTop);
        
        target.addObserver(cController);

        
        switchGame(players);
	}
	
	void switchGame(int players) {
		switch (players) {
		case 1:
			playGround.getBorderMap().put(DrawEdges.TOP, EdgeType.REFLECT);
			playGround.addChild(mPaddle);
			playGround.addChild(target);
			playGround.addChild(block1);
			playGround.addChild(block2);
			counterTop.changeLabels("Points", "");
			counterBottom.changeLabels("Balls", "");
			
			playGround.removeChild(kPaddleTop);
			playGround.removeChild(kPaddleBottom);
			break;
		case 2:
			playGround.getBorderMap().put(DrawEdges.TOP, EdgeType.KILL);

	        playGround.addChild(kPaddleTop);
	        playGround.addChild(kPaddleBottom);
			playGround.removeChild(mPaddle);
			playGround.removeChild(target);
			playGround.removeChild(block1);
			playGround.removeChild(block2);
			counterTop.changeLabels("Bottom", "Player");
			counterBottom.changeLabels("Top", "Player");

			break;

		default:
			build(2);
			break;
		}
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// handle different Events
		if (arg instanceof KeyEvent) {
			KeyEvent keyEvent = (KeyEvent)arg;
			if (keyEvent.key == '1')switchGame(1);
			if (keyEvent.key == '2')switchGame(2);
		} else{
			System.out.println("can not handle event of type: " + arg.getClass().getName());
		}
	}

	@Override
	public Vector2d getCenter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(PApplet p) {
		playGround.move();
		playGround.dedectAndHandleCollision(ball);
		p.background(255);
		p.fill(200,200,200);
		playGround.draw(p);
		controlArea.draw(p);

	}
	public void mousePressed(PApplet p) {
		// System.out.println("MousePressed = " + mousePressed);
		mPaddleController.handleEvent(p);
	}

	public void mouseReleased(PApplet p) {
		mPaddleController.handleEvent(p);
	}

	public void keyPressed(PApplet p) {
		// System.out.println("MousePressed = " + mousePressed);
		kController.handleEvent(p);
	}

	public void keyReleased(PApplet p) {
		kController.handleEvent(p);
	}
}

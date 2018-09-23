package de.openhpi.capstone1.game.controller;

import java.util.Observable;
import java.util.Observer;

import de.openhpi.capstone1.game.controller.KeyboardController.KeyEvent;
import de.openhpi.capstone1.game.model.AlingedFigures.KillEvent;
import de.openhpi.capstone1.game.model.Drawable.BORDER_LOC;
import de.openhpi.capstone1.game.model.Obstacle.ObstacleEvent;

public class CounterController extends Observable implements Observer {
	public static enum Operation {
		INCREASE, DECREASE, RESET
	};

	public class CounterEvent {

		public int value;
		public Operation operation;
		public BORDER_LOC edge;

		CounterEvent(Operation op, int value, BORDER_LOC edge) {
			this.operation = op;
			this.value = value;
			this.edge = edge;
		}
	}

	public CounterController() {
		super();
	}

	@Override
	public void update(Observable o, Object arg) {
		// handle different Events
		if (arg instanceof KillEvent) {

			KillEvent ki = (KillEvent) arg;
			setChanged();
			notifyObservers(new CounterEvent(Operation.INCREASE, 1, ki.edge)); 
		} else if (arg instanceof KeyEvent) {

			setChanged();
			notifyObservers(new CounterEvent(Operation.RESET, 1, null)); 
		} else if (arg instanceof ObstacleEvent) {
			ObstacleEvent obstacleEvent = (ObstacleEvent) arg;
			setChanged();
			
			notifyObservers(new CounterEvent(Operation.INCREASE, obstacleEvent.value, BORDER_LOC.ALL)); 
		} else {
			System.out.println("can not handle event of type: " + arg.getClass().getName());
		}
	}

}

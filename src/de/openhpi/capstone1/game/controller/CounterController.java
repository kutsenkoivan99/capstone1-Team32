package de.openhpi.capstone1.game.controller;

import java.util.Observable;
import java.util.Observer;

public class CounterController extends Observable implements Observer {
	public static enum Operation {INCREASE, DECREASE, RESET};
	
	public class CounterInfo {
		
		public int value;
		public Operation operation;

		CounterInfo(Operation op, int value) {
			this.operation  = op;
			this.value= value;
		}
	}

	public CounterController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}

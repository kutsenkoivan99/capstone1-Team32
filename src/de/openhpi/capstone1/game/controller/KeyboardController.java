package de.openhpi.capstone1.game.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import processing.core.PApplet;

public class KeyboardController extends Observable implements PAppletController {

	protected Map<Character, Set<Observer>> observers = new HashMap<Character, Set<Observer>>();
	char key = '.';
	boolean pressed = false;

	public class KeyInfo {
		public char key;
		public boolean pressed;

		KeyInfo(char key, boolean pressed) {
			this.key = key;
			this.pressed = pressed;
		}
	}

	@Override
	public void handleEvent(PApplet p) {
		setChanged();
		this.key = p.key;
		this.pressed = p.keyPressed;
		notifyObservers(new KeyInfo(p.key, p.keyPressed));
	}

	/**
	 * register the observers for the given keys
	 * 
	 * @param o    the observer
	 * @param keys the array of keys
	 */
	public void addObserver(Observer o, char[] keys) {
		for (int i = 0; i < keys.length; i++) {
			char key = keys[i];

			if (observers.containsKey(key)) {
				observers.get(key).add(o);
			} else {
				Set<Observer> observerSet = new HashSet<Observer>();
				observerSet.add(o);
				observers.put(key, observerSet);

			}
		}

	}

	/**
	 * Returns the number of observers of this Observable object.
	 * 
	 * @return the number of observers
	 */
	@Override
	public int countObservers() {
		Set<Observer> oSet = new HashSet<Observer>();
		for (Entry<Character, Set<Observer>> letterEntry : observers.entrySet()) {
			oSet.addAll(letterEntry.getValue());
		}
		return oSet.size();
	}

	/**
	 * Deletes an observer from the set of observers of this object.
	 * 
	 * @param o the observer to delete
	 */
	@Override
	public void deleteObserver(Observer o) {
		for (Entry<Character, Set<Observer>> letterEntry : observers.entrySet()) {
			letterEntry.getValue().remove(o);
		}

	}

	/**
	 * Clears the observer list so that this object no longer has any observers.
	 * 
	 */
	@Override
	public void deleteObservers() {
		observers = new HashMap<Character, Set<Observer>>();
	}

	/**
	 * If this object has changed, as indicated by the hasChanged method, then
	 * notify all of its observers and then call the clearChanged method to indicate
	 * that this object has no longer changed.
	 * 
	 */
	@Override
	public void notifyObservers() {
		notifyObservers(new KeyInfo(key, pressed));

	}

	/**
	 * If this object has changed, as indicated by the hasChanged method, then
	 * notify all of its observers and then call the clearChanged method to indicate
	 * that this object has no longer changed.
	 * 
	 * @param arg
	 */
	@Override
	public void notifyObservers(Object arg) {
		KeyInfo ki = (KeyInfo) arg;
		if (hasChanged()) {
			Set<Observer> observerSet = observers.get(ki.key);
			if (observerSet != null) {
			for (Observer observer : observerSet) {
				observer.update(this, arg);
			}}
			clearChanged();
		}

	}

}

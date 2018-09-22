package de.openhpi.capstone1.game.model;

import java.util.Observable;
import java.util.Observer;

import de.openhpi.capstone1.game.controller.CounterController;
import processing.core.PApplet;

public class Counter extends AlingedFigures implements Observer {
	String label;
	int value = 0;
	int textSize = 32;
	public float ySize;
	public float xSize;

	public Counter(float xpos, float ypos, float xSize, float ySize, String label) {
		position = new Vector2d(xpos, ypos);
		this.xSize = xSize;
		this.ySize = ySize;
		this.label = label;
		this.setColor(255);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.openhpi.capstone1.game.model.Drawable#getCenter()
	 */
	@Override
	public Vector2d getCenter() {
		return Vector2d.add(position, xSize / 2, ySize / 2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// handle increase, decrease and reset of counter
		CounterController.CounterInfo counterInfo = (CounterController.CounterInfo) arg1;
		switch (counterInfo.operation) {
		case INCREASE:
			this.value += counterInfo.value;
			break;
		case DECREASE:
			this.value -= counterInfo.value;
			break;
		case RESET:
			this.value = 0;
			break;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.openhpi.capstone1.game.model.AlingedFigures#draw(processing.core.PApplet)
	 */
	@Override
	public void draw(PApplet p) {
		p.fill(color);
		p.rect(position.x, position.y, xSize, ySize);
		p.fill(0);
		p.textSize(textSize);
		p.text(label + "", position.x + 5, position.y + 10 + textSize);
		p.line(position.x, position.y +textSize+20, position.x+xSize, position.y +textSize+20);
		p.text(value + "", position.x + 5, position.y + 20 + (textSize*2));
	}

}

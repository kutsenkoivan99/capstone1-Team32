package de.openhpi.capstone1.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import de.openhpi.capstone1.game.controller.CounterController;
import processing.core.PApplet;

public class Counter extends AlingedFigures implements Observer {
	String label1;
	String label2 = "Player";
	int value = 0;
	int textSize = 32;
	public float ySize;
	public float xSize;
	private List<BORDER_LOC> edgeList;

	public Counter(float xpos, float ypos, float xSize, float ySize, BORDER_LOC[] edge) {
		position = new Vector2d(xpos, ypos);
		this.xSize = xSize;
		this.ySize = ySize;
		this.setColor(255);
		this.edgeList = new ArrayList<BORDER_LOC>();
		for (int i = 0; i < edge.length; i++) {
			this.edgeList.add(edge[i]);
		}

		switch (edge[0]) {
		case TOP:
			label1 = "Bottom";
			break;
		case BOTTOM:
			label1 = "Top";
			break;
		case RIGHT:
			label1 = "Right";
			break;
		case LEFT:
			label1 = "Left";
			break;
		case ALL:
			label1 = "ALL";
			break;
		}
	}

	public void changeLabels(String l1, String l2) {
		if (l1 != null) {
			label1 = l1;
		}
		if (l2 != null) {
			label2 = l2;
		}
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
		if (arg1 instanceof CounterController.CounterEvent) {
			// handle increase, decrease and reset of counter
			CounterController.CounterEvent counterEvent = (CounterController.CounterEvent) arg1;

			switch (counterEvent.operation) {
			case INCREASE:
				if (edgeList.contains(counterEvent.edge))
					this.value += counterEvent.value;
				break;
			case DECREASE:
				if (edgeList.contains(counterEvent.edge))
					this.value -= counterEvent.value;
				break;
			case RESET:
				this.value = 0;
				break;
			}

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
		p.text(label1, position.x + 5, position.y + textSize);
		p.text(label2, position.x + 5, position.y + (textSize * 2));
		p.line(position.x, position.y + (textSize * 2) + 10, position.x + xSize, position.y + (textSize * 2) + 10);
		p.text(value + "", position.x + 5, position.y + 10 + (textSize * 3));
	}

}

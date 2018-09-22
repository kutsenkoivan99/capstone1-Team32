package de.openhpi.capstone1.game.model;

import java.util.EnumMap;
import java.util.Map;

import processing.core.PApplet;

public class PlayGround extends AlingedFigures {
	
	public  enum  BORDER_TYPE {REFLECT,KILL};
	public enum STATE {
        NEW, RUNNING, WAITING, FINISHED;
    }
	
	public float ySize;
	public float xSize;
	Map<BORDER_LOC, BORDER_TYPE> borderMap = new EnumMap<BORDER_LOC, BORDER_TYPE>(BORDER_LOC.class);
	
	public Map<BORDER_LOC, BORDER_TYPE> getBorderMap() {
		return borderMap;
	}
	
	public class KillEvent {
		public BORDER_LOC edge;
		
		public KillEvent(BORDER_LOC edge){
			this.edge =edge;
		}
	}
	
	public KillEvent buildKillEvent(BORDER_LOC edge) {
		return new KillEvent(edge);
	}
	public PlayGround(float x, float y, float xSize, float ySize){
		position= new Vector2d(x,y);
		this.xSize=xSize;
		this.ySize = ySize;
		borderMap.put(BORDER_LOC.TOP, BORDER_TYPE.KILL);
		borderMap.put(BORDER_LOC.BOTTOM, BORDER_TYPE.KILL);
		borderMap.put(BORDER_LOC.RIGHT, BORDER_TYPE.REFLECT);
		borderMap.put(BORDER_LOC.LEFT, BORDER_TYPE.REFLECT);
	}
	@Override
	public void draw(PApplet p) {
		  p.noStroke();
		  p.fill(color);
		  p.rect(position.x,position.y,xSize,ySize);
		  
		  //p.noStroke();
		  int c = (borderMap.get(BORDER_LOC.TOP)== BORDER_TYPE.KILL)?p.color(255f,0f,0f):p.color(0);
		  p.stroke(c);
		  p.line(position.x, position.y, position.x+xSize, position.y);
		  
		  c = (borderMap.get(BORDER_LOC.BOTTOM)== BORDER_TYPE.KILL)?p.color(255,0,0):p.color(100);
		  p.stroke(c);
		  p.line(position.x, position.y+ySize, position.x+xSize, position.y+ySize);
		  
		  c = (borderMap.get(BORDER_LOC.RIGHT)== BORDER_TYPE.KILL)?p.color(255f,0f,0f):p.color(0);
		  p.stroke(c);
		  p.line(position.x+xSize-1, position.y, position.x+xSize-1, position.y+ySize-1);
		  c = (borderMap.get(BORDER_LOC.LEFT)== BORDER_TYPE.KILL)?p.color(255f,0f,0f):p.color(0);
		  p.stroke(c);
		  p.line(position.x, position.y, position.x, position.y+ySize-1);
		  p.stroke(0);
	}

	public void changed() {
		this.setChanged();
	}

	@Override
	public Vector2d getCenter() {
		return Vector2d.add(position, xSize/2, ySize/2);
	}


}

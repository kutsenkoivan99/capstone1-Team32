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
	Map<DrawEdges, BORDER_TYPE> borderMap = new EnumMap<DrawEdges, BORDER_TYPE>(DrawEdges.class);
	
	public Map<DrawEdges, BORDER_TYPE> getBorderMap() {
		return borderMap;
	}
	
	public PlayGround(float x, float y, float xSize, float ySize){
		position= new Vector2d(x,y);
		this.xSize=xSize;
		this.ySize = ySize;
		borderMap.put(DrawEdges.TOP, BORDER_TYPE.KILL);
		borderMap.put(DrawEdges.BOTTOM, BORDER_TYPE.KILL);
		borderMap.put(DrawEdges.RIGHT, BORDER_TYPE.REFLECT);
		borderMap.put(DrawEdges.LEFT, BORDER_TYPE.REFLECT);
	}
	
	@Override
	public void move() {
		  for(Drawable drawable: children) {
			  drawable.move();
		  }
		
	}
	
	@Override
	public boolean dedectAndHandleCollision(Drawable drawable) { 
		boolean hasCollision = super.dedectAndHandleCollision(drawable);
		  for(Drawable child: children) {
			  hasCollision |= child.dedectAndHandleCollision(drawable);
		  }
		  return hasCollision;
	}
	@Override
	public void draw(PApplet p) {
		  p.noStroke();
		  p.fill(color);
		  p.rect(position.x,position.y,xSize,ySize);
		  
		  //p.noStroke();
		  int c = (borderMap.get(DrawEdges.TOP)== BORDER_TYPE.KILL)?p.color(255f,0f,0f):p.color(0);
		  p.stroke(c);
		  p.line(position.x, position.y, position.x+xSize, position.y);
		  
		  c = (borderMap.get(DrawEdges.BOTTOM)== BORDER_TYPE.KILL)?p.color(255,0,0):p.color(100);
		  p.stroke(c);
		  p.line(position.x, position.y+ySize, position.x+xSize, position.y+ySize);
		  
		  c = (borderMap.get(DrawEdges.RIGHT)== BORDER_TYPE.KILL)?p.color(255f,0f,0f):p.color(0);
		  p.stroke(c);
		  p.line(position.x+xSize-1, position.y, position.x+xSize-1, position.y+ySize-1);
		  c = (borderMap.get(DrawEdges.LEFT)== BORDER_TYPE.KILL)?p.color(255f,0f,0f):p.color(0);
		  p.stroke(c);
		  p.line(position.x, position.y, position.x, position.y+ySize-1);
		  p.stroke(0);
		  for(Drawable drawable: children) {
			  drawable.draw(p);
		  }
	}

	@Override
	public Vector2d getCenter() {
		return Vector2d.add(position, xSize/2, ySize/2);
	}


}

/**
 * 
 */
package de.openhpi.capstone1.game.model;


import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

/**This interface marks objects that can be drawn with the Processing Library on a PApplet.
 * @author 
 *
 */
public interface Drawable {
	
	
	// the edges of the drawing area or a rectangle
	public  enum DrawEdges  {TOP, BOTTOM, RIGHT, LEFT}

	List<Drawable> children = new ArrayList<Drawable>();
	/**get the drawing position of this object. For a rectangle this is the top left corner
	 * @return the drawing position
	 */
	public Vector2d getPosition(); 
	
	/**set the new drawing position of the object
	 * @param newPosition the new drawing position
	 */
	public void setPosition(Vector2d newPosition); 
	
	/**get the drawing angle
	 * @return the drawing angle
	 */
	public float getAngle();
	
	/**get the fill color of the object
	 * @return the color
	 */
	public int getColor();
	
	/**Set the fill color for drawing of the object 
	 * @param color the color in rgb format
	 */
	public void setColor(int color); 
	
	/**Set the fill color for drawing of the object by giving the r, g, b components 
	 * @param r red limited to the range 0..255
	 * @param g green limited to the range 0..255
	 * @param b blue limited to the range 0..255
	 */
	public void setColor(int r, int g, int b);
	
	/**Get the position of the center of the object. This is also the center for the rotation.
	 * @return the center position
	 */
	public Vector2d getCenter();
	
	/**Indicate if this drawable is visible
	 * @return true or false
	 */
	public boolean isVisible();
	
	/**Set the visibility of the drawable 
	 * @param isVisible - true or false
	 */
	public void setVisible(boolean isVisible);
	
	/**Draw the object on the given processing applet window
	 * @param p the processing applet
	 */
	public void draw(PApplet p);

	/**Move the drawable by its velocity
	 * 
	 */
	public void move();
	
	/**detect if this drawable collides (overlaps) with another drawable.
	 * @param drawable -currently only a ball is supported by this method.
	 * @return true if there is an collision
	 */
	public boolean dedectAndHandleCollision(Drawable drawable);

	/**Add the drawable child as child to this drawable. The child is only added if it is not already a child of the parent.
	 * The position of the drawable will be interpreted relative to the position of the parent
	 * It is up to this drawable ( the parent) to forward the draw and move method to the children
	 * @param child a drawable to add as child
	 */
	public void addChild(Drawable child);
	
	/**If this child drawable is a child of the parent, remove it from the list of children
	 * @param child the child to remove;
	 */
	public void removeChild(Drawable child);
}

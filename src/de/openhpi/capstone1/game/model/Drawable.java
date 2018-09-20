/**
 * 
 */
package de.openhpi.capstone1.game.model;


import processing.core.PApplet;

/**This interface marks objects that can be drawn with the Processing Library on a PApplet.
 * @author walter
 *
 */
public interface Drawable {
	
	
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
	
	public boolean isVisible();
	
	public void setVisible(boolean isVisible);
	/**Draw the object on the given processing applet window
	 * @param p the processing applet
	 */
	public void draw(PApplet p);

}

package de.openhpi.capstone1.game.model;

/**This class is used to specify 2 dimensional vectors for graphics processing
 * the operations are written with a graphical window in mind, that has its origin (0,0) in the top left corner and 
 * positive y values are in the direction down. To be compatible with the Processing library, the values are defined as float.
 * @author
 *
 */
public class Vector2d {
	public float x=0;
	public float y=0;
	
	
	public Vector2d(float xValue, float yValue){
		x=xValue;
		y=yValue;
	}
	
	public Vector2d() {
	}

	public static Vector2d add(Vector2d pos1, Vector2d pos2) {
		return new Vector2d(pos1.x + pos2.x, pos1.y +pos2.y);
	}
	
	public static Vector2d add(Vector2d position, float xSize, float ySize) {
		return new Vector2d(position.x+xSize,position.y+ySize);
	}
	
	public Vector2d add(Vector2d coor) {
		this.x += coor.x;
		this.y += coor.y;
		return this;
	}

	public static Vector2d sub(Vector2d pos1, Vector2d pos2) {
		return new Vector2d(pos1.x - pos2.x, pos1.y -pos2.y);
	}
	public static Vector2d sub(Vector2d position, float xSize, float ySize) {
		return new Vector2d(position.x-xSize,position.y-ySize);
	}
	
	public Vector2d sub(Vector2d coor) {
		this.x -= coor.x;
		this.y -= coor.y;
		return this;
	}

	public float length() {
		return (float)(Math.sqrt((x*x)+(y*y)));
	}

	public Vector2d copy() {
		return new Vector2d(x,y);
	}

	public Vector2d normalize() {
		float factor = this.length();
		Vector2d result = this.copy();
		result.x /= factor;
		result.y /= factor;
		return result;
	}

	public Vector2d mult(float factor) {
		Vector2d result = this.copy();
		result.x *= factor;
		result.y *= factor;
		return result;
	}

	public float heading() {
		// TODO Auto-generated method stub
		return 0;
	}


	public void rotate(float angle) {
		double theta = Math.atan2(-y, x);
		double lenght = Math.sqrt((x*x)+(y*y));
		double alpha = Math.toRadians(angle);
		x = (float) ( Math.cos(theta-alpha) *lenght);
		y = (float) ( -(Math.sin(theta-alpha) *lenght)); 
		
	}


}

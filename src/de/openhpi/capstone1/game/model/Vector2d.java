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
	
	
	/**construct a vector from the x any y value 
	 * @param xValue positive values pointing to the right 
	 * @param yValue positive values pointing to the bottom of the screen
	 */
	public Vector2d(float xValue, float yValue){
		x=xValue;
		y=yValue;
	}
	
	/**construct a null vector
	 * x and y are 0
	 */
	public Vector2d() {
		x=0;
		y=0;
	}

	/**Add 2 vectors and return sum as new one
	 * @param pos1
	 * @param pos2
	 * @return the sum vector
	 */
	public static Vector2d add(Vector2d pos1, Vector2d pos2) {
		return new Vector2d(pos1.x + pos2.x, pos1.y +pos2.y);
	}
	
	/**construct a second vector from the  x and a y value to a vector and return the sum of both vectors as a new one
	 * @param v1 the first vector 
	 * @param x  the x value of the second vector
	 * @param y  the y value of the second vector
	 * @return a new vector holding the sum of the 2 vectors
	 */
	public static Vector2d add(Vector2d v1, float x, float y) {
		return new Vector2d(v1.x+x,v1.y+y);
	}
	
	/**add a vector v2 to this one
	 * @param v2 the vector to add
	 * @return this
	 */
	public Vector2d add(Vector2d v2) {
		this.x += v2.x;
		this.y += v2.y;
		return this;
	}

	/**Subtract the second vector from the first one and return the result as new vector
	 * @param v1 
	 * @param v2
	 * @return a new vector holding v1-v2
	 */
	public static Vector2d sub(Vector2d v1, Vector2d v2) {
		return new Vector2d(v1.x - v2.x, v1.y -v2.y);
	}
	/**Subtract the second vector constructed out of x and y from the first one v1 and return the result as new vector
	 * @param v1
	 * @param x
	 * @param y
	 * @return a new vector holding v1 -v2(x,y)
	 */
	public static Vector2d sub(Vector2d v1, float x, float y) {
		return new Vector2d(v1.x-x,v1.y-y);
	}
	
	/**subrtact vector v2 from this vector 
	 * @param v2
	 * @return this 
	 */
	public Vector2d sub(Vector2d v2) {
		this.x -= v2.x;
		this.y -= v2.y;
		return this;
	}

	/**Calculate the lenght of a vector 
	 * @return the length
	 */
	public float length() {
		return (float)(Math.sqrt((x*x)+(y*y)));
	}

	/**Clone this vector 
	 * @return the copy
	 */
	public Vector2d copy() {
		return new Vector2d(x,y);
	}

	/**Normalize the vector ( let it point in the same direction, but make the length to 1
	 * @return a new normalized vector
	 */
	public Vector2d normalize() {
		float factor = this.length();
		Vector2d result = this.copy();
		result.x /= factor;
		result.y /= factor;
		return result;
	}

	/**multiply a vector by a factor
	 * @param factor
	 * @return a new vector which is this one multiplied by a vector
	 */
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


	/**rotate a vector by an angle
	 * @param angle
	 */
	public void rotate(float angle) {
		double theta = Math.atan2(-y, x);
		double lenght = Math.sqrt((x*x)+(y*y));
		double alpha = Math.toRadians(angle);
		x = (float) ( Math.cos(theta-alpha) *lenght);
		y = (float) ( -(Math.sin(theta-alpha) *lenght)); 
		
	}


}

package net.mrx13415.searchcircle.math;


public class Vector2d {

	private double x = 0.0;
	private double y = 0.0;

	public Vector2d() {

	}

	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return The length oft this vector.
	 */
	public double length() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}

	public Vector2d getDirection() {
		double abs = this.length();
		return new Vector2d(this.x / abs, this.y / abs);
	}

	public double getDistance(Vector2d vector) {
		double deltaX = this.x - vector.getX();
		double deltaY = this.y - vector.getY();
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	}

	/**
	 * @param vector
	 * 
	 * @return The interior angle between this and another vectors in
	 *         degrees.
	 */
	public double angle(Vector2d vector) {
		double radians = Math.acos(this.mul(vector)
				/ (this.length() * vector.length()));
		return Math.toDegrees(radians);
	}

	/**
	 * Calculates the dot product with a vector.
	 * 
	 * @param vector
	 * 
	 * @return Dot product
	 */
	public double mul(Vector2d vec) {
		return this.x * vec.getX() + this.y * vec.getY();
	}

	/**
	 * Subtracts a vector from this vector.
	 * 
	 * @param vector
	 * 
	 * @return difference
	 */
	public Vector2d sub(Vector2d vector) {
		return new Vector2d(this.getX() - vector.getX(), this.getY()
				- vector.getY());
	}

	/**
	 * Adds a vector to this vector.
	 * 
	 * @param vector
	 * 
	 * @return sum total
	 */
	public Vector2d add(Vector2d vector) {
		return new Vector2d(this.getX() + vector.getX(), this.getY()
				+ vector.getY());
	}

	/**
	 * Rotates the the Vector around a given point.
	 * 
	 * @Param phi The angle in radians (rotates clockwise)
	 * 
	 * @Param point The rotation point as a local Vector.
	 * 
	 * @Return The vector rotated by <code> phi </ code> at
	 *         <code> Point </ code>
	 */
	public Vector2d rotate(double phi, Vector2d point) {
		Vector2d vector = this.sub(point);
		vector = vector.rotate(phi);
		return vector.add(point);
	}

	/**
	 * Rotates this vector around the origin. (clockwise)
	 * 
	 * @param phi
	 *            The angle in radians (rotates clockwise)
	 * 
	 * @return The vector rotated by <code> phi </ code>
	 */
	public Vector2d rotate(double phi) {
		return new Vector2d(
				(double) (this.getX() * Math.cos(phi) - this.getY()
						* Math.sin(phi)), (double) (this.getX()
						* Math.sin(phi) + this.getY() * Math.cos(phi)));
	}

}

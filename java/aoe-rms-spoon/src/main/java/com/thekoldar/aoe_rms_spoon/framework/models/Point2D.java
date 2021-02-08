package com.thekoldar.aoe_rms_spoon.framework.models;

/**
 * A point with 2 coordiantes having integer values
 * @author massi
 *
 */
public class Point2D {

	private int x;
	private int y;
	
	public Point2D(Point2D b) {
		this(b.x, b.y);
	}
	
	public Point2D(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Point2D sum(int x, int y) {
		return new Point2D(this.x+ x, this.y + y);
	}
	
	public Point2D sum(Point2D b) {
		return new Point2D(this.x + b.x, this.y + b.y);
	}
	
	public Point2D minus(int x, int y) {
		return new Point2D(this.x - x, this.y - y);
	}
	
	public Point2D minus(Point2D b) {
		return new Point2D(this.x - b.x, this.y - b.y);
	}
	
	public Point2D scale(int x, int y) {
		return new Point2D(this.x * x, this.y * y);
	}
	
	public Point2D scale(Point2D b) {
		return new Point2D(this.x * b.x, this.y * b.y);
	}
	
	public Point2D scale(int k) {
		return new Point2D(this.x * k, this.y * k);
	}
	
	public Point2D translate(int x, int y) {
		return this.sum(x, y);
	}
	
	public Point2D translate(Point2D p) {
		return this.sum(x, y);
	}
	
	/**
	 * clamp coordiantes between 2 other points.
	 * @param minX
	 * @param minY
	 * @param maxX
	 * @param maxY
	 * @return
	 */
	public Point2D clamp(int minX, int minY, int maxX, int maxY) {
		var result = new Point2D(this);
		if (result.x < minX) {
			result.x = minX;
		}
		if (result.x > maxX) {
			result.x = maxX;
		}
		if (result.y < minY) {
			result.y = minY;
		}
		if (result.y > maxY) {
			result.y = maxY;
		}
		return result;
	}
	
	/**
	 * clamp coordinates between 0 and 100
	 * @return
	 */
	public Point2D clampZero100() {
		return this.clamp(0, 0, 100, 100);
	}
	
	
	
	
}

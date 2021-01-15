package com.thekoldar.aoe_rms_spoon.framework.models;

public class Point2D {

	private int x;
	private int y;
	
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
	
	
	
	
	
}

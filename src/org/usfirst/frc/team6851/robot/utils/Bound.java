package org.usfirst.frc.team6851.robot.utils;


import org.opencv.core.Rect;

public class Bound {

	public double left;
	public double right;
	public double top;
	public double bottom;

	public Bound(double left, double top, double right, double down) {
		super();
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = down;
	}

	/*public PointDouble GetCenter() {
		return new PointDouble(left + (right - left) / 2, bottom + (top - bottom) / 2);
	}

	public String CenterToString() {
		PointDouble d = GetCenter();
		return "(" + d.x + ", " + d.y + ")";
	}*/
	
	public double getWidth(){
		return Math.abs( right - left );
	}
	
	public double getHeight(){
		return Math.abs( top - bottom );
	}
	
	public double getArea(){
		return getWidth() * getHeight();
	}

	public Rect toRect() {
		return new Rect((int) top, (int) left, (int) Math.abs(top - bottom), (int) Math.abs(right - left));
	}
	
	public String toString(){
		return "Bound(" + left +  ", " + top + ", " + right + ", " + bottom + ")";
	}
}
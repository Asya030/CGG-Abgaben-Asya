package cgg.a05;

import cgtools.*;

public class Camera {
	public double angle;
	public double width;
	public double height;
	public Point origin;
	
	public Camera(double winkel, double width, double height) {
		this.angle = winkel;
		this.width = width;
		this.height = height;
		this.origin = Vector.point(0, 0, 0);
	}
	
	public Ray rayCalc (double x, double y) {
		return new Ray(origin, Vector.direction(x-(width/2), (height/2)-y, -(width/2) / Math.tan(angle/2)), Double.POSITIVE_INFINITY, 0);
	}

}
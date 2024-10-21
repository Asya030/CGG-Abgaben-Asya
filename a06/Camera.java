package cgg.a06;

import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Vector;

public class Camera {
	double phi;
	double width;
	double height;
	Matrix matrix;
	Point x0 = new Point(0,0,0);

	public Camera(double phi, double width, double height, Matrix matrix) {
		this.phi = phi;
		this.width = width;
		this.height = height;
		this.matrix = matrix;
		
	}

	public Ray generateRay(double x, double y) {
		x = x - width / 2;
		y = -(y - height / 2);
		double z = -((width / 2) / Math.tan(phi / 2));
		Direction d = Vector.normalize(Vector.direction(x, y, z));

		Point rayStartpunkt = Matrix.multiply(matrix, x0);
		d = Matrix.multiply(matrix, d);

		Ray ray = new Ray(rayStartpunkt, d, cgtools.Util.EPSILON, Double.POSITIVE_INFINITY);

		return ray;

	}
	public Ray rayCalc (double x, double y) {
		return new Ray(x0, Vector.direction(x-(width/2), (height/2)-y, -(width/2) / Math.tan(phi/2)), Double.POSITIVE_INFINITY, 0);
	}
	

}

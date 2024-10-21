package cgg.a05;

import cgtools.*;

public class Ray {
	
	public final Point origin;
	public final Direction direction;
	public final double tmax;
    public final double tmin;
	
	public Ray(Point origin, Direction direction, double tmax, double tmin) {
		this.origin = origin;
		this.direction = Vector.normalize(direction);
        this.tmax = tmax;
        this.tmin = tmin;
	}
	
	public Point pointAt(double t) {
		if(isValid(t)) {
			Point p = Vector.add(origin, Vector.multiply(t, direction));
			return p;
		}else 
		{
			return null;
		}
	}

    public boolean isValid(double t) {
         return (t > tmin && t < tmax );
    }

}

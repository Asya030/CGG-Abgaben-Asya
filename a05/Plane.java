package cgg.a05;

import cgtools.*;

public class Plane implements Shape{
	
	public Color color;
	public Point point;
	public Direction direction;
	public final double tmin, tmax;
	public Material material;
	
	public Plane(Point punkt, Direction richtung, Material material) {
		this.direction = richtung;
		this.point = punkt;
		this.tmin = 0;
		this.tmax = Double.POSITIVE_INFINITY;
		this.material = material;
	}

	public Hit intersect(Ray ray) {
		if(Vector.dotProduct(ray.direction, direction) == 0) {
			return null;
		}else 
		{
			double t = ((Vector.dotProduct(point, direction) - Vector.dotProduct(ray.origin, direction)) / Vector.dotProduct(ray.direction, direction));
			if(t==0) 
			{
				return null;
			}else 
			{			
				if(t> tmin && t<tmax) {
					Point x = ray.pointAt(t);	
					return new Hit(t,x,direction, material);				
				}else 
				{
					return null;
				}				
			}
		}
	}
}
package cgg.a05;


import cgtools.*;

public class Sphere implements Shape {

	private Point point;
    private double radius;
    private Material color;
    
    public Sphere (Point point, double radius, Material color){
        this.point = point;
        this.radius = radius;
        this.color = color;
    }

    public Hit intersect(Ray r) {
        Direction x0s = Vector.subtract(r.origin, point);
		double a = Vector.dotProduct(r.direction, r.direction);
		double b = 2 * Vector.dotProduct(x0s, r.direction);
		double c = Vector.dotProduct(x0s, x0s) - (radius * radius);

		double discr = (b*b) - (4 * a * c);

		if(discr < 0 ) {
			return null;
		}
		
		else if(discr == 0) {
			double result = -b/(2*a);
			Point x = r.pointAt(result);
			if(x == null) {
				return null;
			}else 
			{
				Direction n = Vector.divide(Vector.subtract(x, point), radius);
				return new Hit(result, x, n, color);
			}
			
		}
		else 
		{
			double tMinus = (-b - Math.sqrt(Math.pow(b, 2) - 4*a*c))/ 2*a;
			double tPlus = (-b + Math.sqrt(Math.pow(b, 2) - 4*a*c))/ 2*a;
			double result = Math.min(tMinus, tPlus);
			
			Point x = r.pointAt(result);
			if(x == null) {
				return null;
			}
			else {
				Direction n = Vector.divide(Vector.subtract(x, point), radius);
				return new Hit(result, x, n, color);				
			}
		}
    }
}

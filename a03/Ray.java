package cgg.a03;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Ray {

    private Direction x0;
    private Direction direction;
    private double tmin;
    private double tmax;

    public Direction getx0() {
        return x0;
    }
    
    public void setx0(Direction x0) {
        this.x0 = x0;
    }
    
    
    public Direction getDirection() {
        return direction;
    }
    

    public Ray (Direction x0, Direction direction, double tmin, double tmax){
        this.x0 = x0;
        this.direction = direction;
        this.tmin = tmin;
        this.tmax = tmax;
    }
    
    //Berechnung von x(t) = x0+t*d
    public Point pointAt(double t){
        Direction tdir = Vector.multiply(direction,t);
        Point x = Vector.point(Vector.add(x0,tdir));
        return x;
    }

    //Überprüfung ob Parameter t im Intervall [tmin,tmax] liegt
    public boolean isValid (double t){
        return t >= tmin && t <= tmax;
    }


}

package cgg.a05;

import cgtools.*;

public class Background implements Shape {
    Material maincolor;

    public Background(Material maincolor) {
        this.maincolor = maincolor;
    }

    @Override
    public Hit intersect(Ray ray) {
        double t = Double.POSITIVE_INFINITY - 0.1;
		Point x = ray.pointAt(t);
		Direction n = Vector.direction(0, 0, 0);
		return new Hit(t,x,n, maincolor);
    }
}

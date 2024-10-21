package cgg.a04;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;


public class Background implements Shape {
    private Color color;

    //Konstruktor
    public Background(Color color) {
        this.color = color;
    }

    @Override
    public Hit intersect(Ray r) {
        // t auf unendlich setzten, da der Hintergrund nie geschnitten wird 
        double t = Double.POSITIVE_INFINITY;
        Point hitPoint = r.pointAt(t);
        Direction normal = r.getDirection();
        return new Hit(t, hitPoint, normal, color);
    }
}
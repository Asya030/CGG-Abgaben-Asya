package cgg.a04;

import cgtools.*;

public class Plane implements Shape {
    private Point anchorPoint;
    private Direction normal;
    private double radius;
    private Color color;

    public Plane(Point anchorPoint, Direction normal, double radius, Color color) {
        this.anchorPoint = anchorPoint;
        this.normal = Vector.normalize(normal);
        this.radius = radius;
        this.color = color;
    }
    //Mithilfe von ChatGPT
    @Override
    public Hit intersect(Ray r) {
        double d = Vector.dotProduct(normal, r.getDirection());

        // Wenn der Nenner nahe Null ist, ist der Strahl parallel zur Ebene und schneidet sie nicht
        if (Math.abs(d) < 0.00001) {
            return null;
        }

        // Berechnung des Abstands t entlang des Strahls zur Ebene
        double t = Vector.dotProduct(Vector.subtract(anchorPoint, r.getx0()), normal) / d;

        // Prüfen, ob t im gültigen Bereich liegt
        if (!r.isValid(t)) {
            return null;
        }

        Point hitPoint = r.pointAt(t);

        // Überprüfen, ob der Trefferpunkt innerhalb des Radius r liegt
        double distanceFromAnchor = Vector.length(Vector.subtract(hitPoint, anchorPoint));
        if (distanceFromAnchor > radius) {
            return null;
        }

        // Rückgabe eines neuen Hit-Objekts mit den Schnittdetails
        return new Hit(t, hitPoint, normal, color);
    }
}
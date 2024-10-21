package cgg.a06;
import cgtools.*;

public class Cylinder implements Shape{
    Point center;
    double radius;
    Material mat;
    double height;
    
    public Cylinder (double height, double radius, Point center, Material mat){
        this.center = center;
        this.radius = radius;
        this.mat = mat;
        this.height = height;
    }

    public Hit intersect (Ray r){
        /*Quelle:
         * https://silo.tips/download/studienarbeit-implementation-eines-raytracers-innerhalb-der-3d-plattform-groimp
         */
        Direction centerDirection = Vector.direction(center.x(), center.y(), center.z()); // Mittelpunkt des Zylinders
        Point newXo = Vector.subtract(r.origin, centerDirection); //transformierte Uhrsprung des Strahls
        Direction newNormal = Vector.direction(r.direction.x(), 0, r.direction.z());
        newXo = Vector.point(newXo.x(), 0, newXo.z()); // x0 auf xz-Ebene projizieren

        double a = Vector.dotProduct(newNormal, newNormal);
        double b = 2 * Vector.dotProduct(newXo, newNormal);
        double c = Vector.dotProduct(newXo, newXo) - Math.pow(radius, 2);
    
        double discriminant = (b * b) - (4 * a * c);

        if (discriminant >= 0) {
            double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double t2 = (-b - Math.sqrt(discriminant)) / (2 * a);
    
            if (t1 < t2) {
                if (r.isValid(t1)) {
                    Point intersectionPoint = r.pointAt(t1);
    
                    if (intersectionPoint.y() > center.y() + height || intersectionPoint.y() < center.y()) {
                        return null;
                    }
    
                    Direction normal = Vector.normalize(Vector.divide(Vector.subtract(intersectionPoint, center), radius));
                    return new Hit(t1, intersectionPoint, normal, mat);
                }
            } else {
                if (r.isValid(t2)) {
                    Point intersectionPoint = r.pointAt(t2);
                    if (intersectionPoint.y() > center.y() + height || intersectionPoint.y() < center.y()) {
                        return null;
                    }
    
                    Direction normal = Vector.normalize(Vector.divide(Vector.subtract(intersectionPoint, center), radius));
                    return new Hit(t2, intersectionPoint, normal, mat);
                }
            }
        }
        
        return null;
    }
    //Erstellt Zylinder mit zwei "Deckeln"
    public static Group onCylinder(double height, double radius, Point center,Material whiteMaterial) {
        Shape[] groupOnCylinder = new Shape[3];
        Cylinder cylinder = new Cylinder(height, radius, center, whiteMaterial);
        Point centerP1 = Vector.add(center, new Point(0, height, 0));

        Disk disk1 = new Disk(centerP1, new Direction(0, 1, 0), whiteMaterial, radius);
        Disk disk2 = new Disk(center, new Direction(0, -1, 0), whiteMaterial, radius);

        groupOnCylinder[0] = cylinder;
        groupOnCylinder[1] = disk1;
        groupOnCylinder[2] = disk2;

        return new Group(groupOnCylinder);
    }
    }

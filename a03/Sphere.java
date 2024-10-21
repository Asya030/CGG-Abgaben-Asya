package cgg.a03;
import cgtools.*;;


public class Sphere {
    private Direction center;
    private double radius;
    private Color color;
    
    public Sphere (Direction center, double radius, Color color){
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public Hit intersect (Ray r){

        Direction oc = Vector.subtract(r.getx0(), center);
        double a = Vector.dotProduct(r.getDirection(), r.getDirection());
        double b = 2.0 * Vector.dotProduct(oc, r.getDirection());
        double c = Vector.dotProduct(oc, oc) - (radius * radius);
        double t = 0;

       double discr = (b*b) - (4 * a * c);

       if (discr < 0){
        return null;
       }

       if ( discr == 0 ){
        t = (-b + Math.sqrt(discr))/2*a;

       }
       else {
        double  t1 = (-b + Math.sqrt(discr))/2*a;
        double  t2 = (-b - Math.sqrt(discr))/2*a;
        t = Math.min(t1, t2);
       }
       // Berechnung des Normalenvektors für shade Methode
       Direction xm = Vector.subtract(r.pointAt(t),Vector.point(center));
       Direction normal = Vector.multiply(1/radius, xm);

       if (r.isValid(t)){
        return new Hit(t,r.pointAt(t),normal,color);
       }
       else{
        return null;
       }
    }

}
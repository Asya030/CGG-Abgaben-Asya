package cgg.a03;
import cgtools.*;

public class Camera {
    private double phi;
    private double w;
    private double h;

    public Camera (double phi, double w, double h){
        this.phi = phi;
        this.w = w;
        this.h = h;
    }

    public Ray generateRay(double x, double y){
        Direction x0 = new Direction(0,0,0); //Kamerastandpunkt
        // Berechnung der Strahlrichtung für eine Bildposition
        double x1 = x -(w/2);
        double x2 = (-1)*(y - (h/2));
        double x3 = (-1)*((w/2)/(Math.tan(phi/2)));

        Direction d = new Direction(x1,x2,x3);
        Direction dN = Vector.normalize(d);

        Ray ray = new Ray(x0,dN, 0, 2000);

        return ray;


    }


}

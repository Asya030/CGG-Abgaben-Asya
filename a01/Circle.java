package cgg.a01;
import cgtools.*;

public class Circle {
    Color circleColor;
    Color backgroundColor;
    double radius;

    //Konstruktor
    Circle (Color circleColor, Color backgroundColor, double radius){
        this.circleColor = circleColor;
        this.backgroundColor = backgroundColor;
        this.radius = radius;
    }

    public Color getColor(double x, double y){
        double distance = Math.sqrt ((x-256)*(x-256) + (y-128)* (y-128)); // Berechnet den Abstand von der Pixel Koordinate zum Mittelpunkt
        if (distance <= radius){ // innerhalb der Kreises
            return circleColor;
        } 
        return backgroundColor;
    
    }


}

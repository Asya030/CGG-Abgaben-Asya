package cgg.a02;
import cgtools.*;

class Disc {
    double centerX, centerY, radius;
    Color color;

    Disc(double centerX, double centerY, double radius, Color color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
    }

    boolean isPointInDisc(double x, double y) {
        return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2); // Überprüft ob die Entfernung des Punktes <= dem Radius ist 
    }
}

package cgg.a01;

import cgtools.*;

public class PolkaDots {
    Color circleColor;
    Color backgColor;
    int numCircles;
    double radius;

    // Konstruktor
    public PolkaDots(Color circleColor, Color backgColor, int numCircles, double radius) {
        this.circleColor = circleColor;
        this.backgColor = backgColor;
        this.numCircles = numCircles;
        this.radius = radius;
    }
    //Mit Hilfe von ChatGPT
    public Color getColor(double x, double y) {
        double gridSpacingX = 512.0 / numCircles; // Abstand der Mittelpunkte
        double gridSpacingY = 256.0 / numCircles;

        // Berechnet die Position im Raster
        int gridX = (int) (x / gridSpacingX);
        int gridY = (int) (y / gridSpacingY);

        // Berechnet die Position des Mittelpunkts des Kreises im Raster
        double circleCenterX = gridX * gridSpacingX + gridSpacingX / 2;
        double circleCenterY = gridY * gridSpacingY + gridSpacingY / 2;

        // Berechnet den Abstand zum Mittelpunkt des Kreises
        double distanceToCenter = Math.sqrt((x - circleCenterX) * (x - circleCenterX) + (y - circleCenterY) * (y - circleCenterY));

        // Wenn der Abstand kleiner oder gleich dem Radius ist, ist der Punkt innerhalb des Kreises
        if (distanceToCenter <= radius) {
            return circleColor;
        } else {
            return backgColor;
        }
    }
}



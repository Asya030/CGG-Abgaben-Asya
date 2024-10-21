package cgg.a09;

import cgtools.Color;
import cgtools.Sampler;

public class DotsTexture implements Sampler {
    Color polkaColor;
    Color backgroundColor;
    double radius;

    public DotsTexture(Color polkaColor, Color backgrouColor, double radius) {
        this.polkaColor = polkaColor;
        this.backgroundColor = backgrouColor;
        this.radius = radius;
    }
    

    @Override
    public Color getColor(double u, double v) {
        double kasten = radius * 2.5;
        // double kasten = (width + radius) / 7;
        u = u % kasten;
        v = v % kasten;

        // >= inside circle, < outside circle
        // hsv von x abhängig machen um die Farbe zu ändern
        double distance = Math.sqrt(Math.pow(u - kasten / 2, 2) + Math.pow(v - kasten / 2, 2));
        if (distance <= radius) {
            // hsv (color, saturation, intensity)
            return polkaColor;
        }
        return backgroundColor;
    }
}
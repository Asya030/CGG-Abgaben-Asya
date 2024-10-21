package cgg.a08;

import cgtools.*;

public record Lochkamera(double angle, int height, int width) {

    public Ray generateRay(double xRay, double yRay) {
        double x = xRay - width / 2;
        double y = -yRay + height / 2;
        double z = -1 * ((width / 2) / Math.tan(angle / 2));

        return new Ray(Vector.zero, new Direction(x, y, z), 0, Double.POSITIVE_INFINITY);
    }
}
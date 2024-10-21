package cgg.a03;

import cgtools.*;

public class Raytracer implements Sampler {
    public Camera camera;
    public Scene scene;


    public Raytracer(Camera camera, Scene scene) {
        this.camera = camera;
        this.scene = scene;
    }

    @Override
    public Color getColor(double x, double y) {
        Ray ray = camera.generateRay(x, y);
        Hit hit = scene.intersect(ray);
        Color color = scene.backgrColor;
        
        if (hit != null) {
            color = shade(hit.getN(),hit.getC());
            }
        return color;
        }

    static Color shade(Direction normal, Color color) {
        Direction lightDir = Vector.normalize(Vector.direction(1, 1, 0.5));
        Color ambient = Vector.multiply(0.1, color);
        Color diffuse = Vector.multiply(0.9 * Math.max(0, Vector.dotProduct(lightDir, normal)), color);
        return Vector.add(ambient, diffuse);
        }
}




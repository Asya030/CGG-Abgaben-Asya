package cgg.a04;

import cgtools.*;

public class Raytracer implements Sampler {
    public Camera camera;
    public Group scene;
    private Color backgroundColor;


    public Raytracer(Camera camera, Group scene, Color backgroundColor) {
        this.camera = camera;
        this.scene = scene;
        this.backgroundColor = backgroundColor;
    }

    @Override
    public Color getColor(double x, double y) {
        Ray ray = camera.generateRay(x, y);
        Hit hit = scene.intersect(ray);
        
        if (hit != null) {
            return shade(hit.getN(), hit.getC());
        } else {
            return backgroundColor;
        }
    }

    private Color shade(Direction normal, Color color) {
        Direction lightDir = Vector.normalize(Vector.direction(1, 1, 0.5));
        Color ambient = Vector.multiply(0.1, color);
        Color diffuse = Vector.multiply(0.9 * Math.max(0, Vector.dotProduct(lightDir, normal)), color);
        return Vector.add(ambient, diffuse);
    }
}



package cgg.a05;

import cgtools.Vector;

public class Main {
    //gesamter Code teilweise mit ChatGPT und Hilfe von Kommilitonen
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        final int width = 1280;
        final int height = 720;
        final int sampleRate = 16;

        Background background = new Background(new BackgroundMaterial());
        DiffusesMaterial materialCol = new DiffusesMaterial(Vector.lightgray);

        Camera camera = new Camera(Math.PI / 3, width, height);
        Shape plane = new Plane(Vector.point(0.0, -1, 0.0), Vector.direction(0, 1, 0), materialCol);

        
        Shape sphere0 = new Sphere(Vector.point(-0.8, -0.8, -3), 0.2, new DiffusesMaterial(Vector.red));
        Shape sphere1 = new Sphere(Vector.point(0.0, -0.5, -3.5), 0.5, new DiffusesMaterial(Vector.green));
        Shape sphere2 = new Sphere(Vector.point(1.5, -0.6, -5), 0.4, new DiffusesMaterial(Vector.orange));
        Shape sphere4 = new Sphere(Vector.point(-0.1, -0.85, -5), 0.15, new DiffusesMaterial(Vector.skyblue));
        Shape sphere5 = new Sphere(Vector.point(-0.8, -0.8, -3.5), 0.2, new DiffusesMaterial(Vector.pink));
        Shape sphere6 = new Sphere(Vector.point(-1.5, -0.75, -4), 0.25, new DiffusesMaterial(Vector.orange));
        Shape sphere7 = new Sphere(Vector.point(0.8, -0.75, -5), 0.25, new DiffusesMaterial(Vector.yellow));
        Shape sphere8 = new Sphere(Vector.point(1.0, -0.95, -3.2), 0.05, new DiffusesMaterial(Vector.pink));

        final String filename = "doc/a05-diffuse-spheres.png";
        Group gruppe = new Group(plane, background, sphere0, sphere1, sphere2, sphere4, sphere5, sphere6, sphere7, sphere8);
        Raytracer raytracer = new Raytracer(camera, gruppe, sampleRate, width, height, filename);
    }
}

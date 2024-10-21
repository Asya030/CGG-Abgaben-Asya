package cgg.a04;

import cgtools.*;
import cgg.Image;

import static cgtools.Vector.black;
import static cgtools.Vector.blue;
//import static cgtools.Vector.green;
import static cgtools.Vector.orange;
import static cgtools.Vector.skyblue;
import static cgtools.Vector.white;
import static cgtools.Vector.lightgray;

public class Main {
    public static void main(String[] args) {
        final int width = 1024;
        final int height = 512;


        Camera cam = new Camera(Math.PI / 2, width, height);
        Shape background = new Background(blue);
        Shape ground = new Plane(new Point(0, -1.5, 0.0), new Direction(0, 1, 0), 1000, lightgray);
        Shape globe1 = new Sphere(new Direction(0.0, -1.0, -5.0), 1.0, white);
        Shape globe2 = new Sphere(new Direction(0.0, 0.0, -5.0), 0.75, white);
        Shape globe3 = new Sphere(new Direction(0.0, 0.75, -5.0), 0.5, white);
        Shape globe4 = new Sphere(new Direction(-0.15, 0.85, -4.5), 0.05, black);
        Shape globe5 = new Sphere(new Direction(0.15, 0.85, -4.5), 0.05, black);
        Shape globe6 = new Sphere(new Direction(0.0, 0.75, -4.5), 0.1, orange);
        
        Group scene = new Group();
        scene.addShape(background);
        scene.addShape(ground);
        scene.addShape(globe1);
        scene.addShape(globe2);
        scene.addShape(globe3);
        scene.addShape(globe4);
        scene.addShape(globe5);
        scene.addShape(globe6);

        
        Image image = new Image(width, height, 10);
        Raytracer rt = new Raytracer(cam, scene, skyblue);
        image.sample(rt);

        
      // Write the image to disk.
      final String filename = "doc/a04-image.png";
      image.write(filename);
      System.out.println("Wrote image: " + filename);
    }
}
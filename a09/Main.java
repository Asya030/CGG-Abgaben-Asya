package cgg.a09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import cgg.Image;
import cgtools.*;
 //Mithilfe von ChatGPT und Kommilitonen
public class Main {
        public static void main(String[] args) {
                final int width = 3840;
                final int height = 2160;

                List<Shape> sceneShapes = new ArrayList<>();
                List<Shape> snowman = new ArrayList<>();

                // Materials
                DiffusedMaterial snowMaterial = new DiffusedMaterial("doc/schnee.jpg");
                DiffusedMaterial hatMaterial = new DiffusedMaterial("doc/leder.jpg");
                DiffusedMaterial eyeMaterial = new DiffusedMaterial(Vector.color(0, 0, 0));
                EmittingMaterial glitterMaterial = new EmittingMaterial(Vector.color(1, 1, 0.2)); 

                // Ground
                Point wpPoint = new Point(0, 0, 0.0);
                Direction wpDirection = new Direction(0, 1, 0);
                PolishedMetalMaterial wpMaterial = new PolishedMetalMaterial(new CheckerBoard(Vector.white, Vector.gray,300),0.5);
                Plane ground= new Plane(wpPoint, wpDirection, wpMaterial, 500, 500);

                //Hintergrund
                BackgroundMaterial skyMaterial = new BackgroundMaterial("doc/himmel.jpg");
                Shape sky = new Background(skyMaterial);

                // Shapes
                Collections.addAll(sceneShapes,sky, ground);


                 // Building a snowman
                Shape base = new Sphere(new Point(0, 0.5, 0), 0.5, snowMaterial);
                Shape middle = new Sphere(new Point(0, 1.2, 0), 0.4, snowMaterial);
                Shape head = new Sphere(new Point(0, 1.8, 0), 0.3, snowMaterial);
                Shape hatBase = new Zylinder(new Point(0, 2.0, 0), 0.35, 0.05, hatMaterial); // Hat brim
                Shape hatTop = new Zylinder(new Point(0, 2.25, 0), 0.2, 0.2, hatMaterial); // Hat top
                Shape leftEye = new Sphere(new Point(-0.1, 1.9, 0.28), 0.05, eyeMaterial); // Left eye
                Shape rightEye = new Sphere(new Point(0.1, 1.9, 0.28), 0.05, eyeMaterial); // Right eye
        
                Collections.addAll(snowman, base, middle, head, hatBase, hatTop, leftEye, rightEye);
        
                double snowmanAmount = 10;
                double Xmin = -8;
                double Xmax = 8;
                double Ymin = 0;
                double Ymax = 0; // Keep Y fixed for snowmen on the ground
                double Zmin = -30;
                double Zmax = 1.5;
        
                for (int i = 0; i < snowmanAmount; i++) {
                    double randX = Random.random() * (Xmax - Xmin + 1) + Xmin;
                    double randZ = Random.random() * (Zmax - Zmin + 1) + Zmin;
        
                    Matrix translate = Matrix.translation(randX, Ymin, randZ);
                    Group snowmanGroup = new Group(snowman, new Transform(translate));
                    Collections.addAll(sceneShapes, snowmanGroup);
                }

                // Adding glittering spheres
                double glitterAmount = 100;
                double glitterXmin = -8;
                double glitterXmax = 8;
                double glitterYmin = 0;
                double glitterYmax = 3;
                double glitterZmin = -30;
                double glitterZmax = 1.5;

                for (int i = 0; i < glitterAmount; i++) {
                        double randX = Random.random() * (glitterXmax - glitterXmin + 1) + glitterXmin;
                        double randY = Random.random() * (glitterYmax - glitterYmin + 1) + glitterYmin;
                        double randZ = Random.random() * (glitterZmax - glitterZmin + 1) + glitterZmin;

                        Shape glitter = new Sphere(new Point(randX, randY, randZ), 0.05, glitterMaterial);
                        Collections.addAll(sceneShapes, glitter);
                }

                // Settings

                /**
                * translation
                * x: negativ = links, positiv = rechts
                * y: negativ = oben, positiv = unten
                * z: negativ = vorne, positiv = hinten
                * 
                * rotation/direction
                * x: negativ = neigung nach unten, positiv = neigung nach oben
                * -90 = Vogelperspektive
                * y: negativ = rotation nach links, positiv = rotation nach rechts
                * z: negativ = rotation nach vorne, positiv = rotation nach hinten
                */
                Camera camera = new Camera(Math.PI / 3, width, height, new Direction(0, 0, 0), Matrix.translation(0, 1.5, -1));

                Group sceneOneGroup = new Group(sceneShapes, new Transform(Matrix.translation(0, 0, -3)));

                // This class instance defines the contents of the image.
                Raytracer content = new Raytracer(camera, sceneOneGroup);

                // Creates an image and iterates over all pixel positions inside the image
                //Image image = new Image(width, height,30);
                Image image2 = new Image(width, height,30);

                // extra stuff
                try {
                        image2.parallelization(content, 5,4);
                } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                }
                
                
                final String filename2 = "doc/a09-textures.png";
                image2.write(filename2);

                System.out.println("Wrote image: " + filename2);
               

        }

}
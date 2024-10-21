package cgg.a03;
import static cgtools.Vector.blue;
import static cgtools.Vector.direction;
import static cgtools.Vector.green;
import static cgtools.Vector.yellow;
import static cgtools.Vector.pink;
import static cgtools.Vector.skyblue;
import static cgtools.Vector.gray;
import cgg.Image;

public class Main{
public static void main(String[] args) {
    final int width = 512;
    final int height = 256;
    Scene scene = new Scene(gray); 
    Camera cam = new Camera(Math.PI/2, width, height);

    Sphere sphere1 = new Sphere(direction(0, 1, -3), 1, yellow);
    Sphere sphere2 = new Sphere(direction(4, 1, -5), 1, blue);
    Sphere sphere3 = new Sphere(direction(3, 0, -9), 1, green);
    Sphere sphere4 = new Sphere(direction(-6, 0, -7), 1, skyblue);
    Sphere sphere5 = new Sphere(direction(-1, 2,-5), 1, pink);
    scene.addSphere(sphere1);  
    scene.addSphere(sphere2); 
    scene.addSphere(sphere3);
    scene.addSphere(sphere4);
    scene.addSphere(sphere5);
    Image image = new Image(width, height,10);
    Raytracer rt = new Raytracer(cam, scene);
    
      image.sample(rt);
      final String filename = "doc/a03-image.png";
      image.write(filename);
      System.out.println("Wrote image: " + filename);

   
   
}
}
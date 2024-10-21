package cgg.a03;
import cgtools.*;
import java.util.ArrayList;
import java.util.List;


public class Scene {
    public Color backgrColor;

    public List<Sphere> spheres = new ArrayList<Sphere>();

    public Scene (Color backgColor){
        this.backgrColor = backgColor;
    }

    public void addSphere(Sphere sphere){
        spheres.add(sphere);
       }
    

    public Hit intersect(Ray ray){
        Hit lastHit = null;

        for (Sphere sphere : spheres){
            Hit newHit = sphere.intersect(ray);
            if (newHit == null)
                continue;
            
            lastHit = newHit;
        }
        return lastHit;
    }



}
